package com.d.tickettoride.model

class GameInfo {
    constructor(name:String, num:Int) {
        this.gameName = name
        this.numPlayers = num
    }
    var gameName:String? = null
    var numPlayers:Int? = null
}