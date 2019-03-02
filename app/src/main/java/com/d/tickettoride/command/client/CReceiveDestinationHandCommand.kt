package com.d.tickettoride.command.client

import com.d.tickettoride.model.gameplay.DestinationCardHand
import com.d.tickettoride.service.DestCardService

class CReceiveDestinationHandCommand(var hand:DestinationCardHand) : ICommand {

    override fun execute() {
        DestCardService.instance.getFirstDestCardHand(hand)
    }
}