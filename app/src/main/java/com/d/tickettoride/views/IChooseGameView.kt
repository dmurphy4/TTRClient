package com.d.tickettoride.views

import com.d.tickettoride.model.GameInfo

interface IChooseGameView {
    fun startLobbyActivity()
    fun displayGameInList()
    fun removeGameFromList(gameInfo: GameInfo?)
    fun enableJoinGameButton(enable: Boolean)
}