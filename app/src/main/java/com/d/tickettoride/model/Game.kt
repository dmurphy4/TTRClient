package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.Event

data class Game(val gameInfo: GameInfo, var gamePlayers:Map<String, PlayerInfo>, var board: Board,
                var turnOrder:List<String>, var eventHistory:List<Event>)
