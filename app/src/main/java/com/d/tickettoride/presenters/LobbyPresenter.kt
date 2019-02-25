package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.ipresenters.ILobbyPresenter
import com.d.tickettoride.service.LobbyService
import com.d.tickettoride.views.iviews.ILobbyView

class LobbyPresenter(private val lobbyActivity: ILobbyView,
                     private val lobbyService: LobbyService = LobbyService.instance) :
    ILobbyPresenter {

    init {
        RootModel.instance.onGameBoolTrue = { _, new ->
            lobbyActivity.displayErrorMessage("Game Started. Good luck.")
        }
    }

    override fun postGameStarted() {

    }
}