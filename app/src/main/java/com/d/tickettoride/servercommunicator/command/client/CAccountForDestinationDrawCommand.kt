package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.service.DestCardService

class CAccountForDestinationDrawCommand(val deckSize: Int) : ICommand {

    override fun execute() {
        DestCardService.instance.changeNumDestinationCards(deckSize)
    }
}