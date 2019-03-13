package com.d.tickettoride.presenters.ipresenters

import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.City

interface IGamePresenter {
    fun getBoard(): Board
    fun setBoard(cities: String, routes: String)
    fun getCityFromGame(id:Int) : City
    fun drawDestinationCards()
    fun testPhase2()
    fun chooseDestinationCards(indexes: ArrayList<Int>)
    fun getDestCards(): String
    fun getNumDestCards(): Int
}