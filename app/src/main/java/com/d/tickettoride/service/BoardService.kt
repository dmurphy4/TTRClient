package com.d.tickettoride.service

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.*
import com.d.tickettoride.servercommunicator.ServerProxy
import com.d.tickettoride.servercommunicator.command.server.ServerCommand
import com.d.tickettoride.util.fromJson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class BoardService(val proxy: ServerProxy = ServerProxy()) {

    companion object {
        val instance = BoardService()
    }

    fun getPlayerName(id: Int): String {
        return RootModel.instance.game!!.turnOrder[id]
    }

    fun getUserColor(): String {
        return RootModel.instance.user!!.playerInfo!!.color.toString()
    }

    fun drawDestinationCards() {
        val username = RootModel.instance.user!!.username
        proxy.command(ServerCommand.DrawDestinationCards(username))
    }

    fun chooseFirstDestinationCards(indexes: ArrayList<Int>, notChosen: ArrayList<Int>) {
        val username = RootModel.instance.user!!.username
        val destinationIDs = ArrayList<Int>()
        val notChosenDestIDs = ArrayList<Int>()
        for (idx in indexes) {
            destinationIDs.add(RootModel.instance.destinationCardsToChoose!![idx].id)
        }
        for (idx in notChosen) {
            notChosenDestIDs.add(RootModel.instance.destinationCardsToChoose!![idx].id)
        }
        proxy.command(ServerCommand.ChooseFirstDestinationHand(username, destinationIDs, notChosenDestIDs))
    }

    fun chooseDestinationCards(indexes: ArrayList<Int>, notChosen: ArrayList<Int>) {
        val username = RootModel.instance.user!!.username
        val destinationIDs = ArrayList<Int>()
        val notChosenDestIDs = ArrayList<Int>()
        for (idx in indexes) {
            destinationIDs.add(RootModel.instance.destinationCardsToChoose!![idx].id)
        }
        for (idx in notChosen) {
            notChosenDestIDs.add(RootModel.instance.destinationCardsToChoose!![idx].id)
        }
        proxy.command(ServerCommand.ChooseDestinationCard(username, destinationIDs, notChosenDestIDs))
    }

    fun getFormattedDestinations(): ArrayList<String> {
        val cards = RootModel.instance.destinationCardsToChoose!!
        return arrayListOf(
            "${getCity(cards[0].city1).name} to ${getCity(cards[0].city2).name} - ${cards[0].points} points",
            "${getCity(cards[1].city1).name} to ${getCity(cards[1].city2).name} - ${cards[1].points} points",
            "${getCity(cards[2].city1).name} to ${getCity(cards[2].city2).name} - ${cards[2].points} points"
        )
    }

    fun getBoard(): Board {
        return RootModel.instance.game!!.board
    }

    fun setBoard(citiesJson: String, routesJson: String) {
        val cities = Gson().fromJson<Map<Int, City>>(citiesJson)
        val routes = Gson().fromJson<Map<Int, Route>>(routesJson)
        RootModel.instance.game!!.board = Board(cities, routes)
    }

    private fun getCity(id:Int) : City {
        return RootModel.instance.game!!.board.cities.getValue(id)
    }

    fun claimRoute(id:Int) {
        proxy.command(ServerCommand.ClaimRoute(id, RootModel.instance.user!!.username))
    }

    fun claimGrayRoute(id:Int, chosenColor:TrainCarCardType) {
        proxy.command(ServerCommand.ClaimGrayRoute(id, RootModel.instance.user!!.username, chosenColor))
    }

    fun setUpTrainCardMap() {
        RootModel.instance.user!!.trainCardHand.setUpMap()
    }

    fun markRoute(id:Int, username:String) {
        RootModel.instance.game!!.board.markRoute(id, username, RootModel.instance.game!!.getPlayerByUsername(username)!!.color!!.toString())
    }

    fun decreaseCardsPostClaim(id:Int, colorIfGray: TrainCarCardType?) {
        val route = RootModel.instance.game!!.board.routes.getValue(id)

        if (colorIfGray == null) {
            RootModel.instance.user!!.decreaseCardsPostClaim(RouteColor.getCardColor(route.color), route.numTracks)
        }
        else {
            RootModel.instance.user!!.decreaseCardsPostClaim(colorIfGray, route.numTracks)
        }
    }

    fun setRouteAsClaimedListener(callback: ((Int, String) -> Unit)) {
        RootModel.instance.game!!.board.onOwnerChanged = callback
    }

    fun setTrainCardHandChangedListener(callback: ((TrainCarCardHand) -> Unit)) {
        RootModel.instance.user!!.trainCardHand.onHandChanged = callback
    }

    fun setTrainCardHandCreatedListener(callback: ((TrainCarCardHand) -> Unit)) {
        RootModel.instance.user!!.onTrainCardHandCreated = callback
    }
}