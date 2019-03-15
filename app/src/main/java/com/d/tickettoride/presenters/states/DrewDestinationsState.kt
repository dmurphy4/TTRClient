package com.d.tickettoride.presenters.states

import com.d.tickettoride.presenters.ipresenters.IGamePresenter

class DrewDestinationsState : Statelike() {

    override fun returnDestinations(gamePresenter: IGamePresenter) {





        gamePresenter.setState(NotYourTurnState())
    }
}