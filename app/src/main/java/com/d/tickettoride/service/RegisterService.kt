package com.d.tickettoride.service

import com.d.tickettoride.command.server.SRegisterCommand
import com.d.tickettoride.servercommunicator.ServerProxy
import org.jetbrains.anko.doAsync

class RegisterService {

    fun register(userName:String, password:String) {
        doAsync {
            val serverCommand = SRegisterCommand(userName, password)
            ServerProxy().register(serverCommand)
        }
    }
}