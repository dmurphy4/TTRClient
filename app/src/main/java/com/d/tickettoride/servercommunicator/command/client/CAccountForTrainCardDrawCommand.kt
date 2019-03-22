package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.service.TrainCardService

class CAccountForTrainCardDrawCommand(private val deckSize: Int): ICommand {
    override fun execute() {
        TrainCardService.instance.changeDeckSize(deckSize)
    }
}