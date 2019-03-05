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

    override fun toString(): String {
        return when(this) {
            RED -> "red"
            BLUE -> "blue"
            BLACK -> "black"
            GREEN -> "green"
            YELLOW -> "yellow"
            NONE -> "gray"
        }
    }
}