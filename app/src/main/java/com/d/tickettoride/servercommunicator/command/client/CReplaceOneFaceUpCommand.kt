package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.model.gameplay.TrainCarCard
import com.d.tickettoride.service.TrainCardService

class CReplaceOneFaceUpCommand(val index: Int, val card: TrainCarCard): ICommand {
    override fun execute() {
        TrainCardService.instance.replaceFaceUpCard(index, card)
    }
}