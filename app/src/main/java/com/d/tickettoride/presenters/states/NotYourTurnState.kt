package com.d.tickettoride.presenters.states

import com.d.tickettoride.presenters.ipresenters.IGamePresenter

class NotYourTurnState : Statelike() {

    override fun beginTurn(gamePresenter: IGamePresenter, lastTurn:Boolean) {
        gamePresenter.postErrorMessage("Hurry! It's your turn!")
        if (lastTurn) {
            gamePresenter.setState(LastTurnState())
        }
        else {
            gamePresenter.setState(NewTurnState())
        }
    }
}