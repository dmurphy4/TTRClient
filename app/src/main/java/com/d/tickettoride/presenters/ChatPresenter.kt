package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.Event
import com.d.tickettoride.model.gameplay.EventType
import com.d.tickettoride.presenters.ipresenters.IChatPresenter
import com.d.tickettoride.service.ChatService

class ChatPresenter : IChatPresenter {

    override fun sendMessage(message:String) {
        val username = RootModel.instance.user!!.userName
        val event = Event(EventType.MESSAGE, username, message)

        ChatService.instance.sendChat(event)
    }
}