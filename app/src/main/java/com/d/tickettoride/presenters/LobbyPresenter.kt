package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.ipresenters.ILobbyPresenter
import com.d.tickettoride.views.iviews.ILobbyView

class LobbyPresenter(private val lobbyActivity: ILobbyView) : ILobbyPresenter {

    init {
        RootModel.instance.onGameBoolTrue = { _, _ ->
            lobbyActivity.displayErrorMessage("Game Started. Good luck.")
            lobbyActivity.startGame()
        }
    }

    override fun postGameStarted() {

    }
}