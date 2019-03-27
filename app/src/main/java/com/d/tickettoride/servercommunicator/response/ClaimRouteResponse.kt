package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.service.BoardService

class ClaimRouteResponse(val id: Int) : GenericResponse() {

    override fun execute() {
        BoardService.instance.decreaseCardsPostClaim(id, null) // null because it's not gray
    }
}