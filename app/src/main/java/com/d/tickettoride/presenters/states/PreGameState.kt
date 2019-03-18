package com.d.tickettoride.presenters.states

import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.BoardService

class PreGameState : Statelike() {

    override fun drawDestinations(gamePresenter: IGamePresenter) {
        BoardService.instance.drawDestinationCards()
    }

    override fun returnDestinations(gamePresenter: IGamePresenter, indexes: ArrayList<Int>, notChosen: ArrayList<Int>) {
        BoardService.instance.chooseFirstDestinationCards(indexes, notChosen)
    }

    override fun beginPlay(gamePresenter: IGamePresenter) {
        gamePresenter.setState(NotYourTurnState())
    }
}