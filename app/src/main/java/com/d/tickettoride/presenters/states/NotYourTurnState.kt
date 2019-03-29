package com.d.tickettoride.presenters.states

import com.d.tickettoride.presenters.ipresenters.IGamePresenter

class NotYourTurnState : Statelike() {

    override fun beginTurn(gamePresenter: IGamePresenter, lastTurn:Boolean) {
        if (lastTurn) {
            gamePresenter.setState(LastTurnState())
        }
        else {
            gamePresenter.setState(NewTurnState())
        }
    }
}