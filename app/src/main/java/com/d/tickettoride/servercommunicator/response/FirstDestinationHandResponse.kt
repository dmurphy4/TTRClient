package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.model.gameplay.DestinationCardHand
import com.d.tickettoride.service.DestCardService
import com.d.tickettoride.service.ErrorMessageService

class FirstDestinationHandResponse(var hand: DestinationCardHand?) : GenericResponse() {

    override fun execute() {
        if (errorMessage != null) {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
        else {
            DestCardService.instance.getFirstDestCardHand(hand!!)
        }
    }
}