package com.d.tickettoride.service

import com.d.tickettoride.model.RootModel

class StatsService {

    companion object {
        val instance = StatsService()
    }

    fun addPoints(amount: Int, username: String) {
        RootModel.instance.game!!.getPlayerByUsername(username)!!.score += amount
        RootModel.instance.game!!.statsChanged = true
    }

    fun addDestinations(amount: Int, username: String) {
        RootModel.instance.game!!.getPlayerByUsername(username)!!.numDestCards += amount
        RootModel.instance.game!!.statsChanged = true
    }

    fun subtractTrainCars(amount: Int, username: String) {
        RootModel.instance.game!!.getPlayerByUsername(username)!!.numTrains -= amount
        RootModel.instance.game!!.statsChanged = true
    }

    fun addTrainCarCards(amount: Int, username: String) {
        RootModel.instance.game!!.getPlayerByUsername(username)!!.numTrainCards += amount
        RootModel.instance.game!!.statsChanged = true
    }

    fun decreaseTrainCarCards(amount: Int, username: String) {
        RootModel.instance.game!!.getPlayerByUsername(username)!!.numTrainCards -= amount
        RootModel.instance.game!!.statsChanged = true
    }
}