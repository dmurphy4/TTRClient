package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.model.gameplay.DestinationCard
import com.d.tickettoride.service.DestCardService

class ReceiveMoreDestinationsResponse(var cards: ArrayList<DestinationCard>) : GenericResponse() {

    override fun execute() {
        DestCardService.instance.addMoreDestinationCards(cards)
    }
}