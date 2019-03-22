package com.d.tickettoride.service

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.RouteColor
import com.d.tickettoride.model.gameplay.TrainCarCardHand
import com.d.tickettoride.model.gameplay.TrainCarCardType

class TrainCardService {

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
        RootModel.instance.game!!.board.trainDeck.draw()
    }

    fun drawPileSize(): Int {
        return RootModel.instance.game!!.board.trainDeck.drawPile.size
    }

    fun takeFaceUpCard(idx: Int) {
        RootModel.instance.game!!.board.trainDeck.replaceFaceUpCard(idx)
    }

    fun setFaceUpChangedListener(callback: ((index: Int, type: TrainCarCardType) -> Unit)) {
        RootModel.instance.game!!.board.trainDeck.onFaceUpChanged = callback
    }

    fun postClaimDecrease(id: Int) {
        val route = RootModel.instance.game!!.board.routes.getValue(id)
        val color = route.color
        val numCardsToDecrease = route.numTracks

        val cardColor = RouteColor.getCardColor(color)
    }
}