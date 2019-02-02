package com.d.tickettoride.service

import com.d.tickettoride.command.server.SCreateGameCommand
import com.d.tickettoride.model.Game
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.servercommunicator.ServerProxy
import org.jetbrains.anko.doAsync

class CreateGameService {

    fun createGame(gameName:String, numPlayers:Int, creator:String?) {

    }

    fun addGameToList(game:Game) {
        RootModel.instance.gameList?.add(game)
    }
}