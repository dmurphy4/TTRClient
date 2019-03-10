package com.d.tickettoride.model.gameplay

class TrainCarDeck(var drawPile:MutableList<TrainCarCard>, var discardPile:MutableList<TrainCarCard>?) : IDeck {

    var onFaceUpChanged: ((Int, TrainCarCardType) -> Unit)? = null
    private lateinit var faceUpCards: MutableList<TrainCarCard>

    override fun draw(): List<ICard> {
        val returner = ArrayList<TrainCarCard>()
        returner.add(drawPile[0])
        drawPile.drop(1)
        return returner
    }

    fun faceUpCardsReady() {
        faceUpCards = drawPile.take(5).toMutableList()
        drawPile = drawPile.drop(5).toMutableList()
        drawPile.shuffle()
    }

    fun replaceFaceUpCard(idx: Int) {
        discardPile?.add(faceUpCards[idx])
        faceUpCards[idx] = drawPile.removeAt(0)
        onFaceUpChanged?.invoke(idx, faceUpCards[idx].type)
    }
}