package com.d.tickettoride.model.gameplay

class Board (var routes:Map<Int, Route>, var cities:Map<Int, City>,
             var destinationDeck:DestinationDeck?, var trainDeck:TrainCarDeck?) {

    fun prepFaceUpCards() {
        trainDeck!!.faceUpCardsReady()
    }
}
