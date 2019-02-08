package com.d.tickettoride.servercommunicator

import com.d.tickettoride.command.client.CBeginPlayCommand
import com.d.tickettoride.command.client.CCreateGameCommand
import com.d.tickettoride.command.client.CRemoveGameCommand
import com.google.gson.Gson

class CommandData(var type:String, var data:String) {

    fun execute(){
        when (CommandType.valueOf(type)) {
            CommandType.C_CREATE_GAME -> Gson().fromJson(data, CCreateGameCommand::class.java).execute()
            CommandType.C_BEGIN_PLAY -> Gson().fromJson(data, CBeginPlayCommand::class.java).execute()
            CommandType.C_REMOVE_GAME -> Gson().fromJson(data, CRemoveGameCommand::class.java).execute()

            else -> println("Leilani Fambuena")
        }

    }
}