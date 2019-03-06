package com.d.tickettoride.model.gameplay

interface IHand {
    fun draw(cardsDrawn:List<ICard>)
    fun playCards()
}