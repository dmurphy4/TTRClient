package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.service.ChooseGameService
import com.d.tickettoride.service.ErrorMessageService

class JoinGameResponse :
    GenericResponse() {

    override fun execute() {
        if (errorMessage == null) {
            ChooseGameService.instance.setJoinedGame(true)
        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage!!)
        }
    }
}