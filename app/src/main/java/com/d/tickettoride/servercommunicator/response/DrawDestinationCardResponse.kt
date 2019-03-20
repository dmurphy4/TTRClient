package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.model.gameplay.DestinationCard
import com.d.tickettoride.service.DestCardService
import com.d.tickettoride.service.ErrorMessageService

class DrawDestinationCardResponse(private val destinationCards: ArrayList<DestinationCard>)
    : GenericResponse() {

    override fun execute() {
        if (errorMessage != null) {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
        else {
            DestCardService.instance.postFirstDestCards(destinationCards)
        }
    }
}