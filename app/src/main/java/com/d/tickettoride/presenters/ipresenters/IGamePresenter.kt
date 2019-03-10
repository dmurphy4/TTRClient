package com.d.tickettoride.presenters.ipresenters

import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.City
import com.d.tickettoride.model.gameplay.DestinationCardHand
import com.d.tickettoride.model.gameplay.ICard

interface IGamePresenter {
    fun getBoard(): Board
    fun setBoard(cities: String, routes: String)
    fun getCityFromGame(id:Int) : City
    fun drawDestinationCards()
    fun testPhase2()
    fun chooseDestinationCards(destinationIDs: ArrayList<Int>)
    fun getDestCards(): String
}