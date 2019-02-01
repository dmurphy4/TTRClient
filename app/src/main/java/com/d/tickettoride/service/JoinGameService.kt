package com.d.tickettoride.service

import com.d.tickettoride.command.server.SJoinGameCommand
import com.d.tickettoride.servercommunicator.ServerProxy
import org.jetbrains.anko.doAsync

class JoinGameService {


    fun joinGame(gameName:String, userName:String) {
        doAsync {
            val serverCommand = SJoinGameCommand(gameName, userName)
            ServerProxy().joinGame(serverCommand)
        }
    }
}