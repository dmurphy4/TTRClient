package com.d.tickettoride.service

import com.d.tickettoride.servercommunicator.command.server.ServerCommand
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.Event
import com.d.tickettoride.servercommunicator.ServerProxy

class ChatService(private val proxy: ServerProxy = ServerProxy()) {

    companion object {
        val instance = ChatService()
    }

    fun sendChat(event:Event) {
        proxy.command(ServerCommand.SendMessage(event))
    }

    fun postChat(event:Event) {
        RootModel.instance.game!!.addEvent(event)
    }
}