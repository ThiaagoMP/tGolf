package br.com.thiaago.golf.config.setup

import br.com.thiaago.golf.GolfPlugin
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ConfigSetup {

    companion object {
        fun setupFile(plugin: GolfPlugin, configName: String): File {
            val file = File(plugin.dataFolder, configName)
            if (!file.exists()) {
                file.parentFile.mkdirs()
                plugin.saveResource(configName, false)
            }
            return file
        }

        fun setupFileConfiguration(file: File, configName: String): FileConfiguration {
            val fileConfiguration: FileConfiguration = YamlConfiguration()
            try {
                fileConfiguration.load(file)
            } catch (exception: Exception) {
                exception.printStackTrace()
                Bukkit.getConsoleSender().sendMessage("§cError loading config: $configName")
            }
            return fileConfiguration
        }
    }

}