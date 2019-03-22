package com.d.tickettoride.model.gameplay

data class Board(val cities: Map<Int, City>, val routes: Map<Int, Route>) {

    fun markRoute(id:Int, username:String) {
        routes.getValue(id).owner = username
    }
}