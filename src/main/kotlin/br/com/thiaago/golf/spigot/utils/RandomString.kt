package br.com.thiaago.golf.spigot.utils

import java.util.*

class RandomString {

    companion object {
        fun generateRandomString(random: Random, length: Int): String {
            return random.ints(48, 122)
                .filter { i -> (i < 57 || i > 65) && (i < 90 || i > 97) }
                .mapToObj { i -> i }
                .limit(length.toLong())
                .collect({ StringBuilder() }, java.lang.StringBuilder::append, java.lang.StringBuilder::append)
                .toString()
        }

    }

}