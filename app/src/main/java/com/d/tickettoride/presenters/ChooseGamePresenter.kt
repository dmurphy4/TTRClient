package com.d.tickettoride.presenters

import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.ICard
import com.d.tickettoride.presenters.ipresenters.IChooseGamePresenter

import com.d.tickettoride.service.ChooseGameService
import com.d.tickettoride.views.iviews.IChooseGameView

class ChooseGamePresenter(private val chooseGameActivity: IChooseGameView,
                          private val chooseGameService: ChooseGameService = ChooseGameService.instance)
    : IChooseGamePresenter {

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
        // The user in the root model shouldn't be null at this point, so use !!.
        chooseGameService.createGame(gameInfo.gameName, gameInfo.numPlayers, rootModel.user!!.username)
    }

    override fun getAvailableGamesList() : ArrayList<GameInfo> {
        return RootModel.instance.gameList
    }

    override fun joinExistingGame(gameInfo: GameInfo) {
        RootModel.instance.onGameJoined = { _,_ ->
            chooseGameActivity.startLobbyActivity()
        }
        chooseGameService.joinGame(gameInfo.gameName)
    }

    override fun startPoller() {
        ChooseGameService.instance.startPoller()
    }

}