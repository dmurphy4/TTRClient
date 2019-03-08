package com.d.tickettoride.service

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.TrainCarCard
import com.d.tickettoride.model.gameplay.TrainCarCardHand

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

    fun drawCard() {

    }

    fun buildWithCards(cards:List<TrainCarCard>) {

    }
}