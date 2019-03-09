package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.DestinationCardHand
import com.d.tickettoride.model.gameplay.TrainCarCard
import com.d.tickettoride.model.gameplay.TrainCarCardHand
import kotlin.properties.Delegates.observable

class User(var username:String, var playerInfo:PlayerInfo?,
           var destinationHand:DestinationCardHand?) {

    var cardAmountsChanged:Boolean by observable(true) {
            _, old, new -> onCardAmountsChanged?.invoke(old, new)
    }

    var onCardAmountsChanged: ((Boolean, Boolean) -> Unit)? = null

    var trainCardHand by observable<TrainCarCardHand?>(null) { _, old, new ->
        onHandObjectCreated?.invoke(old, new)
    }

    var onHandObjectCreated: ((TrainCarCardHand?, TrainCarCardHand?) -> Unit)? = null
}