package br.com.thiaago.golf.config

import br.com.thiaago.golf.GolfPlugin
import org.bukkit.configuration.file.FileConfiguration
import java.io.File

interface CustomConfig {

    fun setup(plugin: GolfPlugin): CustomConfig

    fun getConfigName(): String?

    fun getConfig(): FileConfiguration?

    fun getFile(): File?

    fun save()

}