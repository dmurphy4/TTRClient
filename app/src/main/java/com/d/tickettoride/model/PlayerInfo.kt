package com.d.tickettoride.model

data class PlayerInfo(var username:String?, var color: PlayerColor?, var score:Int = 0, var numDestCards:Int = 0,
                 var numTrainCards:Int = 0, var numTrains:Int = 45, var yourTurn:Boolean = false)