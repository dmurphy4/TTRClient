package com.d.tickettoride.presenters.states

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.TrainCarCardType
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.BoardService
import com.d.tickettoride.service.TurnService

class ClaimingGrayRouteState : Statelike() {

    override fun chooseNotToClaimGrayRoute(gamePresenter: IGamePresenter) {
        gamePresenter.setState(NewTurnState())
    }

    override fun chooseColorForGrayRoute(gamePresenter: IGamePresenter, id: Int, chosenColor: TrainCarCardType) {
        val route = RootModel.instance.game!!.board.routes.getValue(id)

        if (route.companionId == -1) {
            if (RootModel.instance.user!!.canClaimRoute(route.numTracks, chosenColor)) {
                BoardService.instance.claimGrayRoute(id, chosenColor)
                TurnService.instance.endTurn()
                gamePresenter.setState(NotYourTurnState())
            } else {
                gamePresenter.postErrorMessage("Sorry, you can't use this color to claim this gray route.")
            }
        }
        else {
            if (RootModel.instance.game!!.board.routes[route.companionId]!!.owner != RootModel.instance.user!!.username &&
                RootModel.instance.game!!.playerStats.size > 3) {
                BoardService.instance.claimGrayRoute(id, chosenColor)
                TurnService.instance.endTurn()
                gamePresenter.setState(NotYourTurnState())
            }
            else {
                gamePresenter.postErrorMessage("Sorry, you can't claim this route.")
            }
        }
    }
}