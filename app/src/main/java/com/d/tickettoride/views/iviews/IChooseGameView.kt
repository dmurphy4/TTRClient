package com.d.tickettoride.views.iviews

import com.d.tickettoride.model.GameInfo

interface IChooseGameView {
    fun startLobbyActivity()
    fun displayGameInList()
    fun removeGameFromList(gameInfo: GameInfo?)
    fun enableJoinGameButton(enable: Boolean)
}