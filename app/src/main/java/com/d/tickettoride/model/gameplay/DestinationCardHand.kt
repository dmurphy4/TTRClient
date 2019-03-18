package com.d.tickettoride.model.gameplay

class DestinationCardHand(var cards:ArrayList<DestinationCard>) : IHand {

    fun extend(cardList:ArrayList<DestinationCard>) {
        cards.addAll(cardList)
    }

    override fun draw(cardsDrawn: List<ICard>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playCards() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}