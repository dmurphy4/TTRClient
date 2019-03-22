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

    fun canClaimRoute(route: Route) : Boolean{
        val routeLength = route.numTracks
        return routeLength < (trainCardHand!!.cardMap[RouteColor.getCardColor(route.color)]!! + trainCardHand!!.cardMap[TrainCarCardType.LOCOMOTIVE]!!)
    }

}