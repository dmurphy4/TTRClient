package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.service.ErrorMessageService

open class GenericResponse(private val errorMessage:String?) {

    open fun execute() {
        if (errorMessage != null) {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
    }
}