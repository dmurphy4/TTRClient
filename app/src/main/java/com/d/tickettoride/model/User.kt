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
        var minTracks = 0
        if (cardColor == TrainCarCardType.LOCOMOTIVE) {
            minTracks = trainCardHand.getAmountOfType(TrainCarCardType.LOCOMOTIVE)
        }
        else {
            minTracks =
                trainCardHand.getAmountOfType(cardColor) + trainCardHand.getAmountOfType(TrainCarCardType.LOCOMOTIVE)
        }
        return routeLength <= minTracks
    }

    fun canClaimGray(route: Route): ArrayList<TrainCarCardType> {
        val numLocomotive = trainCardHand.getAmountOfType(TrainCarCardType.LOCOMOTIVE)
        val numNeeded = route.numTracks - numLocomotive
        val typesToUse = ArrayList<TrainCarCardType>()

        for (type in TrainCarCardType.values()) {
            if (trainCardHand.getAmountOfType(type) >= numNeeded) {
                typesToUse.add(type)
            }
        }
        return typesToUse
    }

    fun decreaseCardsPostClaim(color:TrainCarCardType, amount:Int) {
        var am = amount
        if (trainCardHand.getAmountOfType(color) < amount) {
            am -= trainCardHand.getAmountOfType(color)
            trainCardHand.changeCardCount(color, -1 * trainCardHand.getAmountOfType(color))
            trainCardHand.changeCardCount(TrainCarCardType.LOCOMOTIVE, -1 * am)
        }
        else {
            trainCardHand.changeCardCount(color, -1 * am)
        }
        trainCardHand.onHandChanged?.invoke(trainCardHand)
    }
}