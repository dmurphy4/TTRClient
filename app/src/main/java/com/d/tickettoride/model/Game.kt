package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.PlayerInfo

data class Game(val info: GameInfo, var players:ArrayList<PlayerInfo>)
