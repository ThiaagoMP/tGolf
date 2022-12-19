package br.com.thiaago.golf.spigot.inventory

import br.com.thiaago.golf.constants.Constants
import br.com.thiaago.golf.controllers.GolfController
import br.com.thiaago.golf.spigot.utils.ItemBuilder
import dev.triumphteam.gui.components.GuiType
import dev.triumphteam.gui.guis.Gui
import dev.triumphteam.gui.guis.GuiItem
import io.github.bananapuncher714.nbteditor.NBTEditor
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player

class GolfInventory(private val golfController: GolfController) {

    val gui: Gui =
        Gui.gui().title(Component.text(ChatColor.translateAlternateColorCodes('&', "&7Golf Clubs"))).type(GuiType.CHEST)
            .rows(3).create()

    fun configure() {
        golfController.storedGolfClubs.forEach { golfClub ->
            val lore = golfClub.lore.toMutableList()
            lore.replaceAll { string ->
                string.replace("%power%", golfClub.power.toString())
            }
            val guiItem = GuiItem(
                NBTEditor.set(
                    ItemBuilder(Material.GOLD_HOE).setDisplayName(golfClub.displayName).setLore(lore).toItemStack(),
                    golfClub.power,
                    Constants.GOLF_CLUB_NBT.value
                )
            )

            guiItem.setAction { event ->
                event.isCancelled = true
                val player = event.whoClicked as Player
                if (player.inventory.firstEmpty() == -1) return@setAction
                player.inventory.addItem(guiItem.itemStack.clone())
                player.closeInventory()
            }

            gui.setItem(golfClub.slot, guiItem)
        }
    }

}