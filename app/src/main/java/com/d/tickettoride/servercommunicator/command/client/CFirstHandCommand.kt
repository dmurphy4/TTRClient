package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.model.gameplay.TrainCarCardHand
import com.d.tickettoride.service.TrainCardService

class CFirstHandCommand(private val hand:TrainCarCardHand) :
    ICommand {

    override fun execute() {
        TrainCardService.instance.getFirstHand(hand)
    }
}