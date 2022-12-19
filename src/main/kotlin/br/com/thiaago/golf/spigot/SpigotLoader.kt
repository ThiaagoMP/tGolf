package br.com.thiaago.golf.spigot

import br.com.thiaago.golf.GolfPlugin
import br.com.thiaago.golf.config.impl.GolfHolesConfig
import br.com.thiaago.golf.config.impl.MessagesConfig
import br.com.thiaago.golf.spigot.commands.GolfClubsCommand
import br.com.thiaago.golf.spigot.commands.ScoreCommand
import br.com.thiaago.golf.spigot.commands.SetHoleCommand
import br.com.thiaago.golf.spigot.commands.SpawnBallCommand
import br.com.thiaago.golf.spigot.listeners.PlayerInteractListener
import br.com.thiaago.golf.spigot.listeners.PlayerJoinListener
import br.com.thiaago.golf.spigot.listeners.ProjectileHitListener
import br.com.thiaago.golf.spigot.listeners.custom.GolfBallHitListener
import br.com.thiaago.golf.spigot.listeners.custom.PlayerGolfSwingListener
import br.com.thiaago.golf.spigot.listeners.custom.PlayerPocketedGolfBallListener
import dev.triumphteam.cmd.bukkit.BukkitCommandManager
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

class SpigotLoader {

    companion object {
        fun load(plugin: GolfPlugin) {
            val golfController = plugin.golfController!!
            val messagesConfig = plugin.configController?.configs?.get(MessagesConfig::class.java)?.getConfig()!!

            Bukkit.getPluginManager()
                .registerEvents(PlayerJoinListener(golfController, plugin.golfPlayersRepository!!), plugin)
            Bukkit.getPluginManager().registerEvents(PlayerInteractListener(), plugin)
            Bukkit.getPluginManager().registerEvents(ProjectileHitListener(), plugin)

            Bukkit.getPluginManager().registerEvents(PlayerGolfSwingListener(), plugin)
            Bukkit.getPluginManager().registerEvents(GolfBallHitListener(golfController), plugin)
            Bukkit.getPluginManager().registerEvents(
                PlayerPocketedGolfBallListener(golfController, plugin.golfPlayersRepository!!, messagesConfig),
                plugin
            )

            val manager: BukkitCommandManager<CommandSender> = BukkitCommandManager.create(plugin)
            manager.registerCommand(
                GolfClubsCommand(messagesConfig),
                ScoreCommand(messagesConfig, plugin.golfController, plugin.golfPlayersRepository),
                SpawnBallCommand(messagesConfig),
                SetHoleCommand(
                    plugin.configController!!.configs[GolfHolesConfig::class.java]!!,
                    messagesConfig,
                    golfController
                )
            )
        }
    }

}