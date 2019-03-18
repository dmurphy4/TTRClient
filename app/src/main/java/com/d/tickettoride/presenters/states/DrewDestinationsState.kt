package com.d.tickettoride.presenters.states

import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.BoardService
import com.d.tickettoride.service.TurnService

class DrewDestinationsState : Statelike() {

    override fun returnDestinations(gamePresenter: IGamePresenter, indexes: ArrayList<Int>, notChosen: ArrayList<Int>) {
        BoardService.instance.chooseDestinationCards(indexes, notChosen)

        TurnService.instance.endTurn()
        gamePresenter.setState(NotYourTurnState())
    }
}