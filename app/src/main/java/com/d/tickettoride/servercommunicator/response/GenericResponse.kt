package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.service.ErrorMessageService

open class GenericResponse(val errorMessage:String? = null) {

    open fun execute() {
        if (errorMessage != null) {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
    }
}