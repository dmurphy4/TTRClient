package com.d.tickettoride.model.gameplay

class TrainCarDeck(var drawPile:List<TrainCarCard>, var discardPile:MutableList<TrainCarCard>?, var faceUpCards:List<TrainCarCard>?) : IDeck {

    init {
        discardPile = ArrayList()
    }

    fun discard(cards: List<TrainCarCard>) {
        discardPile!!.addAll(cards)
    }

    override fun draw(): List<ICard> {
        val returner = ArrayList<TrainCarCard>()
        returner.add(drawPile[0])
        drawPile.drop(1)
        return returner
    }

    fun faceUpCardsReady() {
        faceUpCards = drawPile.take(5)
        drawPile.drop(5)
    }
}