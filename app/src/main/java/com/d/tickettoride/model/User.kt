package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.DestinationCardHand
import com.d.tickettoride.model.gameplay.TrainCarCardHand

class User(var userName:String, var playerInfo:PlayerInfo?,
                var trainCardHand: TrainCarCardHand?, var destinationHand:DestinationCardHand?) {


}