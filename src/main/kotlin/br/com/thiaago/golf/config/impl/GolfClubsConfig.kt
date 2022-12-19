package br.com.thiaago.golf.config.impl

import br.com.thiaago.golf.GolfPlugin
import br.com.thiaago.golf.config.CustomConfig
import br.com.thiaago.golf.config.setup.ConfigSetup
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import java.io.File

class GolfClubsConfig : CustomConfig {

    private var file: File? = null
    private var fileConfig: FileConfiguration? = null

    override fun setup(plugin: GolfPlugin): CustomConfig {
        file = ConfigSetup.setupFile(plugin, getConfigName())
        fileConfig = ConfigSetup.setupFileConfiguration(file!!, getConfigName())
        return this
    }

    override fun getConfigName(): String {
        return "golfclubs.yml"
    }

    override fun getConfig(): FileConfiguration? {
        return fileConfig
    }

    override fun getFile(): File? {
        return file
    }

    override fun save() {
        try {
            fileConfig?.save(file!!)
        } catch (exception: Exception) {
            exception.printStackTrace()
            Bukkit.getConsoleSender()
                .sendMessage("§7An error occurred while saving a configuration: " + getConfigName())
        }
    }

}