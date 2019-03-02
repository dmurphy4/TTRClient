package com.d.tickettoride.views.iviews

interface IGameView {
    fun enableMapClicks(enable: Boolean)
    fun enableClaimButton(enable: Boolean)
    fun enableChooseDestButton(enable: Boolean)
    fun dismissDestPickPopup()
    fun displayErrorMessage(message: String)
}