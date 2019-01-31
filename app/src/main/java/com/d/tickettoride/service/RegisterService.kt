package com.d.tickettoride.service

import com.d.tickettoride.command.server.SRegisterCommand
import com.d.tickettoride.servercommunicator.ServerProxy

class RegisterService {

    fun register(userName:String, password:String) {
        val serverCommand = SRegisterCommand(userName, password)
        ServerProxy().register(serverCommand)
    }

    fun setUser(userName:String) {

    }
}