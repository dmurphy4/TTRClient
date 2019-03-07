package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.model.gameplay.DestinationCard
import com.d.tickettoride.service.DestCardService

class CChooseDestCardCommand(private val destinationCards:List<DestinationCard>) :
    ICommand {

    override fun execute() {
        DestCardService.instance.postFirstDestCards(destinationCards)
    }
}