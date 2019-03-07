package com.d.tickettoride.service

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.servercommunicator.ServerProxy
import com.d.tickettoride.servercommunicator.command.server.ServerCommand
import com.google.gson.Gson
import java.io.File

class BoardService(val proxy: ServerProxy = ServerProxy()) {

    companion object {
        val instance = BoardService()
    }

    fun claimRoute() {

    }

    fun drawDestinationCards() {
        val username = RootModel.instance.user!!.username
        proxy.command(ServerCommand.DrawDestinationCards(username))
    }

    fun getBoard(): Board {
        val bufferedReader = File("../util/board.json").bufferedReader()
        val boardString = bufferedReader.use { it.readText() }

        return Gson().fromJson(boardString, Board::class.java)
    }
}