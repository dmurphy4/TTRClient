package com.d.tickettoride.presenters.states

import com.d.tickettoride.presenters.ipresenters.IGamePresenter

class NotYourTurnState : Statelike() {

    override fun beginTurn(gamePresenter: IGamePresenter) {



        gamePresenter.setState(NewTurnState())
    }
}