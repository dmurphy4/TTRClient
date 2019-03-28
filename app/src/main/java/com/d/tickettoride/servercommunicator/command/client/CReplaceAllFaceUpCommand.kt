package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.model.gameplay.TrainCarCard
import com.d.tickettoride.service.TrainCardService

class CReplaceAllFaceUpCommand(private val faceUpCards: ArrayList<TrainCarCard>) : ICommand {

    override fun execute() {
        TrainCardService.instance.replaceAllFaceUp(faceUpCards)
    }
}