package com.d.tickettoride.views

import com.d.tickettoride.model.GameInfo

interface IChooseGameView {
    fun startLobbyActivity()
    fun displayGameInList(gameInfo: GameInfo)
}