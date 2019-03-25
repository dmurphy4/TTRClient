package com.d.tickettoride.presenters.states

import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.TurnService

class NotYourTurnState : Statelike() {

    init {
        TurnService.instance.endTurn()
    }

    override fun beginTurn(gamePresenter: IGamePresenter) {
        gamePresenter.postErrorMessage("Hurry! It's your turn!")
        gamePresenter.setState(NewTurnState())
    }
}