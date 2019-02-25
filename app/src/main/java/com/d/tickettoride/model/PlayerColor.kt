package com.d.tickettoride.model

enum class PlayerColor {
    RED, BLUE, BLACK, GREEN, YELLOW, NONE;

    fun getColor(color: String): PlayerColor {
        return when (color) {
            "red" -> RED
            "black" -> BLACK
            "blue" -> BLUE
            "green" -> GREEN
            "yellow" -> YELLOW
            else -> NONE
        }
    }
}