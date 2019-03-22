package com.d.tickettoride.model.gameplay

enum class RouteColor {
    RED, WHITE, BLACK, GREEN, YELLOW, PURPLE, ORANGE, GRAY, BLUE;

    companion object {
        fun getCardColor(routeColor: RouteColor) : TrainCarCardType {
            return when (routeColor) {
                RED -> TrainCarCardType.RED
                WHITE -> TrainCarCardType.WHITE
                BLACK -> TrainCarCardType.BLACK
                GREEN -> TrainCarCardType.GREEN
                YELLOW -> TrainCarCardType.YELLOW
                PURPLE -> TrainCarCardType.PURPLE
                ORANGE -> TrainCarCardType.ORANGE
                BLUE -> TrainCarCardType.BLUE
                GRAY -> TrainCarCardType.NONE
            }
        }
    }

    override fun toString(): String {
        return when(this) {
            RED -> "#B82044"
            WHITE -> "#EDECEA"
            BLACK -> "#5E6168"
            GREEN -> "#93BC53"
            YELLOW -> "#E6EE58"
            PURPLE -> "#CC88AB"
            ORANGE -> "#D49543"
            GRAY -> "#B6B5B3"
            BLUE -> "#48A3CF"
        }
    }


}