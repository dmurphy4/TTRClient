package com.d.tickettoride.views.iviews

interface IGameView {
    fun enableMapClicks(enable: Boolean)
    fun enableClaimButton(enable: Boolean)
    fun displayErrorMessage(message: String)
}