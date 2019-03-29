package com.d.tickettoride.presenters.states

import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.BoardService

class PreGameState : Statelike() {

    override fun claimRoute(gamePresenter: IGamePresenter, id: Int) {
        gamePresenter.postErrorMessage("You need to draw destinations to start!")
    }

    override fun drawFaceUp(idx: Int, gamePresenter: IGamePresenter) {
        gamePresenter.postErrorMessage("You need to draw destinations to start!")
    }

    override fun drawFromDrawpile(gamePresenter: IGamePresenter) {
        gamePresenter.postErrorMessage("You need to draw destinations to start!")
    }

    override fun drawDestinations(gamePresenter: IGamePresenter) {
        BoardService.instance.drawDestinationCards()
        gamePresenter.setState(PreGameDrawingDestinationsState())
    }
}