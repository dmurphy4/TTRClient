package com.d.tickettoride.command.client

import com.d.tickettoride.service.ErrorMessageService
import com.d.tickettoride.service.JoinGameService

class CJoinGameCommand (private val errorMessage:String?,
                        private val success:Boolean) : ICommand {

    override fun execute() {
        if (success) {
            JoinGameService.instance.setJoinedGame(true)
        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage!!)
        }
    }
}