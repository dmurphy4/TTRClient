package com.d.tickettoride.views.iviews

import com.d.tickettoride.model.GameInfo

interface IChooseGameView {
    fun startLobbyActivity()
    fun displayGameInList(gameInfo: GameInfo)
    fun removeGameFromList(gameInfo: GameInfo?)
}