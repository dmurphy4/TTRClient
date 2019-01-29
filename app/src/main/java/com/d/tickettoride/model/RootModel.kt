package com.d.tickettoride.model

class RootModel {

    companion object {
        val instance = RootModel()
    }
    var user:Player? = null

    var loggedIn:Boolean = false

    var thisGame:Game? = null

    var currentGames:List<Game>? = null

}

fun main() {
    val a = RootModel.instance
    a.user = Player("dallin", PlayerColor.BLACK)
    val b = RootModel.instance
    print(b.user.toString())
}