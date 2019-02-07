package com.d.tickettoride.service

import com.d.tickettoride.command.server.SJoinGameCommand
import com.d.tickettoride.model.Game
import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.Player
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.servercommunicator.CommandType
import com.d.tickettoride.servercommunicator.ServerProxy
import com.google.gson.Gson

class JoinGameService {

    companion object {
        val instance = JoinGameService()
    }

    fun joinGame(gameName:String) {
        val data = Gson().toJson(SJoinGameCommand(gameName, RootModel.instance.user!!.userName))
        ServerProxy().command(CommandType.S_JOIN_GAME, data)
    }

    fun setGameData(gameInfo: GameInfo, player: Player) {
        RootModel.instance.game = Game(gameInfo, player)
    }
}