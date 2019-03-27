package com.d.tickettoride.views.iviews

interface IEndGameView {
    fun displayErrorMessage(message: String)
    fun displayWinner(username: String, points: Int)
    fun displayPlayerStats(row: Int, username: String, claimedPts: Int, destinationPts: Int, negDestinationPts: Int,
                           mostRoutePoints: Int, totalPoints: Int)
    fun goBackToChooseGameActivity()
}