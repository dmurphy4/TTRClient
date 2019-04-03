package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.*
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.presenters.states.ClaimingGrayRouteState
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
            setHandChangedListener()
            currentState.beginPlay(this)
        }

        rootModel.user!!.onYourTurn = { _, new ->
            if (new) {
                postErrorMessage("Hurry! IT'S YOUR TURN")
                currentState.beginTurn(this, rootModel.user!!.lastTurn)
            }
        }

        rootModel.onGameSummaryGiven = { _, _ ->
            gameActivity.startEndGameActivity()
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
        val route = RootModel.instance.game!!.board.routes.getValue(id)

        if (route.color == RouteColor.GRAY) {

            currentState.claimGrayRoute(this)

            if (currentState is ClaimingGrayRouteState) {
                val typesToUse = RootModel.instance.user!!.canClaimGray(route).toTypedArray()

                if (typesToUse.isNotEmpty()) {
                    gameActivity.displayColorPickPopup(typesToUse)
                } else {
                    postErrorMessage("Sorry, you don't have the cards to claim this gray route.")
                    currentState.beginTurn(this, RootModel.instance.user!!.lastTurn)
                }
            }
        }

        else {
            currentState.claimRoute(this, id)
        }
    }

    override fun claimGrayRoute(id: Int, color: String) {
        currentState.chooseColorForGrayRoute(this, id, TrainCarCardType.valueOf(color.toUpperCase()))
    }

    override fun getState(): Statelike {
        return currentState
    }

    override fun dismissDestinationPopUp() {
        gameActivity.dismissDestPickPopup()
    }

    private fun setHandChangedListener() {
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
    }
}