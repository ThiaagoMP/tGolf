package br.com.thiaago.golf.spigot.listeners.custom

import br.com.thiaago.golf.constants.Constants
import br.com.thiaago.golf.controllers.GolfController
import br.com.thiaago.golf.model.GolfHole
import br.com.thiaago.golf.model.GolfSwing
import br.com.thiaago.golf.spigot.event.GolfBallHitEvent
import br.com.thiaago.golf.spigot.event.PlayerPocketedGolfBallEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.util.Vector

class GolfBallHitListener(private val golfController: GolfController) : Listener {

    @EventHandler
    fun onHit(event: GolfBallHitEvent) {
        val snowball = event.snowball
        val golfSwing = snowball.getMetadata(Constants.METADATA_GOLF_BALL_SNOW.value)[0].value() as GolfSwing

        val golfHole: GolfHole = golfController.storedGolfHoles.firstOrNull { golfHoles ->
            golfHoles.location == snowball.location.block.location.add(Vector(0.0, -1.0, 0.0))
        } ?: return

        val playerPocketedGolfBallEvent = PlayerPocketedGolfBallEvent(golfSwing, golfHole.location)
        Bukkit.getPluginManager().callEvent(playerPocketedGolfBallEvent)

    }

}