package br.com.thiaago.golf.loader

import br.com.thiaago.golf.config.ConfigController
import br.com.thiaago.golf.config.impl.GolfClubsConfig
import br.com.thiaago.golf.model.golfclub.impl.StoredGolfClub

class GolfClubLoader {

    companion object {
        fun load(configController: ConfigController): MutableList<StoredGolfClub> {
            val list = ArrayList<StoredGolfClub>()

            val golfClubsConfig =
                configController.configs[GolfClubsConfig::class.java]?.getConfig()
                    ?: return emptyList<StoredGolfClub>().toMutableList()

            golfClubsConfig.getKeys(false).forEach {
                val section = golfClubsConfig.getConfigurationSection(it)
                list.add(
                    StoredGolfClub(
                        it,
                        section.getInt("SLOT"),
                        section.getString("DISPLAY_NAME"),
                        section.getStringList("LORE"),
                        section.getDouble("POWER")
                    )
                )
            }

            return list
        }
    }

}