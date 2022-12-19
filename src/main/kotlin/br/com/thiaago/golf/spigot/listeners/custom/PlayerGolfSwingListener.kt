package br.com.thiaago.golf.spigot.listeners.custom

import br.com.thiaago.golf.GolfPlugin
import br.com.thiaago.golf.constants.Constants
import br.com.thiaago.golf.model.GolfSwing
import br.com.thiaago.golf.spigot.event.PlayerGolfSwingEvent
import br.com.thiaago.golf.spigot.utils.VectorVerifier
import org.bukkit.ChatColor
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
        val velocityMultiplied = direction.add(Vector(0.0, direction.y + (event.power / 2), 0.0)).multiply(event.power)

        if (!VectorVerifier.verify(velocityMultiplied)) {
            event.player.sendMessage(
                ChatColor.translateAlternateColorCodes(
                    '&',
                    "&cThe power of your bat is very strong, you can break anything with it! Please use another one!"
                )
            )
            event.isCancelled = true
            return
        }

        snowball.velocity = velocityMultiplied
        snowball.setMetadata(
            Constants.METADATA_GOLF_BALL_SNOW.value,
            FixedMetadataValue(
                GolfPlugin.instance,
                GolfSwing(event.player, event.power, event.armorStand)
            )
        )
    }

}