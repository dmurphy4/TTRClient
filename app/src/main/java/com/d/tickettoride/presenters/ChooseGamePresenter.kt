package com.d.tickettoride.presenters

import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.service.CreateGameService
import com.d.tickettoride.service.JoinGameService
import com.d.tickettoride.views.IChooseGameView

class ChooseGamePresenter(private val chooseGameActivity: IChooseGameView,
                          private val createGameService: CreateGameService = CreateGameService.instance,
                          private val joinGameService: JoinGameService = JoinGameService.instance) : IChooseGamePresenter {

    init {
        val rootModel = RootModel.instance
        rootModel.onGameListChanged = { old, new ->
            if (old < new) { // a game was added
                chooseGameActivity.displayGameInList()
            }
            else { // a game was removed
                chooseGameActivity.removeGameFromList(rootModel.gameToRemoveFromList)
            }
        }
    }

    override fun createNewGame(gameInfo: GameInfo) {
        val rootModel = RootModel.instance
        createGameService.createGame(gameInfo.gameName, gameInfo.numPlayers, rootModel.user?.userName)
        //createGameService.addGameToList(gameInfo) // where to put this so it depends on the response from the server
    }

    override fun getAvailableGamesList() : ArrayList<GameInfo> {
        return RootModel.instance.gameList
    }

    override fun joinExistingGame(gameInfo: GameInfo) {
        RootModel.instance.onGameJoined = { _,_ ->
            chooseGameActivity.startLobbyActivity()
        }
        joinGameService.joinGame(gameInfo.gameName)
    }

    override fun startPoller() {
        CreateGameService.instance.startPoller()
    }
}