package com.d.tickettoride.service

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.RouteColor
import com.d.tickettoride.model.gameplay.TrainCarCard
import com.d.tickettoride.model.gameplay.TrainCarCardType
import com.d.tickettoride.servercommunicator.command.server.ServerCommand
import com.d.tickettoride.model.gameplay.TrainCarCardHand
import com.d.tickettoride.servercommunicator.ServerProxy

class TrainCardService(val proxy: ServerProxy = ServerProxy()) {

    companion object {
        val instance = TrainCardService()
    }

    fun getFirstHand(hand:TrainCarCardHand) {
        RootModel.instance.user!!.trainCardHand = hand
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

    fun replaceFaceUpCard(idx: Int, card: TrainCarCard) {
        RootModel.instance.game!!.trainCarDeck.replaceFaceUpCard(idx, card)
    }

    fun changeDeckSize(deckSize: Int) {
        RootModel.instance.game!!.trainCarDeck.changeDeckSize(deckSize)
    }

    fun addCardToUserHand(card: TrainCarCard) {
        RootModel.instance.user!!.trainCardHand.changeCardCount(card.type, 1)
    }

    fun replaceAllFaceUp(cards: ArrayList<TrainCarCard>) {
        RootModel.instance.game!!.trainCarDeck.replaceAllFaceUp(cards)
    }

    fun removeCardFromUserHand(card: TrainCarCard) {
        RootModel.instance.user!!.trainCardHand.changeCardCount(card.type, -1)
    }

    fun setFaceUpChangedListener(callback: ((index: Int, type: TrainCarCardType) -> Unit)) {
        RootModel.instance.game!!.trainCarDeck.onFaceUpChanged = callback
    }

    fun setDeckSizeChangedListener(callback: ((size: Int) -> Unit)) {
        RootModel.instance.game!!.trainCarDeck.onDeckSizeChanged = callback
    }

    fun postClaimDecrease(id: Int) {
        val route = RootModel.instance.game!!.board.routes.getValue(id)
        val color = route.color
        val numCardsToDecrease = route.numTracks

        val cardColor = RouteColor.getCardColor(color)
    }
}