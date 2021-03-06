package com.d.tickettoride.presenters.states

import com.d.tickettoride.model.gameplay.TrainCarCardType
import com.d.tickettoride.presenters.ipresenters.IGamePresenter

open class Statelike {

    open fun drawFaceUp(idx: Int, gamePresenter: IGamePresenter) {
        gamePresenter.postErrorMessage("Sorry, you can't draw a face up card right now.")
    }

    open fun drawFromDrawpile(gamePresenter: IGamePresenter) {
        gamePresenter.postErrorMessage("Sorry, you can't draw a from the draw pile right now.")
    }

    open fun drawDestinations(gamePresenter: IGamePresenter) {
        gamePresenter.postErrorMessage("Sorry, you can't draw destination cards right now.")
    }

    open fun returnDestinations(gamePresenter: IGamePresenter, indexes: ArrayList<Int>, notChosen: ArrayList<Int>) {//not sure if this one is necessary...
        gamePresenter.postErrorMessage("Sorry, you can't return destination cards right now.")
    }

    open fun claimRoute(gamePresenter: IGamePresenter, id:Int) {
        gamePresenter.postErrorMessage("Sorry, you can't claim a route right now.")
    }

    open fun claimGrayRoute(gamePresenter: IGamePresenter) {
        gamePresenter.postErrorMessage("Sorry, you can't claim that gray route right now.")
    }

    open fun chooseColorForGrayRoute(gamePresenter: IGamePresenter, id:Int, chosenColor:TrainCarCardType) {
        gamePresenter.postErrorMessage("Sorry, can't choose a color for a gray route right now.")
    }

    open fun chooseNotToClaimGrayRoute(gamePresenter: IGamePresenter) {
        gamePresenter.postErrorMessage("Sorry, can't choose to not claim a gray route rn.")
    }

    open fun beginTurn(gamePresenter: IGamePresenter, lastTurn: Boolean) {

    }

    open fun beginPlay(gamePresenter: IGamePresenter) {
        gamePresenter.postErrorMessage("Sorry, you can't begin play right now.")
    }
}