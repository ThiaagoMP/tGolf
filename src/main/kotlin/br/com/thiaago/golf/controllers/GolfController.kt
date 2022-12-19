package br.com.thiaago.golf.controllers

import br.com.thiaago.golf.GolfPlugin
import br.com.thiaago.golf.config.impl.GolfHolesConfig
import br.com.thiaago.golf.loader.GolfClubLoader
import br.com.thiaago.golf.loader.GolfHolesLoader
import br.com.thiaago.golf.model.GolfHole
import br.com.thiaago.golf.model.GolfPlayer
import br.com.thiaago.golf.model.golfclub.impl.StoredGolfClub

class GolfController(
    val players: MutableList<GolfPlayer> = ArrayList(),
    val storedGolfClubs: MutableList<StoredGolfClub> = GolfClubLoader.load(GolfPlugin.instance!!.configController!!),
    val storedGolfHoles: MutableList<GolfHole> = GolfHolesLoader.load(
        GolfPlugin.instance?.configController?.configs?.get(GolfHolesConfig::class.java)?.getConfig()!!
    )
)