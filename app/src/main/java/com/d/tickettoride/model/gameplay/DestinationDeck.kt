package com.d.tickettoride.model.gameplay

class DestinationDeck (var cards:ArrayList<DestinationCard>) : IDeck {

    override fun draw() : List<ICard> {

        return ArrayList() // empty for now
    }

    fun discard(cards:List<ICard>) {

    }
}