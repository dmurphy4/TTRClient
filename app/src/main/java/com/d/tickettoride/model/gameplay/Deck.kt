package com.d.tickettoride.model.gameplay

class Deck(private val drawPile: List<Card>, private val discard:Set<Card>) {

    fun drawCard() : Card {
        return drawPile[0]
    }


}