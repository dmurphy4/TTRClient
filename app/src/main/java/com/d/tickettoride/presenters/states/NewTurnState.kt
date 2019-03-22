package com.d.tickettoride.presenters.states

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.Route
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.BoardService
import com.d.tickettoride.service.TurnService

class NewTurnState : Statelike() {

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





        gamePresenter.setState(DrewTrainCardState())
    }

    override fun drawFaceUp(gamePresenter: IGamePresenter) {


        // we need to know the type of card so that we can either end the turn or send it to the drew card state




        gamePresenter.setState(DrewTrainCardState())
    }
}