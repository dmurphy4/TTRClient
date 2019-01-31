package com.d.tickettoride.command.client

import com.d.tickettoride.service.LoginService

class CLoginRegisterCommand (private val errorMessage:String?, private val success:Boolean) : ICommand {

    override fun execute() {
        LoginService().loginUser(success, errorMessage)
    }
}