package com.d.tickettoride.presenters.states

import com.d.tickettoride.model.gameplay.TrainCarCardType
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.TrainCardService
import com.d.tickettoride.service.TurnService

class DrewTrainCardState : Statelike() {

    private val trainCardService = TrainCardService.instance

    override fun drawFaceUp(idx: Int, gamePresenter: IGamePresenter) {
        if (trainCardService.getFaceUpCardType(idx) == TrainCarCardType.LOCOMOTIVE) {
            gamePresenter.postErrorMessage("You can't draw a locomotive as your second card")
        } else {
            trainCardService.takeFaceUpCard(idx)
            TurnService.instance.endTurn()
            gamePresenter.setState(NotYourTurnState())
        }
    }

    override fun drawFromDrawpile(gamePresenter: IGamePresenter) {
        TurnService.instance.endTurn()
        gamePresenter.setState(NotYourTurnState())
    }
}