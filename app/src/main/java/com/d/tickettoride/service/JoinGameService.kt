package com.d.tickettoride.service

import com.d.tickettoride.command.server.SJoinGameCommand
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.servercommunicator.ServerProxy
import org.jetbrains.anko.doAsync

class JoinGameService {


    fun joinGame(gameName:String) {
        val serverCommand = SJoinGameCommand(gameName, RootModel.instance.user!!.userName)
        ServerProxy().joinGame(serverCommand)
    }
}