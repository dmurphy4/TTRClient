package com.d.tickettoride.presenters

import com.d.tickettoride.model.GameInfo

interface IChooseGamePresenter {
    fun createNewGame(gameInfo: GameInfo)
    fun joinExistingGame(gameInfo: GameInfo)
}