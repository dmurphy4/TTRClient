package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.service.ErrorMessageService
import com.d.tickettoride.service.LoginService

class LoginRegisterResponse (private val username:String?) :
    GenericResponse() {

    override fun execute() {
        if (errorMessage == null) {
            LoginService.instance.loginUser(username!!)
        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage!!)
        }
    }
}