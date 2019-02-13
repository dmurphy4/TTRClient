package com.d.tickettoride.command.client

import com.d.tickettoride.service.ChooseGameService
import com.d.tickettoride.service.ErrorMessageService

class CJoinGameCommand (private val errorMessage:String?) : ICommand {

    override fun execute() {
        if (errorMessage == null) {
            ChooseGameService.instance.setJoinedGame(true)
        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
    }
}