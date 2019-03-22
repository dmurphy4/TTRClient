package com.d.tickettoride.service

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.TrainCarCard
import com.d.tickettoride.model.gameplay.TrainCarCardHand
import com.d.tickettoride.model.gameplay.TrainCarCardType
import com.d.tickettoride.servercommunicator.ServerProxy
import com.d.tickettoride.servercommunicator.command.server.ServerCommand

class TrainCardService(val proxy: ServerProxy = ServerProxy()) {

    companion object {
        val instance = TrainCardService()
    }

    fun getFirstHand(hand:TrainCarCardHand) {
        RootModel.instance.user!!.trainCardHand = hand
        for (playerStats in RootModel.instance.game!!.playerStats) {
            if (playerStats.username == RootModel.instance.user!!.username) {
                playerStats.numTrainCards = hand.cards.size
                RootModel.instance.game!!.statsChanged = true
                break
            }
        }
    }

    fun drawFromDeck() {
        proxy.command(ServerCommand.DrawTrainCarCard(RootModel.instance.user!!.username))
    }

    fun drawPileSize(): Int {
        return RootModel.instance.game!!.trainCarDeck.deckSize
    }

    fun getFaceUpCardType(idx: Int): TrainCarCardType {
        return RootModel.instance.game!!.trainCarDeck.getFaceUpCardType(idx)
    }

    fun takeFaceUpCard(idx: Int) {
        proxy.command(ServerCommand.DrawFaceUp(idx, RootModel.instance.user!!.username))
    }

    fun addCardToUserHand(card: TrainCarCard) {
        RootModel.instance.user!!.trainCardHand.changeCardCount(card.type, 1)
    }

    fun removeCardFromUserHand(card: TrainCarCard) {
        RootModel.instance.user!!.trainCardHand.changeCardCount(card.type, -1)
    }

    fun setFaceUpChangedListener(callback: ((index: Int, type: TrainCarCardType) -> Unit)) {
        RootModel.instance.game!!.trainCarDeck.onFaceUpChanged = callback
    }
}