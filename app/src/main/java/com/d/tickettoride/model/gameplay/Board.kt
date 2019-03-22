package com.d.tickettoride.model.gameplay

data class Board(val cities: Map<Int, City>, val routes: Map<Int, Route>) {

    fun markRoute(id:Int, username:String, playerColor: String) {
        routes.getValue(id).owner = username
        onOwnerChanged?.invoke(id, playerColor)
    }

    var onOwnerChanged: ((Int, String) -> Unit)? = null
}