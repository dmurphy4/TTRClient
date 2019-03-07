package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.service.ErrorMessageService

class CreateGameResponse(private val errorMessage:String?) : GenericResponse(errorMessage) {

    override fun execute() {
        if (errorMessage != null) {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
    }
}