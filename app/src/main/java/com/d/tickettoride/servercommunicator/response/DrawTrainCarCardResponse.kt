package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.model.gameplay.TrainCarCard
import com.d.tickettoride.service.TrainCardService

class DrawTrainCarCardResponse(private val card: TrainCarCard): GenericResponse() {

    override fun execute() {
        TrainCardService.instance.addCardToUserHand(card)
    }
}