package br.com.thiaago.golf.spigot.listeners

import br.com.thiaago.golf.constants.Constants
import br.com.thiaago.golf.spigot.event.GolfBallHitEvent
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent

class ProjectileHitListener : Listener {

    @EventHandler
    fun onHit(event: ProjectileHitEvent) {
        if (event.entity.type == EntityType.SNOWBALL && event.entity.hasMetadata(Constants.METADATA_GOLF_BALL_SNOW.value)) Bukkit.getPluginManager()
            .callEvent(GolfBallHitEvent(event.entity))
    }

}