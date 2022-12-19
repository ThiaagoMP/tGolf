package br.com.thiaago.golf.spigot.utils

import org.bukkit.util.Vector

class VectorVerifier {

    companion object {
        fun verify(vector: Vector) =
            vector.isInAABB(Vector(-4, -4, -4), Vector(4, 4, 4))

    }

}