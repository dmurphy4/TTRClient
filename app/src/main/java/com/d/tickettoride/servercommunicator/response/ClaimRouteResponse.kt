package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.service.BoardService
import com.d.tickettoride.service.ErrorMessageService

class ClaimRouteResponse(val id: Int) : GenericResponse() {

    override fun execute() {
        if (errorMessage == null) {
            BoardService.instance.decreaseCardsPostClaim(id, null) // null because it's not gray
        }
        else {
            ErrorMessageService.instance.postErrorMessage(errorMessage)
        }
    }
}