package br.com.thiaago.golf.spigot.event

import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerGolfSwingEvent(
    val player: Player,
    val power: Double,
    val armorStand: ArmorStand,
    private var cancelled: Boolean = false
) : Event(), Cancellable {

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }

    companion object {
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }
    }

    override fun isCancelled(): Boolean {
        return cancelled
    }

    override fun setCancelled(cancel: Boolean) {
        this.cancelled = cancel
    }

}
