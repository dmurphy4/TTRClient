package com.d.tickettoride.model.gameplay

class TrainCarDeck(var faceUpCards: ArrayList<TrainCarCard>, var deckSize: Int) {

    var onFaceUpChanged: ((Int, TrainCarCardType) -> Unit)? = null
    var onDeckSizeChanged: ((Int) -> Unit)? = null

    fun getFaceUpCardType(idx: Int): TrainCarCardType {
        return faceUpCards[idx].type
    }

    fun replaceFaceUpCard(idx: Int, card: TrainCarCard) {
        faceUpCards[idx] = card
        onFaceUpChanged?.invoke(idx, faceUpCards[idx].type)
    }

    fun changeDeckSize(size: Int) {
        deckSize = size
        onDeckSizeChanged?.invoke(deckSize)
    }

    fun replaceAllFaceUp(cards: ArrayList<TrainCarCard>) {
        faceUpCards = cards
        for (ind in 0..4) {
            onFaceUpChanged?.invoke(ind, faceUpCards[ind].type)
        }
    }
}








//fun draw(): ICard {
//    return drawPile.removeAt(0)
//}
//
//fun faceUpCardsReady() {
//    faceUpCards = drawPile.take(5).toMutableList()
//    drawPile = drawPile.drop(5).toMutableList()
//    drawPile.shuffle()
//    onFaceUpSet?.invoke(faceUpCards)
//}
//