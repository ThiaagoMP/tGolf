package br.com.thiaago.golf.spigot.listeners.custom

import br.com.thiaago.golf.controllers.GolfController
import br.com.thiaago.golf.dao.repository.GolfPlayersRepository
import br.com.thiaago.golf.spigot.event.PlayerPocketedGolfBallEvent
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerPocketedGolfBallListener(
    private val golfController: GolfController,
    private val golfPlayersRepository: GolfPlayersRepository,
    private val messagesConfig: FileConfiguration
) : Listener {

    @EventHandler
    fun onPocketed(event: PlayerPocketedGolfBallEvent) {
        val golfPlayer =
            golfController.players.firstOrNull { golfPlayer -> golfPlayer.player == event.golfSwing.player } ?: return
        val player = golfPlayer.player.player
        golfPlayer.points++
        player.playSound(player.location, Sound.LEVEL_UP, 1f, 1f)
        player.sendMessage(
            ChatColor.translateAlternateColorCodes(
                '&',
                messagesConfig.getString("GOLF_POCKETED").replace("%points%", golfPlayer.points.toString())
            )
        )
        golfPlayersRepository.updatePlayer(golfPlayer)
    }

}