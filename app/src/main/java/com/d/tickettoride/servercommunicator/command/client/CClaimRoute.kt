package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.service.BoardService

class CClaimRoute(var id:Int, var username:String) : ICommand {

    override fun execute() {
        BoardService.instance.markRoute(id, username)
    }
}