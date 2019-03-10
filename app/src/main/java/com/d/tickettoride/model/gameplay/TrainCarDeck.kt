package com.d.tickettoride.model.gameplay

class TrainCarDeck(var drawPile:ArrayList<TrainCarCard>, var discardPile:ArrayList<TrainCarCard>,
                   var faceUpCards:ArrayList<TrainCarCard>) : IDeck {

    init {
        discardPile = ArrayList()
    }

    fun discard(cards: List<TrainCarCard>) {
        discardPile.addAll(cards)
    }

    override fun draw(): List<ICard> {
        val returner = ArrayList<TrainCarCard>()
        returner.add(drawPile[0])
        drawPile.drop(1)
        return returner
    }

    fun faceUpCardsReady() {
        faceUpCards = ArrayList(drawPile.take(5))
        drawPile = ArrayList(drawPile.drop(5))
    }
}