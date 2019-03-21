package com.d.tickettoride.model.gameplay

class TrainCarDeck(var drawPile:MutableList<TrainCarCard>, var discardPile:MutableList<TrainCarCard>?) : IDeck {

    var onFaceUpChanged: ((Int, TrainCarCardType) -> Unit)? = null
    var onFaceUpSet: ((MutableList<TrainCarCard>) -> Unit)? = null
    private lateinit var faceUpCards: MutableList<TrainCarCard>

    override fun draw(): ICard {
        return drawPile.removeAt(0)
    }

    fun faceUpCardsReady() {
        faceUpCards = drawPile.take(5).toMutableList()
        drawPile = drawPile.drop(5).toMutableList()
        drawPile.shuffle()
        onFaceUpSet?.invoke(faceUpCards)
    }

    fun replaceFaceUpCard(idx: Int) {
        faceUpCards[idx] = drawPile.removeAt(0)
        onFaceUpChanged?.invoke(idx, faceUpCards[idx].type)
    }

    fun getFaceUpCardType(idx: Int): TrainCarCardType {
        return faceUpCards[idx].type
    }
}