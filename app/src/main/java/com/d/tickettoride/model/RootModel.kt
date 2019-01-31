package com.d.tickettoride.model

import kotlin.properties.Delegates.observable

class RootModel {

    companion object {
        val instance = RootModel()
    }
    var user:User? by observable<User?>(null) { // we don't care about this...
                                                            // but I just wanted to figure it out
        _, _, _ -> onUserChanged
    }

    var onUserChanged: ((User, User) -> Unit)? = null

    var loggedIn:Boolean by observable(false) {
        _, old, new -> onLogIn?.invoke(old, new)
    }

    var onLogIn: ((Boolean, Boolean) -> Unit)? = null

    var thisGame:Game? = null

    var gameList:List<Game>? by observable(ArrayList()) {
        _, _, _ -> onGameListChanged
    }

    var onGameListChanged: ((ArrayList<Game>, ArrayList<Game>) -> Unit)? = null // lets figure out where to put this

}