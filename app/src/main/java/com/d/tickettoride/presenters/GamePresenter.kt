package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.*
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.presenters.ipresenters.ITrainCardsPresenter
import com.d.tickettoride.presenters.states.NotYourTurnState
import com.d.tickettoride.presenters.states.Statelike
import com.d.tickettoride.service.BoardService
import com.d.tickettoride.views.iviews.IGameView
import java.lang.StringBuilder

class GamePresenter(private val gameActivity: IGameView,
                    private val boardService: BoardService = BoardService.instance,
                    private val trainCardsPresenter: ITrainCardsPresenter,
                    private var currentState: Statelike
): IGamePresenter {

    private var phase2Iteration = 0

    init {
        val rootModel = RootModel.instance
        rootModel.onDestinationCardsGiven = { _, new ->
            if (new) {
                gameActivity.displayDestPickPopup(boardService.getFormattedDestinations())
                rootModel.destCardsGiven = false
            }
        }
        rootModel.user!!.onHandObjectCreated = { _, hand ->
            // hand shouldn't be null when this is called
            rootModel.user!!.trainCardHand!!.setUpMap()
            gameActivity.updateTrainCards(
                hand!!.cardMap[TrainCarCardType.BLACK]!!,
                hand.cardMap[TrainCarCardType.BLUE]!!,
                hand.cardMap[TrainCarCardType.GREEN]!!,
                hand.cardMap[TrainCarCardType.ORANGE]!!,
                hand.cardMap[TrainCarCardType.PURPLE]!!,
                hand.cardMap[TrainCarCardType.RED]!!,
                hand.cardMap[TrainCarCardType.WHITE]!!,
                hand.cardMap[TrainCarCardType.YELLOW]!!,
                hand.cardMap[TrainCarCardType.LOCOMOTIVE]!!
            )
        }

        rootModel.game!!.playerStats[0].yourTurn = true

        rootModel.user!!.onCardAmountsChanged = { _, new ->
            if (new) {
                val handMap = rootModel.user!!.trainCardHand!!.cardMap
                gameActivity.updateTrainCards(
                    handMap[TrainCarCardType.BLACK]!!,
                    handMap[TrainCarCardType.BLUE]!!,
                    handMap[TrainCarCardType.GREEN]!!,
                    handMap[TrainCarCardType.ORANGE]!!,
                    handMap[TrainCarCardType.PURPLE]!!,
                    handMap[TrainCarCardType.RED]!!,
                    handMap[TrainCarCardType.WHITE]!!,
                    handMap[TrainCarCardType.YELLOW]!!,
                    handMap[TrainCarCardType.LOCOMOTIVE]!!
                )
                rootModel.user!!.cardAmountsChanged = false
            }
        }

        currentState = NotYourTurnState()
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

    override fun chooseDestinationCards(indexes: ArrayList<Int>) {
        boardService.chooseDestinationCards(indexes)
    }

    override fun postErrorMessage(message:String) {
        gameActivity.displayErrorMessage(message)
    }

    override fun setState(state: Statelike) {
        currentState = state
    }

    override fun getDestCards(): String{
        val sb = StringBuilder()

        if (RootModel.instance.user!!.destinationHand == null){
            sb.append("You have no Destination Cards")
            return sb.toString()
        }

        val cards: List<DestinationCard> = RootModel.instance.user!!.destinationHand!!.cards
        for (dest in ArrayList(cards)){
            sb.append(RootModel.instance.game!!.board.cities[dest.city1]!!.name)
            sb.append(" -> ")
            sb.append(RootModel.instance.game!!.board.cities[dest.city2]!!.name)
            sb.append(": ")
            sb.append(dest.points.toString())
            sb.append(" points\n")
        }
        return sb.toString()


    }

    override fun getNumDestCards(): Int {
        val cards = RootModel.instance.game!!.board.destinationDeck!!.cards
        return cards.size
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
            5 -> {
                gameActivity.displayErrorMessage("Claiming route for ${RootModel.instance.game!!.playerStats[0].username} " +
                        "from Las Vegas to Salt Lake City")
                RootModel.instance.game!!.board.routes[10]!!.owner = RootModel.instance.game!!.playerStats[0].username
                gameActivity.drawRoute(10, RootModel.instance.game!!.playerStats[0].color!!.toString())

                phase2Iteration += 1
            }
            6 -> {
                gameActivity.displayErrorMessage("Claiming route for ${RootModel.instance.game!!.playerStats[1].username} " +
                        "from Toronto to Montreal")
                RootModel.instance.game!!.board.routes[76]!!.owner = RootModel.instance.game!!.playerStats[0].username
                gameActivity.drawRoute(76, RootModel.instance.game!!.playerStats[1].color!!.toString())

                phase2Iteration += 1
            }
            7 -> {
                //decrease number of destination cards
                gameActivity.displayErrorMessage("Removing 2 from the destination card deck")
                var deck: ArrayList<DestinationCard> = RootModel.instance.game!!.board.destinationDeck!!.cards
                RootModel.instance.game!!.board.destinationDeck!!.cards = ArrayList(deck.drop(2))
            }
        }
    }
}