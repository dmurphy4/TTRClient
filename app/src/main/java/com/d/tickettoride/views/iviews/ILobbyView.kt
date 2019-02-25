package com.d.tickettoride.views.iviews

interface ILobbyView {
    fun startGame()
    fun setGameName(name: String)
    fun displayErrorMessage(message: String?)
}