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

    fun setBoard(boardString: String) {
        val board = Gson().fromJson(boardString, Board::class.java)
        RootModel.instance.game?.board = board
    }
}