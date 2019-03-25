package com.d.tickettoride.presenters.states

import com.d.tickettoride.model.gameplay.TrainCarCardType
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.Route
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.BoardService
import com.d.tickettoride.service.TrainCardService
import com.d.tickettoride.service.TurnService

class NewTurnState : Statelike() {

    private val trainCardService = TrainCardService.instance

    override fun drawDestinations(gamePresenter: IGamePresenter) {
        BoardService.instance.drawDestinationCards()
        gamePresenter.setState(DrewDestinationsState())
    }

    override fun claimRoute(gamePresenter: IGamePresenter, id:Int) {
        val route: Route = RootModel.instance.game!!.board.routes.getValue(id)

        if (RootModel.instance.user!!.canClaimRoute(route)) {
            BoardService.instance.claimRoute(id)
            TurnService.instance.endTurn()
            gamePresenter.setState(NotYourTurnState())
        }
        else {
            gamePresenter.postErrorMessage("Sorry, you don't have the cards to claim this route.")
        }
    }

    override fun drawFromDrawpile(gamePresenter: IGamePresenter) {
        trainCardService.drawFromDeck()
        gamePresenter.setState(DrewTrainCardState())
    }

    override fun drawFaceUp(idx: Int, gamePresenter: IGamePresenter) {
        val previousType = trainCardService.getFaceUpCardType(idx)
        trainCardService.takeFaceUpCard(idx)
        if (previousType == TrainCarCardType.LOCOMOTIVE) {
            TurnService.instance.endTurn()
            gamePresenter.setState(NotYourTurnState())
        } else {
            gamePresenter.setState(DrewTrainCardState())
        }
    }
}