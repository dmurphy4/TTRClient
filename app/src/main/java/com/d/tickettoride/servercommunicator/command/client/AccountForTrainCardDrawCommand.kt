package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.service.TrainCardService

class AccountForTrainCardDrawCommand(private val deckSize: Int): ICommand {
    override fun execute() {
        TrainCardService.instance.changeDeckSize(deckSize)
    }
}