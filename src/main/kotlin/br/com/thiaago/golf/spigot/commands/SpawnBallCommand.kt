package br.com.thiaago.golf.spigot.commands

import br.com.thiaago.golf.constants.Constants
import br.com.thiaago.golf.spigot.service.SpawnBallService
import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.Default
import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player

@Command("spawnball")
class SpawnBallCommand(private val messagesConfig: FileConfiguration?) : BaseCommand() {

    @Default
    fun execute(player: Player) {
        if (!player.hasPermission(Constants.SPAWN_BALL_COMMAND_PERMISSION.value)) {
            player.sendMessage(
                ChatColor.translateAlternateColorCodes(
                    '&', messagesConfig?.getString("NOT_PERM") ?: "&cNot permission!"
                )
            )
            return
        }
        SpawnBallService.spawnBall(player.location)

    }

}