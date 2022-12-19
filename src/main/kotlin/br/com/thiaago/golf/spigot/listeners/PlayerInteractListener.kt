package br.com.thiaago.golf.spigot.listeners

import br.com.thiaago.golf.constants.Constants
import br.com.thiaago.golf.spigot.event.PlayerGolfSwingEvent
import io.github.bananapuncher714.nbteditor.NBTEditor
import org.bukkit.Bukkit
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class PlayerInteractListener : Listener {

    @EventHandler
    fun onDamage(event: EntityDamageByEntityEvent) {
        if (event.damager.type != EntityType.PLAYER) return
        if (event.entity.type != EntityType.ARMOR_STAND || !event.entity.hasMetadata(Constants.METADATA_GOLF_BALL_ARMOR_STAND.value)) return

        val armorStand = event.entity as ArmorStand
        val player = event.damager as Player

        if (!NBTEditor.contains(player.inventory.itemInMainHand, Constants.GOLF_CLUB_NBT.value)) return
        val power = NBTEditor.getDouble(player.inventory.itemInMainHand, Constants.GOLF_CLUB_NBT.value)

        val playerGolfSwingEvent = PlayerGolfSwingEvent(player, power, armorStand)
        Bukkit.getPluginManager().callEvent(playerGolfSwingEvent)
        if (playerGolfSwingEvent.isCancelled) return

        event.isCancelled = true
        armorStand.remove()
    }

}