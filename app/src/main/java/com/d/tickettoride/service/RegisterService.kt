package com.d.tickettoride.service

import com.d.tickettoride.command.server.SRegisterCommand
import com.d.tickettoride.servercommunicator.CommandType
import com.d.tickettoride.servercommunicator.ServerProxy
import com.google.gson.Gson

class RegisterService {

    fun register(userName:String, password:String) {
        val serverCommand = SRegisterCommand(userName, password)
        val gson = Gson()
        ServerProxy().command(CommandType.S_REGISTER, gson.toJson(serverCommand))
    }
}