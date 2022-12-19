package br.com.thiaago.golf.spigot.loader

import br.com.thiaago.golf.controllers.GolfController
import br.com.thiaago.golf.dao.repository.GolfPlayersRepository
import br.com.thiaago.golf.model.GolfPlayer
import org.bukkit.entity.Player

class PlayerLoader {

    companion object {
        fun load(player: Player, golfController: GolfController, golfPlayersRepository: GolfPlayersRepository) {
            var golfPlayer = golfPlayersRepository.getGolfPlayer(player)
            if (golfPlayer == null) {
                golfPlayer = GolfPlayer(player, 0)
                golfPlayersRepository.insertPlayer(golfPlayer)
            }
            golfController.players.add(golfPlayer)
        }
    }

}