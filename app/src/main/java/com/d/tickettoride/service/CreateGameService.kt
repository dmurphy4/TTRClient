package com.d.tickettoride.service

import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.RootModel

class CreateGameService {

    fun createGame(gameName:String, numPlayers:Int, creator:String?) {

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
}