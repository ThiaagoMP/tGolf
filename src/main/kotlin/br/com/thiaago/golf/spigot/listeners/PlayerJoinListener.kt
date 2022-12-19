package br.com.thiaago.golf.spigot.listeners

import br.com.thiaago.golf.controllers.GolfController
import br.com.thiaago.golf.dao.repository.GolfPlayersRepository
import br.com.thiaago.golf.spigot.loader.PlayerLoader
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener(
    private val golfController: GolfController, private val golfPlayersRepository: GolfPlayersRepository
) : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        PlayerLoader.load(event.player, golfController, golfPlayersRepository)
    }

}