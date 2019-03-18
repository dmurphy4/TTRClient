package com.d.tickettoride.presenters.states

import com.d.tickettoride.presenters.ipresenters.IGamePresenter

class NewTurnState : Statelike() {

    override fun drawDestinations(gamePresenter: IGamePresenter) {





        gamePresenter.setState(DrewDestinationsState())
    }

    override fun claimRoute(gamePresenter: IGamePresenter) {




        gamePresenter.setState(NotYourTurnState())
    }

    override fun drawFromDrawpile(gamePresenter: IGamePresenter) {





        gamePresenter.setState(DrewTrainCardState())
    }

    override fun drawFaceUp(gamePresenter: IGamePresenter) {


        // we need to know the type of card so that we can either end the turn or send it to the drew card state




        gamePresenter.setState(DrewTrainCardState())
    }
}