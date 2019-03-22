package com.d.tickettoride.views.iviews

interface IGameView {
    fun enableClaimButton(enable: Boolean)
    fun enableChooseDestButton(enable: Boolean)
    fun dismissDestPickPopup()
    fun setRoutesAsUnHighlighted()
    fun updateTrainCards(black: Int, blue: Int, green: Int, orange: Int, pink: Int, red: Int,
                         white: Int, yellow: Int, locomotive: Int)
    fun displayErrorMessage(message: String)
    fun drawRoute(id: Int, color: String)
    fun displayDestPickPopup(cards: ArrayList<String>)
    fun setRouteToClaimed(id: Int, playerColor: String)
}