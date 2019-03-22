package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.*

class GameCreator(val gameInfo: GameInfo,
                  var playerStats:ArrayList<PlayerInfo>,
                  var faceUpTrainCarCards: ArrayList<TrainCarCard>,
                  var trainCarCardDeckSize: Int,
                  var destinationDeckSize: Int,
                  var turnOrder:List<String>,
                  var eventHistory:ArrayList<Event> = ArrayList()) {

    fun createGame() : Game {
        return Game(
            gameInfo,
            playerStats,
            turnOrder,
            TrainCarDeck(faceUpTrainCarCards, trainCarCardDeckSize),
            destinationDeckSize,
            eventHistory
        )
    }
}