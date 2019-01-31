package com.d.tickettoride.service

import com.d.tickettoride.command.server.SCreateGameCommand
import com.d.tickettoride.servercommunicator.ServerProxy

class CreateGameService {

    fun createGame(gameName:String, numPlayers:Int, creator:String?) {
        var serverComm = SCreateGameCommand(gameName, numPlayers, creator)
        ServerProxy().createGame(serverComm)
    }

    fun addGameToList() {

    }
}