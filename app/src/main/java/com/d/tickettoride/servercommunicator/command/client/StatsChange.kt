package com.d.tickettoride.servercommunicator.command.client

import com.d.tickettoride.service.StatsService

class StatsChange(val type:StatsChangeType, private val amount:Int) {

    fun execute(username: String) {
        val service = StatsService.instance
        when (type) {
            StatsChangeType.ADD_POINTS -> service.addPoints(amount, username)
            StatsChangeType.ADD_DESTINATION_CARDS -> service.addDestinations(amount, username)
            StatsChangeType.DECREASE_TRAIN_CARS -> service.subtractTrainCars(amount, username)
            StatsChangeType.ADD_TRAIN_CAR_CARDS -> service.addTrainCarCards(amount, username)
            StatsChangeType.DECREASE_TRAIN_CAR_CARDS -> service.decreaseTrainCarCards(amount, username)
        }
    }
}