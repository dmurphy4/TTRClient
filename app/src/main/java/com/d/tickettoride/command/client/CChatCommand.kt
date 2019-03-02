package com.d.tickettoride.command.client

import com.d.tickettoride.model.gameplay.Event
import com.d.tickettoride.service.ChatService

class CChatCommand(val event: Event) : ICommand {

    override fun execute() {
        ChatService.instance.postChat(event)
    }
}