package com.d.tickettoride.service

import com.d.tickettoride.command.server.SCreateGameCommand
import com.d.tickettoride.command.server.SJoinGameCommand
import com.d.tickettoride.model.Game
import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.servercommunicator.CommandType
import com.d.tickettoride.servercommunicator.Poller
import com.d.tickettoride.servercommunicator.ServerProxy
import com.google.gson.Gson

class ChooseGameService(private val proxy: ServerProxy = ServerProxy()) {

    companion object {
        val instance = ChooseGameService()
    }

    fun createGame(gameName:String, numPlayers:Int, creator:String) {
        val data = Gson().toJson(SCreateGameCommand(gameName, numPlayers, creator))
        proxy.command(CommandType.S_CREATE_GAME, data)
    }

    fun addGameToList(gameInfo: GameInfo) {
        val rootModel = RootModel.instance
        rootModel.gameList.add(gameInfo)
        rootModel.gameListLength++
    }

    fun removeGameFromList(gameInfo:GameInfo) {
        val rootModel = RootModel.instance
        rootModel.gameToRemoveFromList = gameInfo
        rootModel.gameList.remove(gameInfo)
        rootModel.gameListLength--
    }

    fun joinGame(gameName:String) {
        val data = Gson().toJson(SJoinGameCommand(gameName, RootModel.instance.user!!.username))
        ServerProxy().command(CommandType.S_JOIN_GAME, data)
    }

    fun setJoinedGame(joined: Boolean) {
        RootModel.instance.waitingForGame = joined
    }

    fun setGameData(game: Game) {
        RootModel.instance.game = game
    }

    fun startPoller() {
        RootModel.instance.poller = Poller()
    }
}