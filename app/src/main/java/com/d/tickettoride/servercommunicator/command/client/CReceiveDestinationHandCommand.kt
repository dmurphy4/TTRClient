package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.model.gameplay.DestinationCardHand
import com.d.tickettoride.servercommunicator.command.client.ICommand
import com.d.tickettoride.service.DestCardService

class CReceiveDestinationHandCommand(var hand:DestinationCardHand) :
    ICommand {

    override fun execute() {
        DestCardService.instance.getFirstDestCardHand(hand)
    }
}