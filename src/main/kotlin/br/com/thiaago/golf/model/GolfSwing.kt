package br.com.thiaago.golf.model

import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player

data class GolfSwing(
    val player: Player,
    val power: Double,
    val armorStand: ArmorStand,
)