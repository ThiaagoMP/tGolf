package br.com.thiaago.golf.spigot.event

import br.com.thiaago.golf.model.GolfSwing
import org.bukkit.Location
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerPocketedGolfBallEvent(
    val golfSwing: GolfSwing,
    val locationPocketed: Location,
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
