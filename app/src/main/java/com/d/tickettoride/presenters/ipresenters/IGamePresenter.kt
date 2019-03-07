package com.d.tickettoride.presenters.ipresenters

import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.City

interface IGamePresenter {
    fun getBoard(): Board
    fun setBoard(boardString: String)
    fun getCityFromGame(id:Int) : City
    fun drawDestinationCards()
}