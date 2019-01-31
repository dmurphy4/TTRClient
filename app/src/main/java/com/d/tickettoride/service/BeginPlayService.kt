package com.d.tickettoride.service

import com.d.tickettoride.command.server.SBeginPlayCommand
import com.d.tickettoride.servercommunicator.ServerProxy

class BeginPlayService {

    fun beginPlay(gameName:String) {
        val serverCommand = SBeginPlayCommand(gameName)
        ServerProxy().beginPlay(serverCommand)
    }

    fun setCurrentGame(gameName:String) { // I don't know what this is for again...

    }
}