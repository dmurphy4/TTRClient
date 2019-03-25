package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.model.gameplay.TrainCarCardType
import com.d.tickettoride.service.BoardService

class ClaimRouteResponse(val id: Int, private val colorIfGray: TrainCarCardType?) : GenericResponse() {

    override fun execute() {
        BoardService.instance.decreaseCardsPostClaim(id, colorIfGray)
    }
}