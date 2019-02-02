package com.d.tickettoride.model

import kotlin.properties.Delegates.observable

class RootModel {

    companion object {
        val instance = RootModel()
    }
    var user: User? by observable<User?>(null) {
        _, _, _ -> onUserChanged
    }
    var onUserChanged: ((User, User) -> Unit)? = null

    var loggedIn:Boolean by observable(false) {
        _, old, new -> onLogIn?.invoke(old, new)
    }
    var onLogIn: ((Boolean, Boolean) -> Unit)? = null

    var thisGame:Game? by observable<Game?>(null) {
        _, _, _ -> onGameStarted
    }
    var onGameStarted: ((Game, Game) -> Unit)? = null

    var gameList:GameListWrapper<GameInfo> by observable(GameListWrapper()) { // maybe not right
        _, o, n -> onGameListChanged?.invoke(o, n)
    }

    var onGameListChanged: ((GameListWrapper<GameInfo>, GameListWrapper<GameInfo>) -> Unit)? = null

    var gameListLength:Int by observable(0) {
        _, old, new -> onGameAdded?.invoke(old, new)
    }

    var onGameAdded: ((Int, Int) -> Unit)? = null

    var errorMessage:String? by observable<String?>(null) {
        _,_,new -> onErrorMessageGiven
    }
    var onErrorMessageGiven: ((String, String) -> Unit)? = null
}