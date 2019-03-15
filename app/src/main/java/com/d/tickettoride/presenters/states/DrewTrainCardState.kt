package com.d.tickettoride.presenters.states

import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.TurnService

class DrewTrainCardState : Statelike() {

    override fun drawFaceUp(gamePresenter: IGamePresenter) {




        TurnService.instance.endTurn()
        gamePresenter.setState(NotYourTurnState())
    }

    override fun drawFromDrawpile(gamePresenter: IGamePresenter) {



        TurnService.instance.endTurn()
        gamePresenter.setState(NotYourTurnState())
    }
}