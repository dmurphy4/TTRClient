package com.d.tickettoride.command.client

import com.d.tickettoride.service.ErrorMessageService
import com.d.tickettoride.service.LoginService

class CLoginRegisterCommand (private val errorMessage:String?, private val userName:String?) : ICommand {

    override fun execute() {
        if (errorMessage == null) {
            LoginService.instance.loginUser(userName!!)
        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
    }
}