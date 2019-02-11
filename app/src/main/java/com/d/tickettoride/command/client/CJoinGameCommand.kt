package com.d.tickettoride.command.client

import com.d.tickettoride.service.ErrorMessageService
import com.d.tickettoride.service.JoinGameService

class CJoinGameCommand (private val errorMessage:String?) : ICommand {

    override fun execute() {
        if (errorMessage == null) {
            JoinGameService.instance.setJoinedGame(true)
        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
    }
}