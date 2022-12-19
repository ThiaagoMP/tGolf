package br.com.thiaago.golf.dao.sql

import br.com.thiaago.golf.model.GolfPlayer
import org.bukkit.OfflinePlayer
import java.sql.Connection

object GolfPlayersTable {

    const val TABLE_NAME = "golf_players"
    const val FIELD_NICK = "nick"
    const val FIELD_POINTS = "points"

}

class GolfPlayersSQL(private val connection: Connection) {

    fun createTable() {
        val statement = connection.prepareStatement(
            "CREATE TABLE IF NOT EXISTS ${GolfPlayersTable.TABLE_NAME}" + "(${GolfPlayersTable.FIELD_NICK} varchar(16), ${GolfPlayersTable.FIELD_POINTS} bigint);"
        )
        statement.executeUpdate()
        statement.close()
    }

    fun insertPlayer(golfPlayer: GolfPlayer) {
        val preparedStatement = connection.prepareStatement(
            "INSERT INTO ${GolfPlayersTable.TABLE_NAME}" + " (${GolfPlayersTable.FIELD_NICK},${GolfPlayersTable.FIELD_POINTS}) VALUES (?,?);"
        )
        preparedStatement.setString(1, golfPlayer.player.name)
        preparedStatement.setLong(2, golfPlayer.points)
        preparedStatement.executeUpdate()
        preparedStatement.close()
    }

    fun updatePlayer(golfPlayer: GolfPlayer) {
        val preparedStatement = connection.prepareStatement(
            "UPDATE ${GolfPlayersTable.TABLE_NAME} " + "SET ${GolfPlayersTable.FIELD_POINTS} = ? WHERE ${GolfPlayersTable.FIELD_NICK} = ?;"
        )
        preparedStatement.setLong(1, golfPlayer.points)
        preparedStatement.setString(2, golfPlayer.player.name)
        preparedStatement.executeUpdate()
        preparedStatement.close()
    }

    fun hasPlayer(player: OfflinePlayer): Boolean {
        val preparedStatement =
            connection.prepareStatement("SELECT * FROM ${GolfPlayersTable.TABLE_NAME} WHERE ${GolfPlayersTable.FIELD_NICK} = ?;")
        preparedStatement.setString(1, player.name)
        val resultSet = preparedStatement.executeQuery()
        val has = resultSet.next()
        resultSet.close()
        return has
    }

    fun getGolfPlayer(player: OfflinePlayer): GolfPlayer? {
        val preparedStatement =
            connection.prepareStatement("SELECT * FROM ${GolfPlayersTable.TABLE_NAME} WHERE ${GolfPlayersTable.FIELD_NICK} = ?;")
        preparedStatement.setString(1, player.name)
        val resultSet = preparedStatement.executeQuery()
        var golfPlayer: GolfPlayer? = null
        while (resultSet.next()) {
            golfPlayer = GolfPlayer(player, resultSet.getLong(GolfPlayersTable.FIELD_POINTS))
        }
        preparedStatement.close()
        resultSet.close()
        return golfPlayer
    }

}