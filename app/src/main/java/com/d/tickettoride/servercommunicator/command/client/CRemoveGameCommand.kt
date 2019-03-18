package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.service.ChooseGameService

class CRemoveGameCommand(private val gameInfo: GameInfo) :
    ICommand {

    override fun execute() {
        ChooseGameService.instance.removeGameFromList(gameInfo)
    }
}