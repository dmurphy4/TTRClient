package com.d.tickettoride.service

import com.d.tickettoride.command.server.SBeginPlayCommand
import com.d.tickettoride.model.Game
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.servercommunicator.ServerProxy
import org.jetbrains.anko.doAsync

class BeginPlayService {

    fun beginPlay(gameName:String) {
        doAsync {
            val serverCommand = SBeginPlayCommand(gameName)
            ServerProxy().beginPlay(serverCommand)
        }
    }

    fun setCurrentGame(game:Game) {
        var rootModel = RootModel.instance
        rootModel.thisGame = game
    }
}