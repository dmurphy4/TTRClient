package com.d.tickettoride.presenters.ipresenters

interface ITrainCardsPresenter {
    fun replaceFaceUpCard(idx: Int)
    fun drawFromDeck()
}