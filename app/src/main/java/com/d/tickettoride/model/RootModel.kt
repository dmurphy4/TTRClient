package com.d.tickettoride.model

class RootModel {

    companion object {
        val instance = RootModel()
    }
    var user:User? = null

    var loggedIn:Boolean = false

    var thisGame:Game? = null

    var currentGames:List<Game>? = null

}