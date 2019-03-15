package com.d.tickettoride.servercommunicator.command.client

import com.google.gson.annotations.SerializedName

enum class StatsChangeType {
    @SerializedName("POINTS") POINTS,
    @SerializedName("ADD_TRAIN_CAR_CARDS") ADD_TRAIN_CAR_CARDS,
    @SerializedName("DECREASE_TRAIN_CAR_CARDS") DECREASE_TRAIN_CAR_CARDS,
    @SerializedName("DESTINATION_CARDS") DESTINATION_CARDS,
    @SerializedName("TRAIN_CARS") TRAIN_CARS
}