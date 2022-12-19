package br.com.thiaago.golf.dao.repository

import br.com.thiaago.golf.dao.sql.GolfPlayersSQL
import br.com.thiaago.golf.model.GolfPlayer
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.ListeningExecutorService
import org.bukkit.OfflinePlayer
import java.sql.Connection

class GolfPlayersRepository(
    private val connection: Connection,
    private val service: ListeningExecutorService,
    private val golfPlayersSQL: GolfPlayersSQL = GolfPlayersSQL(connection)
) {

    fun createTable() {
        service.submit {
            golfPlayersSQL.createTable()
        }
    }

    fun insertPlayer(golfPlayer: GolfPlayer) {
        service.submit {
            golfPlayersSQL.insertPlayer(golfPlayer)
        }
    }

    fun updatePlayer(golfPlayer: GolfPlayer) {
        service.submit {
            golfPlayersSQL.updatePlayer(golfPlayer)
        }
    }

    fun hasPlayer(offlinePlayer: OfflinePlayer): Boolean {
        val guavaFuture: ListenableFuture<Boolean> = service.submit<Boolean> {
            return@submit golfPlayersSQL.hasPlayer(offlinePlayer)
        }
        return guavaFuture.get()
    }

    fun getGolfPlayer(offlinePlayer: OfflinePlayer): GolfPlayer? {
        val guavaFuture: ListenableFuture<GolfPlayer> = service.submit<GolfPlayer> {
            return@submit golfPlayersSQL.getGolfPlayer(offlinePlayer)
        }
        return guavaFuture.get()
    }

    fun closeConnection() {
        connection.close()
    }

}