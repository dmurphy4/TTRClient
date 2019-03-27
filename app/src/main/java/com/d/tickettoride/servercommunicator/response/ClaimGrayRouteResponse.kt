package com.d.tickettoride.servercommunicator.response

import com.d.tickettoride.model.gameplay.TrainCarCardType
import com.d.tickettoride.service.BoardService

class ClaimGrayRouteResponse(val id: Int, private val color: TrainCarCardType?) : GenericResponse() {

    override fun execute() {
        BoardService.instance.decreaseCardsPostClaim(id, color)
    }
}