package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.City
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.service.BoardService
import com.d.tickettoride.views.iviews.IGameView

class GamePresenter(private val gameActivity: IGameView,
                    private val boardService: BoardService = BoardService.instance): IGamePresenter {

    init {
        val rootModel = RootModel.instance
        rootModel.onDestinationCardsGiven = { _, new ->
            if (new) {
                gameActivity.displayDestPickPopup(rootModel.destinationCardsToChoose!!)
                rootModel.destCardsGiven = false
            }
        }
    }

    override fun getBoard(): Board {
        return boardService.getBoard()
    }

    override fun getCityFromGame(id:Int) : City {
        return RootModel.instance.game!!.board.cities.getValue(id)
    }

    fun phase2Update() {
        RootModel.instance.game!!.playerStats[0].score += 4
        RootModel.instance.game!!.playerStats[0].numTrains -= 3
        RootModel.instance.game!!.playerStats[0].numTrainCards -= 3
        RootModel.instance.user!!.trainCardHand
    }
}