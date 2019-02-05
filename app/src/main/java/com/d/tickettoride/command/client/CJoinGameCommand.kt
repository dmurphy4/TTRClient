package com.d.tickettoride.command.client

import com.d.tickettoride.service.ErrorMessageService

class CJoinGameCommand (val errorMessage:String?, val success:Boolean, val playerColor:String, val userName:String) : ICommand {

    override fun execute() {
        if (success) {

        }
        else {
            ErrorMessageService().postErrorMessage(errorMessage!!)
        }
    }
}