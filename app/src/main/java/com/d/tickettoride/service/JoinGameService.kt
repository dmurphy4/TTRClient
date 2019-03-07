package com.d.tickettoride.service

import com.d.tickettoride.command.server.ServerCommand
import com.d.tickettoride.model.Game
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.servercommunicator.ServerProxy

class JoinGameService {

    companion object {
        val instance = JoinGameService()
    }

    fun joinGame(gameName:String) {
        ServerProxy().command(ServerCommand.JoinGame(gameName, RootModel.instance.user!!.username))
    }

    fun setJoinedGame() {
        RootModel.instance.waitingForGame = true
    }

    fun setGameData(game: Game) {
        RootModel.instance.game = game
    }
}