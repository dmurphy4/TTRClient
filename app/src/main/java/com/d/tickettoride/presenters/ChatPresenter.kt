package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.model.gameplay.Event
import com.d.tickettoride.model.gameplay.EventType
import com.d.tickettoride.presenters.ipresenters.IChatPresenter
import com.d.tickettoride.service.ChatService
import com.d.tickettoride.service.ChooseGameService
import com.d.tickettoride.views.ChatFragment
import com.d.tickettoride.views.iviews.IChatView
import com.d.tickettoride.views.iviews.IChooseGameView

class ChatPresenter(private val chatFragment: IChatView,
                    private val chatService: ChatService = ChatService.instance) : IChatPresenter {

    override fun sendMessage(message:String) {
        val username = RootModel.instance.user!!.username
        val event = Event(EventType.MESSAGE, username, message)

        chatService.sendChat(event)
    }

    override fun getChatList(): ArrayList<Event> {
        return ArrayList()
    }
}