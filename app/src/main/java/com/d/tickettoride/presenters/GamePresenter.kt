package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.City
import com.d.tickettoride.model.gameplay.TrainCarCardType
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
        rootModel.user!!.onHandObjectCreated = { _, hand ->
            // hand shouldn't be null when this is called
            gameActivity.updateTrainCards(
                hand!!.cardMap[TrainCarCardType.BLACK].toString(),
                hand.cardMap[TrainCarCardType.BLUE].toString(),
                hand.cardMap[TrainCarCardType.GREEN].toString(),
                hand.cardMap[TrainCarCardType.ORANGE].toString(),
                hand.cardMap[TrainCarCardType.PURPLE].toString(),
                hand.cardMap[TrainCarCardType.RED].toString(),
                hand.cardMap[TrainCarCardType.WHITE].toString(),
                hand.cardMap[TrainCarCardType.YELLOW].toString(),
                hand.cardMap[TrainCarCardType.LOCOMOTIVE].toString()
            )
        }
    }

    override fun getBoard(): Board {
        return boardService.getBoard()
    }

    override fun setBoard(boardString: String) {
        boardService.setBoard(boardString)
    }

    override fun getCityFromGame(id:Int) : City {
        val rootModel = RootModel.instance
        return rootModel.game!!.board.cities.getValue(id)
    }

    override fun drawDestinationCards() {
        boardService.drawDestinationCards()
    }

    override fun chooseDestinationCards(destinationIDs: ArrayList<Int>) {
        boardService.chooseDestinationCards(destinationIDs)
    }

    fun phase2Update() {
        RootModel.instance.game!!.playerStats[0].score += 4
        RootModel.instance.game!!.playerStats[0].numTrains -= 3
        RootModel.instance.game!!.playerStats[0].numTrainCards -= 3
        RootModel.instance.game!!.statsChanged = true
        RootModel.instance.user!!.trainCardHand
    }
}