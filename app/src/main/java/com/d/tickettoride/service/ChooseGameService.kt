package com.d.tickettoride.service

import com.d.tickettoride.servercommunicator.command.server.ServerCommand
import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.servercommunicator.Poller
import com.d.tickettoride.servercommunicator.ServerProxy

class ChooseGameService(private val proxy: ServerProxy = ServerProxy()) {

    companion object {
        val instance = ChooseGameService()
    }

    fun createGame(gameName:String, numPlayers:Int, creator:String) {
        proxy.command(ServerCommand.CreateGame(gameName, numPlayers, creator))
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
        proxy.command(ServerCommand.JoinGame(gameName, RootModel.instance.user!!.username))
    }

    fun setJoinedGame(joined: Boolean) {
        RootModel.instance.waitingForGame = joined
    }

    fun startPoller() {
        RootModel.instance.poller = Poller()
    }
}