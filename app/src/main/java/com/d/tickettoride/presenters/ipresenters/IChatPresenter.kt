package com.d.tickettoride.presenters.ipresenters

import com.d.tickettoride.model.gameplay.Event

interface IChatPresenter {

    fun sendMessage(message:String)
    fun getChatList(): ArrayList<Event>
}