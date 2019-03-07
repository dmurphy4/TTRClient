package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.service.ChooseGameService
import com.d.tickettoride.service.ErrorMessageService

class JoinGameResponse (private val errorMessage:String?) :
    GenericResponse(errorMessage) {

    override fun execute() {
        if (errorMessage == null) {
            ChooseGameService.instance.setJoinedGame(true)
        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
    }
}