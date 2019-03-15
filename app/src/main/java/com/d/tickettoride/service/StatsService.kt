package com.d.tickettoride.service

import com.d.tickettoride.model.RootModel

class StatsService {

    companion object {
        val instance = StatsService()
    }

    fun addPoints(amount: Int, username: String) {
        RootModel.instance.game!!.getPlayerByUsername(username)!!.score += amount
    }

    fun addDestinations(amount: Int, username: String) {
        RootModel.instance.game!!.getPlayerByUsername(username)!!.numDestCards += amount
    }

    fun subtractTrainCars(amount: Int, username: String) {
        RootModel.instance.game!!.getPlayerByUsername(username)!!.numTrains -= amount
    }

    fun addTrainCarCards(amount: Int, username: String) {
        RootModel.instance.game!!.getPlayerByUsername(username)!!.numTrainCards += amount
    }

    fun decreaseTrainCarCards(amount: Int, username: String) {
        RootModel.instance.game!!.getPlayerByUsername(username)!!.numTrainCards -= amount
    }
}