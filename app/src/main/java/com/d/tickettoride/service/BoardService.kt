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

    fun claimRoute() {

    }

    fun getPlayerName(id: Int): String {
        return RootModel.instance.game!!.turnOrder[id]
    }

    fun drawDestinationCards() {
        val username = RootModel.instance.user!!.username
        proxy.command(ServerCommand.DrawDestinationCards(username))
    }

    fun chooseDestinationCards(destinationIDs: ArrayList<Int>) {
        val username = RootModel.instance.user!!.username
        proxy.command(ServerCommand.ChooseDestinationCard(username, destinationIDs))
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
}