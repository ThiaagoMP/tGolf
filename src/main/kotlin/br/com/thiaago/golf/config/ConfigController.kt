package br.com.thiaago.golf.config

import br.com.thiaago.golf.GolfPlugin
import br.com.thiaago.golf.config.impl.GolfClubsConfig
import br.com.thiaago.golf.config.impl.GolfHolesConfig
import br.com.thiaago.golf.config.impl.MessagesConfig

class ConfigController(val configs: MutableMap<Class<*>, CustomConfig> = emptyMap<Class<*>, CustomConfig>().toMutableMap()) {

    fun load(plugin: GolfPlugin): ConfigController {
        configs[MessagesConfig::class.java] = MessagesConfig().setup(plugin)
        configs[GolfClubsConfig::class.java] = GolfClubsConfig().setup(plugin)
        configs[GolfHolesConfig::class.java] = GolfHolesConfig().setup(plugin)
        return this
    }

}