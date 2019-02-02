package com.d.tickettoride.presenters

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
        var rootModel = RootModel.instance
        rootModel.onGameListChanged = { _, _ ->
            print("hi")
        }
    }

    override fun createNewGame(gameInfo: GameInfo) {
        var rootModel = RootModel.instance
        CreateGameService().createGame(gameInfo.name, gameInfo.numPlayers, rootModel.user?.userName)
        rootModel.gameList.add(gameInfo)
        chooseGameActivity.displayGameInList(gameInfo)
    }

    override fun joinExistingGame(gameInfo: GameInfo) {
        joinGameService.joinGame(gameInfo.name)
    }

    override fun setSelectedGameInfo(gameInfo: GameInfo) {
        return
    }
}