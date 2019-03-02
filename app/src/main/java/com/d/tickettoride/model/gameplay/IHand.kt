package com.d.tickettoride.model.gameplay

interface IHand {
    fun sort()
    fun draw(cardsDrawn:List<ICard>)
    fun playCards()
}