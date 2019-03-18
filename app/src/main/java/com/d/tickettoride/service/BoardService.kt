package com.d.tickettoride.service

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.City
import com.d.tickettoride.model.gameplay.Route
import com.d.tickettoride.servercommunicator.ServerProxy
import com.d.tickettoride.servercommunicator.command.server.ServerCommand
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

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

    fun setBoard(cities: String, routes: String) {
        val cities = Gson().fromJson<Map<Int, City>>(cities)
        val routes = Gson().fromJson<Map<Int, Route>>(routes)
        RootModel.instance.game!!.board.cities = cities
        RootModel.instance.game!!.board.routes = routes
    }

    private fun getCity(id:Int) : City {
        return RootModel.instance.game!!.board.cities.getValue(id)
    }

    fun claimRoute(id:Int) {

    }
}