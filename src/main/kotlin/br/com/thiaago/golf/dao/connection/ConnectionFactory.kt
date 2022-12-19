package br.com.thiaago.golf.dao.connection

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection

class ConnectionFactory {
    private val type = "jdbc:mysql://"
    private val driverName = "com.mysql.jdbc.Driver"

    fun setup(
        host: String,
        database: String,
        username: String,
        password: String
    ): Connection {
        val config = HikariConfig().apply {
            this.jdbcUrl = "$type$host/$database"
            this.driverClassName = driverName
            this.username = username
            this.password = password
        }
        val dataSource = HikariDataSource(config)
        return dataSource.connection
    }

}