package com.d.tickettoride.presenters.ipresenters

interface ITrainCardsPresenter {
    fun replaceFaceUpCard(idx: Int)
    fun drawFromDeck()
    fun getFaceUpCard(idx: Int): Int
    fun getDrawPileSize(): Int
    fun setGamePresenter(presenter: IGamePresenter)
}