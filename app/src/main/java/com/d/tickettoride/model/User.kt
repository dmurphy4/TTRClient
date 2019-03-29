package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.*
import kotlin.properties.Delegates.observable

class User(var username:String, var playerInfo:PlayerInfo?,
           var destinationHand:DestinationCardHand?) {

    var cardAmountsChanged:Boolean by observable(true) {
        _, old, new -> onCardAmountsChanged?.invoke(old, new)
    }

    var onCardAmountsChanged: ((Boolean, Boolean) -> Unit)? = null

    var trainCardHand: TrainCarCardHand by observable(TrainCarCardHand(listOf())) { _, old, new ->
        onTrainCardHandCreated?.invoke(new)
    }

    var onTrainCardHandCreated: ((TrainCarCardHand) -> Unit)? = null

    var yourTurn: Boolean by observable(false) {
        _, old, new -> onYourTurn?.invoke(old, new)
    }

    var onYourTurn: ((Boolean, Boolean) -> Unit)? = null

    var lastTurn: Boolean = false

    fun canClaimRoute(routeLength: Int, cardColor: TrainCarCardType) : Boolean {
        val minTracks = when(cardColor) {
            TrainCarCardType.LOCOMOTIVE -> trainCardHand.getAmountOfType(TrainCarCardType.LOCOMOTIVE)
            else -> trainCardHand.getAmountOfType(cardColor) + trainCardHand.getAmountOfType(TrainCarCardType.LOCOMOTIVE)
        }
        return routeLength <= minTracks
    }

    fun canClaimGray(route: Route): ArrayList<String> {
        val numLocomotive = trainCardHand.getAmountOfType(TrainCarCardType.LOCOMOTIVE)
        val numNeeded = route.numTracks - numLocomotive
        val typesToUse = ArrayList<String>()

        if (numNeeded <= 0) {
            typesToUse.add(TrainCarCardType.LOCOMOTIVE.toString())
        }

        for (type in TrainCarCardType.values()) {
            if (trainCardHand.getAmountOfType(type) >= numNeeded) {
                typesToUse.add(type.toString())
            }
        }
        return typesToUse
    }

    fun decreaseCardsPostClaim(color:TrainCarCardType, amount:Int) {
        var locomotives = amount
        val amountOfColor = trainCardHand.getAmountOfType(color)
        if (amount > amountOfColor) {
            locomotives -= amountOfColor
            trainCardHand.changeCardCount(color, -1 * amountOfColor)
            trainCardHand.changeCardCount(TrainCarCardType.LOCOMOTIVE, -1 * locomotives)
        }
        else {
            trainCardHand.changeCardCount(color, -1 * amount)
        }
        trainCardHand.onHandChanged?.invoke(trainCardHand)
    }
}