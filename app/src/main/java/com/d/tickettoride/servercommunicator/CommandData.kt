package com.d.tickettoride.servercommunicator

import com.d.tickettoride.servercommunicator.command.CommandType
import com.d.tickettoride.servercommunicator.command.client.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class CommandData(var type:String, var data:String) {

    fun execute() {
        when (CommandType.valueOf(type)) {
            CommandType.C_CREATE_GAME -> Gson().fromJson(data, CCreateGameCommand::class.java).execute()
            CommandType.C_BEGIN_PLAY -> Gson().fromJson(data, CBeginPlayCommand::class.java).execute()
            CommandType.C_REMOVE_GAME -> Gson().fromJson(data, CRemoveGameCommand::class.java).execute()
            CommandType.C_DEST_CARD -> Gson().fromJson(data, CChooseDestCardCommand::class.java).execute()
            CommandType.C_FIRST_HAND -> Gson().fromJson(data, CFirstHandCommand::class.java).execute()
            CommandType.C_EVENT -> Gson().fromJson(data, CChatCommand::class.java).execute()

            else -> println("Leilani Fonbuena")
        }

    }
}