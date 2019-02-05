package com.d.tickettoride.command.client

import com.d.tickettoride.service.ErrorMessageService
import com.d.tickettoride.service.LoginService

class CLoginRegisterCommand (private val errorMessage:String?, private val success:Boolean, private val username:String) : ICommand {

    override fun execute() {
        if (success) {
            LoginService().loginUser()
        }
        else {
            ErrorMessageService().postErrorMessage(errorMessage!!)
        }
    }
}