package com.d.tickettoride.model.gameplay

class Player(var info: PlayerInfo) {

    override fun toString(): String {
        return this.info.username + " is my name and my color is " + this.info.color.toString()
    }
}