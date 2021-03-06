package com.d.tickettoride.model

import com.d.tickettoride.model.gameplay.DestinationCard
import com.d.tickettoride.servercommunicator.Poller
import kotlin.properties.Delegates.observable

class RootModel {

    companion object {
        val instance = RootModel()
    }

    var host: String? = null
    var port: String? = null

    var poller: Poller? = null

    var user: User? = null

    var loggedIn:Boolean by observable(false) {
        _, old, new -> onLogIn?.invoke(old, new)
    }

    var onLogIn: ((Boolean, Boolean) -> Unit)? = null

    var waitingForGame: Boolean by observable(false) {
        _, old, new -> onGameJoined?.invoke(old, new)
    }

    var onGameJoined: ((Boolean, Boolean) -> Unit)? = null

    var game: Game? = null

    var gameStarted: Boolean by observable(false) {
        _, old, new -> onGameBoolTrue?.invoke(old, new)
    }

    var onGameBoolTrue: ((Boolean, Boolean) -> Unit)? = null

    var gameList: ArrayList<GameInfo> = ArrayList()

    var gameToRemoveFromList: GameInfo? = null

    var gameListLength: Int by observable(0) {
        _, old, new -> onGameListChanged?.invoke(old, new)
    }

    var onGameListChanged: ((Int, Int) -> Unit)? = null

    var destinationCardsToChoose: ArrayList<DestinationCard>? = null

    var destCardsGiven: Boolean by observable(false) {
            _, old, new -> onDestinationCardsGiven?.invoke(old, new)
    }

    var onDestinationCardsGiven: ((Boolean, Boolean) -> Unit)? = null

    var errorMessage: String? by observable<String?>(null) {
        _, old, new -> onErrorMessageGiven?.invoke(old, new)
    }

    var onErrorMessageGiven: ((String?, String?) -> Unit)? = null

    var gameSummary: GameSummary? by observable<GameSummary?>(null) {
        _, old, new -> onGameSummaryGiven?.invoke(old, new!!)
    }

    var onGameSummaryGiven: ((GameSummary?, GameSummary) -> Unit)? = null

    var gameWinner: String? = null

    var gameWinnerPoints: Int = 0
}