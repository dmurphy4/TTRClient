package com.d.tickettoride.service

import android.provider.DocumentsContract
import com.d.tickettoride.command.server.SCreateGameCommand
import com.d.tickettoride.model.Game
import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.servercommunicator.ServerProxy
import org.jetbrains.anko.doAsync

class CreateGameService {

    fun createGame(gameName:String, numPlayers:Int, creator:String?) {

    }

    fun addGameToList(gameInfo: GameInfo) {
        val rootModel = RootModel.instance
        rootModel.gameList.add(gameInfo)
        val x = rootModel.gameListLength
        rootModel.gameListLength = x + 1
    }
}