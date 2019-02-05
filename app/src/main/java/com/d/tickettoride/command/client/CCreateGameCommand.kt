package com.d.tickettoride.command.client

import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.service.CreateGameService
import com.d.tickettoride.service.ErrorMessageService

class CCreateGameCommand (private val errorMessage:String?, private val success:Boolean, private val gameInfo: GameInfo)
    : ICommand {

    override fun execute() {
        if (success) {
            CreateGameService().addGameToList(gameInfo)
        }
        else {
            ErrorMessageService().postErrorMessage(errorMessage!!)
        }
    }
}