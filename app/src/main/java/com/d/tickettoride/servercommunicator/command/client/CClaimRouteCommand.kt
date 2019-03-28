package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.service.BoardService

class CClaimRouteCommand(var id:Int, var username:String, var errorMessage: String?) : ICommand {

    override fun execute() {
        if (errorMessage == null) BoardService.instance.markRoute(id, username)
    }
}