package com.d.tickettoride.model

enum class PlayerColor {
    RED, BLUE, BLACK, GREEN, YELLOW, NONE;

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