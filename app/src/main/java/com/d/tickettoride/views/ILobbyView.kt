package com.d.tickettoride.views

interface ILobbyView {
    fun startGame()
    fun setGameName(name: String)
    fun displayErrorMessage(message: String?)
}