package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.*
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.presenters.states.PreGameState
import com.d.tickettoride.presenters.states.Statelike
import com.d.tickettoride.service.BoardService
import com.d.tickettoride.views.iviews.IGameView
import java.lang.StringBuilder

class GamePresenter(private val gameActivity: IGameView,
                    private val boardService: BoardService = BoardService.instance): IGamePresenter {

    private var currentState: Statelike = PreGameState()

    init {
        val rootModel = RootModel.instance
        rootModel.onDestinationCardsGiven = { _, new ->
            if (new) {
                gameActivity.displayDestPickPopup(boardService.getFormattedDestinations())
                rootModel.destCardsGiven = false
            }
        }
        boardService.setTrainCardHandCreatedListener { hand ->
            hand.setUpMap()
            gameActivity.updateTrainCards(
                hand.getBlack(),
                hand.getBlue(),
                hand.getGreen(),
                hand.getOrange(),
                hand.getPurple(),
                hand.getRed(),
                hand.getWhite(),
                hand.getYellow(),
                hand.getLocomotive()
            )
            currentState.beginPlay(this)
        }
        boardService.setTrainCardHandChangedListener { hand ->
            gameActivity.updateTrainCards(
                hand.getBlack(),
                hand.getBlue(),
                hand.getGreen(),
                hand.getOrange(),
                hand.getPurple(),
                hand.getRed(),
                hand.getWhite(),
                hand.getYellow(),
                hand.getLocomotive()
            )
        }
        rootModel.game!!.playerStats[0].yourTurn = true

        rootModel.user!!.onYourTurn = { _, new ->
            if (new) {
                currentState.beginTurn(this)
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
        currentState.drawDestinations(this)
    }

    override fun chooseDestinationCards(indexes: ArrayList<Int>, notChosen: ArrayList<Int>) {
        currentState.returnDestinations(this, indexes, notChosen)
    }

    override fun postErrorMessage(message:String) {
        gameActivity.displayErrorMessage(message)
    }

    override fun setRouteClaimedListener() {
        boardService.setRouteAsClaimedListener { id, color ->
            gameActivity.setRouteToClaimed(id, color)
        }
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
        return RootModel.instance.game!!.destinationDeckSize
    }

    override fun getUserColor(): String {
        return boardService.getUserColor()
    }

    override fun setState(state: Statelike) {
        currentState = state
    }
    override fun claimRoute(id: Int) {
        currentState.claimRoute(this, id)
    }

    override fun getState(): Statelike {
        return currentState
    }

//    override fun testPhase2() {
//        when(phase2Iteration) {
//            0 -> {
//                gameActivity.displayErrorMessage("Adding 10 to each player's stats...")
//                for (playerStats in RootModel.instance.game!!.playerStats) {
//                    playerStats.numTrainCards += 10
//                    playerStats.numDestCards += 10
//                    playerStats.numTrains += 10
//                    playerStats.score += 10
//                }
//                RootModel.instance.game!!.statsChanged = true
//                phase2Iteration += 1
//            }
//            1 -> {
//                gameActivity.displayErrorMessage("Subtracting 10 from each player's stats...")
//                for (playerStats in RootModel.instance.game!!.playerStats) {
//                    playerStats.numTrainCards -= 10
//                    playerStats.numDestCards -= 10
//                    playerStats.numTrains -= 10
//                    playerStats.score -= 10
//                }
//                RootModel.instance.game!!.statsChanged = true
//                phase2Iteration += 1
//            }
//            2 -> {
//                gameActivity.displayErrorMessage("Changing player turn...")
//                RootModel.instance.game!!.updateTurn()
//                RootModel.instance.game!!.statsChanged = true
//
//                phase2Iteration += 1
//            }
//            3 -> {
//                gameActivity.displayErrorMessage("Adding train car cards (5 of black, 2 of red, 3 of blue, 1 locomotive...")
//
//                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.LOCOMOTIVE] =
//                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.LOCOMOTIVE]!! + 1
//                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLACK] =
//                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLACK]!! + 5
//                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.RED] =
//                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.RED]!! + 2
//                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLUE] =
//                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLUE]!! + 3
//
//                RootModel.instance.user!!.cardAmountsChanged = true
//
//                phase2Iteration += 1
//            }
//            4 -> {
//                gameActivity.displayErrorMessage("Subtracting train car cards (5 of black, 2 of red, 3 of blue, 1 locomotive...")
//                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.LOCOMOTIVE] =
//                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.LOCOMOTIVE]!! - 1
//                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLACK] =
//                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLACK]!! - 5
//                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.RED] =
//                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.RED]!! - 2
//                RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLUE] =
//                    RootModel.instance.user!!.trainCardHand!!.cardMap[TrainCarCardType.BLUE]!! - 3
//
//                RootModel.instance.user!!.cardAmountsChanged = true
//
//                phase2Iteration += 1
//            }
//            5 -> {
//                gameActivity.displayErrorMessage("Claiming route for ${RootModel.instance.game!!.playerStats[0].username} " +
//                        "from Las Vegas to Salt Lake City")
//                RootModel.instance.game!!.board.routes[10]!!.owner = RootModel.instance.game!!.playerStats[0].username
//                gameActivity.drawRoute(10, RootModel.instance.game!!.playerStats[0].color!!.toString())
//
//                phase2Iteration += 1
//            }
//            6 -> {
//                gameActivity.displayErrorMessage("Claiming route for ${RootModel.instance.game!!.playerStats[1].username} " +
//                        "from Toronto to Montreal")
//                RootModel.instance.game!!.board.routes[76]!!.owner = RootModel.instance.game!!.playerStats[0].username
//                gameActivity.drawRoute(76, RootModel.instance.game!!.playerStats[1].color!!.toString())
//
//                phase2Iteration += 1
//            }
//            7 -> {
//                //decrease number of destination cards
//                gameActivity.displayErrorMessage("Removing 2 from the destination card deck")
//                val deck: ArrayList<DestinationCard> = RootModel.instance.game!!.board.destinationDeck!!.cards
//                RootModel.instance.game!!.board.destinationDeck!!.cards = ArrayList(deck.drop(2))
//            }
//        }
//    }
}