package br.com.thiaago.golf.spigot.listeners.custom

import br.com.thiaago.golf.GolfPlugin
import br.com.thiaago.golf.constants.Constants
import br.com.thiaago.golf.model.GolfSwing
import br.com.thiaago.golf.spigot.event.PlayerGolfSwingEvent
import org.bukkit.entity.Snowball
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.util.Vector

class PlayerGolfSwingListener : Listener {

    @EventHandler
    fun onGolfSwing(event: PlayerGolfSwingEvent) {
        val snowball = event.armorStand.launchProjectile(Snowball::class.java)
        val direction = event.player.location.direction
        snowball.velocity = direction.add(Vector(0.0, direction.y + (event.power / 2), 0.0)).multiply(event.power)
        snowball.setMetadata(
            Constants.METADATA_GOLF_BALL_SNOW.value,
            FixedMetadataValue(
                GolfPlugin.instance,
                GolfSwing(event.player, event.power, event.armorStand)
            )
        )
    }

}