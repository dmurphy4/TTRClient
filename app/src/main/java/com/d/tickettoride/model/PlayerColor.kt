package com.d.tickettoride.model

enum class PlayerColor {
    RED, BLUE, BLACK, GREEN, YELLOW;

    fun getColor():String {
        return when (this) {
            RED -> "Red"
            BLACK -> "Black"
            BLUE -> "Blue"
            GREEN -> "Green"
            YELLOW -> "Yellow"
        }
    }
}