package com.d.tickettoride.model

import com.d.tickettoride.servercommunicator.Poller
import kotlin.properties.Delegates.observable

class RootModel {

    companion object {
        val instance = RootModel()
    }

    var poller:Poller? = null

    var user: User? by observable<User?>(null) {
        _, _, _ -> onUserChanged
    }
    var onUserChanged: ((User, User) -> Unit)? = null

    var loggedIn:Boolean by observable(false) {
        _, old, new -> onLogIn?.invoke(old, new)
    }

    var onLogIn: ((Boolean, Boolean) -> Unit)? = null

    var waitingForGame:Boolean by observable(false) {
        _, old, new -> onGameJoined?.invoke(old, new)
    }

    var onGameJoined: ((Boolean, Boolean) -> Unit)? = null

    var game:Game? by observable<Game?>(null) {
        _, old, new -> onGameStarted?.invoke(old, new!!)
    }
    var onGameStarted: ((Game?, Game) -> Unit)? = null

    var gameList:ArrayList<GameInfo> = ArrayList()

    var gameToRemoveFromList:GameInfo? = null

    var gameListLength:Int by observable(0) {
        _, old, new -> onGameListChanged?.invoke(old, new)
    }
    var onGameListChanged: ((Int, Int) -> Unit)? = null

    var errorMessage:String? by observable<String?>(null) {
        _,old,new -> onErrorMessageGiven?.invoke(old, new)
    }
    var onErrorMessageGiven: ((String?, String?) -> Unit)? = null
}