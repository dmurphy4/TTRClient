package com.d.tickettoride.service

import com.d.tickettoride.command.server.SBeginPlayCommand
import com.d.tickettoride.model.Game
import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.servercommunicator.ServerProxy
import org.jetbrains.anko.doAsync

class BeginPlayService {

    companion object {
        val instance = BeginPlayService()
    }

    fun setCurrentGame(game:Game) {
        var rootModel = RootModel.instance
        rootModel.thisGame = game
    }
}