package com.d.tickettoride.presenters.states

import com.d.tickettoride.presenters.ipresenters.IGamePresenter

class DrewTrainCardState : Statelike() {

    override fun drawFaceUp(gamePresenter: IGamePresenter) {





        gamePresenter.setState(NotYourTurnState())
    }

    override fun drawFromDrawpile(gamePresenter: IGamePresenter) {





        gamePresenter.setState(NotYourTurnState())
    }
}