package br.com.thiaago.golf.model.golfclub.impl

import br.com.thiaago.golf.model.golfclub.GolfClub

data class StoredGolfClub(
    val key: String,
    val slot: Int,
    val displayName: String,
    val lore: List<String>,
    override val power: Double
) :
    GolfClub(power)