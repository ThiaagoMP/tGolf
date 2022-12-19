package br.com.thiaago.golf.loader

import br.com.thiaago.golf.model.GolfHole
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.configuration.file.FileConfiguration

class GolfHolesLoader {

    companion object {
        fun load(holesConfig: FileConfiguration): MutableList<GolfHole> {
            val holes = ArrayList<GolfHole>()
            holesConfig.getKeys(false).forEach {
                val section = holesConfig.getConfigurationSection(it)
                holes.add(
                    GolfHole(
                        Location(
                            Bukkit.getWorld(section.getString("world")),
                            section.getDouble("x"),
                            section.getDouble("y"),
                            section.getDouble("z")
                        )
                    )
                )
            }
            return holes
        }
    }

}