package com.d.tickettoride.presenters

import android.util.Log
import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.service.CreateGameService
import com.d.tickettoride.service.JoinGameService
import com.d.tickettoride.views.IChooseGameView

class ChooseGamePresenter(chooseGameView: IChooseGameView) : IChooseGamePresenter {

    private val chooseGameActivity = chooseGameView
    private val createGameService = CreateGameService()
    private val joinGameService = JoinGameService()

    init {
        val rootModel = RootModel.instance
        rootModel.onGameAdded = { _, _ ->
            chooseGameActivity.displayGameInList(rootModel.gameList[rootModel.gameListLength - 1])
        }
    }

    override fun createNewGame(gameInfo: GameInfo) {
        val rootModel = RootModel.instance
        createGameService.createGame(gameInfo.name, gameInfo.numPlayers, rootModel.user?.userName)
        createGameService.addGameToList(gameInfo) // where to put this so it depends on the response from the server
    }

    override fun joinExistingGame(gameInfo: GameInfo) {
        joinGameService.joinGame(gameInfo.name)
    }

    override fun setSelectedGameInfo(gameInfo: GameInfo) {
        return
    }
}