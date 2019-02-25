package com.d.tickettoride.presenters.ipresenters

import com.d.tickettoride.model.GameInfo

interface IChooseGamePresenter {
    fun createNewGame(gameInfo: GameInfo)
    fun joinExistingGame(gameInfo: GameInfo)
    fun startPoller()
    fun getAvailableGamesList() : ArrayList<GameInfo>
}