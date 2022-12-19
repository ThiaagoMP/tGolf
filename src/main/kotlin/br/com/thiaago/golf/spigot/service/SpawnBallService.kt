package br.com.thiaago.golf.spigot.service

import br.com.thiaago.golf.GolfPlugin
import br.com.thiaago.golf.constants.Constants
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.util.EulerAngle

class SpawnBallService {

    companion object {
        fun spawnBall(location: Location) {
            // -1.2
            val armorStand =
                location.world.spawnEntity(
                    Location(
                        location.world,
                        location.x,
                        location.y - 1.2,
                        location.z,
                        location.yaw - 90,
                        location.pitch
                    ), EntityType.ARMOR_STAND
                ) as ArmorStand
            armorStand.itemInHand = ItemStack(Material.SNOW_BALL)
            armorStand.isVisible = false
            armorStand.isCustomNameVisible = false
            armorStand.canPickupItems = false
            armorStand.removeWhenFarAway = false
            armorStand.setGravity(false)
            armorStand.rightArmPose = EulerAngle(Math.toRadians(-90.0), Math.toRadians(-90.0), 0.0)
            armorStand.setMetadata(Constants.METADATA_GOLF_BALL_ARMOR_STAND.value, FixedMetadataValue(GolfPlugin.instance, 0))
        }
    }

}