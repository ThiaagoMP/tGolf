package br.com.thiaago.golf.spigot.commands

import br.com.thiaago.golf.constants.Constants
import br.com.thiaago.golf.controllers.GolfController
import br.com.thiaago.golf.dao.repository.GolfPlayersRepository
import br.com.thiaago.golf.model.GolfPlayer
import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.ArgName
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.Default
import dev.triumphteam.cmd.core.annotation.SubCommand
import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player

@Command("score")
class ScoreCommand(
    private val messagesConfig: FileConfiguration?,
    private val golfController: GolfController?,
    private val golfPlayersRepository: GolfPlayersRepository?
) : BaseCommand() {

    @Default
    fun execute(player: Player, @ArgName("playerTarget") playerTarget: Player) {
        if (!player.hasPermission(Constants.SCORE_COMMAND_PERMISSION.value)) {
            player.sendMessage(
                ChatColor.translateAlternateColorCodes(
                    '&', messagesConfig?.getString("NOT_PERM") ?: "&cNot permission!"
                )
            )
            return
        }

        val golfPlayer = getGolfPlayer(playerTarget) ?: return

        player.sendMessage(
            ChatColor.translateAlternateColorCodes(
                '&',
                messagesConfig?.getString("SCORE")?.replace(
                    "%score%", golfPlayer.points.toString()
                )?.replace("%player%", golfPlayer.player.name) ?: "&7Points: ${golfPlayer.points}"
            )
        )
    }

    @SubCommand("setpoints")
    fun executeSetPoints(
        player: Player, @ArgName("playerTarget") playerTarget: Player, @ArgName("points") points: Long
    ) {
        if (!player.hasPermission(Constants.SCORE_SET_POINTS_COMMAND_PERMISSION.value)) {
            player.sendMessage(
                ChatColor.translateAlternateColorCodes(
                    '&', messagesConfig?.getString("NOT_PERM") ?: "&cNot permission!"
                )
            )
            return
        }

        val golfPlayer = getGolfPlayer(playerTarget) ?: return

        golfPlayersRepository?.updatePlayer(golfPlayer) ?: return
        golfPlayer.points = points

        player.sendMessage(
            ChatColor.translateAlternateColorCodes(
                '&', messagesConfig?.getString("ALTERED_POINTS") ?: "&7Points altered!"
            ).replace("%player%", playerTarget.name).replace("%points%", points.toString())
        )

    }

    private fun getGolfPlayer(playerTarget: Player): GolfPlayer? {
        var golfPlayer = golfController?.players?.firstOrNull { golfPlayer -> golfPlayer.player == playerTarget }

        if (golfPlayer == null)
            golfPlayer = golfPlayersRepository?.getGolfPlayer(playerTarget) ?: return null

        return golfPlayer
    }


}
