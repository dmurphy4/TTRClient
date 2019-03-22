package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.model.gameplay.TrainCarCard
import com.d.tickettoride.service.ErrorMessageService
import com.d.tickettoride.service.TrainCardService

class DrawTrainCarCardResponse(private val card: TrainCarCard): GenericResponse() {

    override fun execute() {
        if (errorMessage == null) {
            TrainCardService.instance.addCardToUserHand(card)
        } else {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
    }
}