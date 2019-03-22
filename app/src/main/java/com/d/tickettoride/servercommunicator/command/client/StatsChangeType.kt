package com.d.tickettoride.servercommunicator.command.client

import com.google.gson.annotations.SerializedName

enum class StatsChangeType {
    @SerializedName("ADD_POINTS") ADD_POINTS,
    @SerializedName("ADD_TRAIN_CAR_CARDS") ADD_TRAIN_CAR_CARDS,
    @SerializedName("DECREASE_TRAIN_CAR_CARDS") DECREASE_TRAIN_CAR_CARDS,
    @SerializedName("ADD_DESTINATION_CARDS") ADD_DESTINATION_CARDS,
    @SerializedName("DECREASE_TRAIN_CARS") DECREASE_TRAIN_CARS
}