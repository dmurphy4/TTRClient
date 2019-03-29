package com.d.tickettoride.presenters.states

import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.TurnService

class NotYourTurnState : Statelike() {

    init {
        TurnService.instance.endTurn()
    }

    override fun beginTurn(gamePresenter: IGamePresenter, lastTurn:Boolean) {
        if (lastTurn) {
            gamePresenter.setState(LastTurnState())
        }
        else {
            gamePresenter.setState(NewTurnState())
        }
    }
}