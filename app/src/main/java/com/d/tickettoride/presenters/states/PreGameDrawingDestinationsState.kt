package com.d.tickettoride.presenters.states

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.BoardService

class PreGameDrawingDestinationsState : Statelike() {
    override fun returnDestinations(gamePresenter: IGamePresenter, indexes: ArrayList<Int>, notChosen: ArrayList<Int>) {
        if (indexes.size < 2) {
            gamePresenter.postErrorMessage("Sorry boss, you need to choose 2 or 3")
        }
        else {
            BoardService.instance.chooseFirstDestinationCards(indexes, notChosen)
        }
    }

    override fun beginPlay(gamePresenter: IGamePresenter) {
        if (RootModel.instance.game!!.playerStats[0].username == RootModel.instance.user!!.username) {
            gamePresenter.setState(NewTurnState())
        }
        else {
            gamePresenter.setState(NotYourTurnState())
        }
    }
}