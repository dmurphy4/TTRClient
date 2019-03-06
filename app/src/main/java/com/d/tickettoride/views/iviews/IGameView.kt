package com.d.tickettoride.views.iviews

import com.d.tickettoride.model.gameplay.DestinationCard

interface IGameView {
    fun enableClaimButton(enable: Boolean)
    fun enableChooseDestButton(enable: Boolean)
    fun dismissDestPickPopup()
    fun displayErrorMessage(message: String)
    fun displayDestPickPopup(cards:List<DestinationCard>)
}