package com.d.tickettoride.service

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.DestinationCard
import com.d.tickettoride.model.gameplay.DestinationCardHand

class DestCardService {

    companion object {
        val instance = DestCardService()
    }

    fun postFirstDestCards(cards:ArrayList<DestinationCard>) {
        RootModel.instance.destinationCardsToChoose = cards
        RootModel.instance.destCardsGiven = true
    }

    fun getFirstDestCardHand(hand:DestinationCardHand) {
        RootModel.instance.user!!.destinationHand = hand
        RootModel.instance.game!!.onTurnChanged?.invoke(0, 0)
    }

    fun claimDestCards() {

    }

    fun sendKeptDestCards() {

    }
}