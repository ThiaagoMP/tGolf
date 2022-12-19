package br.com.thiaago.golf

import br.com.thiaago.golf.config.ConfigController
import br.com.thiaago.golf.controllers.GolfController
import br.com.thiaago.golf.dao.connection.ConnectionFactory
import br.com.thiaago.golf.dao.repository.GolfPlayersRepository
import br.com.thiaago.golf.spigot.SpigotLoader
import br.com.thiaago.golf.spigot.inventory.GolfInventory
import br.com.thiaago.golf.spigot.loader.PlayerLoader
import com.google.common.util.concurrent.MoreExecutors
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.Executors

class GolfPlugin : JavaPlugin() {

    companion object {
        var instance: GolfPlugin? = null
            private set
    }

    var configController: ConfigController? = null
    var golfController: GolfController? = null

    var golfPlayersRepository: GolfPlayersRepository? = null

    var golfInventory: GolfInventory? = null

    private val executorService = Executors.newSingleThreadExecutor()
    var service = MoreExecutors.listeningDecorator(executorService)

    override fun onEnable() {
        instance = this
        saveDefaultConfig()
        configController = ConfigController().load(this);

        golfPlayersRepository = GolfPlayersRepository(
            ConnectionFactory().setup(
                config.getString("MySQL.HOST"),
                config.getString("MySQL.DATABASE"),
                config.getString("MySQL.USER"),
                config.getString("MySQL.PASSWORD")
            ),
            service
        )

        golfPlayersRepository?.createTable() ?: run {
            Bukkit.getPluginManager().disablePlugin(this)
        }

        golfController = GolfController()

        golfInventory = GolfInventory(golfController!!)
        golfInventory!!.configure()

        SpigotLoader.load(this)

        Bukkit.getOnlinePlayers().forEach {
            PlayerLoader.load(it, golfController!!, golfPlayersRepository!!)
        }
    }

    override fun onDisable() {
        golfPlayersRepository?.closeConnection() ?: return
    }
}