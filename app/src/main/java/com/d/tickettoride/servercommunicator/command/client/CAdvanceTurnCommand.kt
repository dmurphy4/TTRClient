package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.service.TurnService

class CAdvanceTurnCommand(val username: String, val lastTurn: Boolean) : ICommand {

    override fun execute() {
        TurnService.instance.updateTurn()
    }
}