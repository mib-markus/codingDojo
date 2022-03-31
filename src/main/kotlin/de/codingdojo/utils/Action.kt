package de.codingdojo.utils

enum class Action {
    BLOCK, POKE, SHARPEN;

    companion object {

        fun parse(string: String) =
            string.toCharArray().map {
                when (it) {
                    'P' -> POKE
                    'S' -> SHARPEN
                    'B' -> BLOCK
                    else -> throw Exception("unknown action")
                }
            }
    }
}
