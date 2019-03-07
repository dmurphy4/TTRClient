package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.service.ChooseGameService
import com.d.tickettoride.service.ErrorMessageService

class CCreateGameCommand (private val gameInfo: GameInfo) :
    ICommand {

    override fun execute() {
        ChooseGameService.instance.addGameToList(gameInfo)
    }
}