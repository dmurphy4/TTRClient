package com.d.tickettoride.model.gameplay

class DestinationDeck (var cards:ArrayList<DestinationCard>) : IDeck {

    override fun draw() : ICard {
        return cards[0] // empty for now
    }

    fun discard(cards:List<ICard>) {

    }
}