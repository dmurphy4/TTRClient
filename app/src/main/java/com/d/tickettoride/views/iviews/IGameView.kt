package com.d.tickettoride.views.iviews

import com.d.tickettoride.model.gameplay.DestinationCard

interface IGameView {
    fun enableClaimButton(enable: Boolean)
    fun enableChooseDestButton(enable: Boolean)
    fun dismissDestPickPopup()
    fun updateTrainCards(black: String, blue: String, green: String, orange: String,
                         pink: String, red: String, white: String, yellow: String, locomotive: String)
    fun displayErrorMessage(message: String)
    fun displayDestPickPopup(cards:ArrayList<DestinationCard>)
}