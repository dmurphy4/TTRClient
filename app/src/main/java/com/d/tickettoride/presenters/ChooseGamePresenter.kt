package com.d.tickettoride.presenters

import com.d.tickettoride.model.GameInfo
import com.d.tickettoride.views.IChooseGameView

class ChooseGamePresenter(chooseGameView: IChooseGameView) : IChooseGamePresenter {

    private val chooseGameActivity = chooseGameView

    override fun createNewGame(gameInfo: GameInfo) {
        chooseGameActivity.displayGameInList(gameInfo)
    }

    override fun joinExistingGame(gameInfo: GameInfo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setSelectedGameInfo(gameInfo: GameInfo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}