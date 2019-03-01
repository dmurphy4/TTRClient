package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.DestinationCardHand
import com.d.tickettoride.model.gameplay.TrainCarHand

data class User(var userName:String, var playerInfo:PlayerInfo?,
                var trainHand: TrainCarHand?, var destinationHand:DestinationCardHand?)