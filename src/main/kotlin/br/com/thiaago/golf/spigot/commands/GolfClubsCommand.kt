package br.com.thiaago.golf.spigot.commands

import br.com.thiaago.golf.GolfPlugin
import br.com.thiaago.golf.constants.Constants
import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.Default
import dev.triumphteam.cmd.core.annotation.SubCommand
import io.github.bananapuncher714.nbteditor.NBTEditor
import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player

@Command("golfclubs", alias = ["gc"])
class GolfClubsCommand(private val messagesConfig: FileConfiguration?) : BaseCommand() {

    @Default
    fun execute(player: Player) {
        if (!player.hasPermission(Constants.GOLF_CLUBS_COMMAND_PERMISSION.value)) {
            player.sendMessage(
                ChatColor.translateAlternateColorCodes(
                    '&',
                    messagesConfig?.getString("NOT_PERM") ?: "&cNot permission!"
                )
            )
            return
        }
        GolfPlugin.instance?.golfInventory?.gui?.open(player) ?: player.sendMessage(
            ChatColor.translateAlternateColorCodes(
                '&', "&cError occurred please contact an administrator!"
            )
        )
    }

    @SubCommand("setpower")
    fun executeSubCommand(player: Player, power: Double) {
        if (!player.hasPermission(Constants.GOLF_CLUBS_SET_POWER_COMMAND_PERMISSION.value)) {
            player.sendMessage(
                ChatColor.translateAlternateColorCodes(
                    '&', messagesConfig?.getString("NOT_PERM") ?: "&cNot permission!"
                )
            )
            return
        }
        val itemInHand = player.itemInHand
        player.itemInHand = NBTEditor.set(itemInHand, power, Constants.GOLF_CLUB_NBT.value)
        player.sendMessage(
            ChatColor.translateAlternateColorCodes(
                '&',
                messagesConfig?.getString("SET_POWER")
                    ?: "&7The power of your golf club in your hand has been set to: &a%power%"
            ).replace("%power%", power.toString())
        )
    }

}