package br.com.thiaago.golf.spigot.commands

import br.com.thiaago.golf.config.CustomConfig
import br.com.thiaago.golf.constants.Constants
import br.com.thiaago.golf.controllers.GolfController
import br.com.thiaago.golf.model.GolfHole
import br.com.thiaago.golf.spigot.utils.RandomString
import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.Default
import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import java.util.*

@Command("sethole")
class SetHoleCommand(
    private val golfHolesConfig: CustomConfig?,
    private val messagesConfig: FileConfiguration?,
    private val golfController: GolfController?
) :
    BaseCommand() {

    @Default
    fun execute(player: Player) {
        if (!player.hasPermission(Constants.SET_HOLE_COMMAND_PERMISSION.value)) {
            player.sendMessage(
                ChatColor.translateAlternateColorCodes(
                    '&',
                    messagesConfig?.getString("NOT_PERM") ?: "&cNot permission!"
                )
            )
            return
        }

        val golfHole = GolfHole(player.location.block.location.add(0.0, -1.0, 0.0))
        golfController?.storedGolfHoles?.add(golfHole) ?: return

        val section =
            golfHolesConfig?.getConfig()?.createSection(RandomString.generateRandomString(Random(), 10)) ?: return
        section.set("world", player.location.world.name)
        section.set("x", player.location.block.x)
        section.set("y", player.location.block.y - 1)
        section.set("z", player.location.block.z)
        golfHolesConfig.save()
        player.sendMessage(
            ChatColor.translateAlternateColorCodes(
                '&',
                messagesConfig?.getString("GOLF_HOLE_CREATED") ?: "&7Golf hole created!"
            )
        )
    }

}