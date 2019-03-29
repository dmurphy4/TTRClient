package com.d.tickettoride.presenters.states

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.Route
import com.d.tickettoride.model.gameplay.RouteColor
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.BoardService
import com.d.tickettoride.service.TurnService

class LastTurnState : Statelike() {

    override fun claimRoute(gamePresenter: IGamePresenter, id: Int) {
        val route: Route = RootModel.instance.game!!.board.routes.getValue(id)

        if (RootModel.instance.user!!.canClaimRoute(route.numTracks, RouteColor.getCardColor(route.color))) {
            BoardService.instance.claimRoute(id)
            TurnService.instance.endTurn()
            gamePresenter.setState(NotYourTurnState())
        }
        else {
            gamePresenter.postErrorMessage("Sorry, you don't have the cards to claim this route.")
        }
    }

    override fun drawDestinations(gamePresenter: IGamePresenter) {
        BoardService.instance.drawDestinationCards()
        gamePresenter.setState(DrewDestinationsState())
    }
}