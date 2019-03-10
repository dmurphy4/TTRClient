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

    private var phase2Iteration = 0

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
            rootModel.user!!.trainCardHand!!.setUpMap()
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

        rootModel.game!!.playerStats[0].yourTurn = true

        rootModel.game!!.onTurnChanged = { _, turn ->
            gameActivity.updatePlayerTurn(boardService.getPlayerName(turn))
        }

        rootModel.user!!.onCardAmountsChanged = { _, new ->
            if (new) {
                val handMap = rootModel.user!!.trainCardHand!!.cardMap
                gameActivity.updateTrainCards(
                    handMap[TrainCarCardType.BLACK].toString(),
                    handMap[TrainCarCardType.BLUE].toString(),
                    handMap[TrainCarCardType.GREEN].toString(),
                    handMap[TrainCarCardType.ORANGE].toString(),
                    handMap[TrainCarCardType.PURPLE].toString(),
                    handMap[TrainCarCardType.RED].toString(),
                    handMap[TrainCarCardType.WHITE].toString(),
                    handMap[TrainCarCardType.YELLOW].toString(),
                    handMap[TrainCarCardType.LOCOMOTIVE].toString()
                )
                rootModel.user!!.cardAmountsChanged = false
            }
        }
    }

    override fun getBoard(): Board {
        return boardService.getBoard()
    }

    override fun setBoard(cities: String, routes: String) {
        boardService.setBoard(cities, routes)
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

    override fun testPhase2() {
        when(phase2Iteration) {
            0 -> {
                gameActivity.displayErrorMessage("Adding 10 to each player's stats...")
                for (playerStats in RootModel.instance.game!!.playerStats) {
                    playerStats.numTrainCards += 10
                    playerStats.numDestCards += 10
                    playerStats.numTrains += 10
                    playerStats.score += 10
                }
                RootModel.instance.game!!.statsChanged = true
                phase2Iteration += 1
            }
            1 -> {
                gameActivity.displayErrorMessage("Subtracting 10 from each player's stats...")
                for (playerStats in RootModel.instance.game!!.playerStats) {
                    playerStats.numTrainCards -= 10
                    playerStats.numDestCards -= 10
                    playerStats.numTrains -= 10
                    playerStats.score -= 10
                }
                RootModel.instance.game!!.statsChanged = true
                phase2Iteration += 1
            }
            2 -> {
                gameActivity.displayErrorMessage("Changing player turn...")
                RootModel.instance.game!!.updateTurn()

                RootModel.instance.game!!.statsChanged = true

                phase2Iteration += 1
            }
            3 -> {
                gameActivity.displayErrorMessage("Adding train car cards (5 of black, 2 of red, 3 of blue, 1 locomotive...")
                val rootModel = RootModel.instance

                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.LOCOMOTIVE] =
                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.LOCOMOTIVE]!! + 1
                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLACK] =
                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLACK]!! + 5
                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.RED] =
                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.RED]!! + 2
                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLUE] =
                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLUE]!! + 3

                RootModel.instance.user!!.cardAmountsChanged = true

                phase2Iteration += 1
            }
            4 -> {
                gameActivity.displayErrorMessage("Subtracting train car cards (5 of black, 2 of red, 3 of blue, 1 locomotive...")
                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.LOCOMOTIVE] =
                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.LOCOMOTIVE]!! - 1
                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLACK] =
                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLACK]!! - 5
                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.RED] =
                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.RED]!! - 2
                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLUE] =
                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLUE]!! - 3

                RootModel.instance.user!!.cardAmountsChanged = true

                phase2Iteration += 1
            }
        }
    }
}