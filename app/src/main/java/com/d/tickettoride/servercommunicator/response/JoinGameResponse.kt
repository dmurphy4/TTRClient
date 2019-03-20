package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.service.ChooseGameService
import com.d.tickettoride.service.ErrorMessageService

class JoinGameResponse(private val playerColor: String) :
    GenericResponse() {

    override fun execute() {
        if (errorMessage == null) {
            ChooseGameService.instance.setJoinedGame(true, playerColor)

        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage!!)
        }
    }
}