package com.d.tickettoride.model

class Player {
    constructor(uN:String, c:PlayerColor) {
        this.userName = uN
        this.color = c
    }

    var userName:String

    var color:PlayerColor

    override fun toString(): String {
        return this.userName + " is my name and my color is " + this.color.toString()
    }
}