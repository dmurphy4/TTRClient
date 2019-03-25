package com.d.tickettoride.service

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.servercommunicator.ServerProxy
import com.d.tickettoride.servercommunicator.command.server.ServerCommand

class TurnService(private val proxy: ServerProxy = ServerProxy()) {

    companion object {
        val instance = TurnService()
    }

    fun updateTurn(lastTurn: Boolean) {
        RootModel.instance.game!!.updateTurn()
        RootModel.instance.user!!.lastTurn = lastTurn
        RootModel.instance.game!!.statsChanged = true
    }

    fun endTurn() {
        RootModel.instance.user!!.yourTurn = false
        proxy.command(ServerCommand.EndTurn(RootModel.instance.user!!.username))
    }
}