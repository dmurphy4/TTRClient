package com.d.tickettoride.service

import com.d.tickettoride.command.server.SJoinGameCommand
import com.d.tickettoride.servercommunicator.ServerProxy

class JoinGameService {


    fun joinGame(gameName:String, userName:String) {
        val serverCommand = SJoinGameCommand(gameName, userName)
        ServerProxy().joinGame(serverCommand)
    }

    fun addPlayerToGame(userName:String) {

    }
}