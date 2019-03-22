package com.d.tickettoride.presenters.ipresenters

import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.City
import com.d.tickettoride.presenters.states.Statelike

interface IGamePresenter {
    fun getBoard(): Board
    fun setBoard(cities: String, routes: String)
    fun getCityFromGame(id:Int) : City
    fun drawDestinationCards()
    fun claimRoute(id: Int)
    fun setState(state:Statelike)
    fun chooseDestinationCards(indexes: ArrayList<Int>, notChosen: ArrayList<Int>)
    fun getDestCards(): String
    fun getNumDestCards(): Int
    fun getUserColor(): String
    fun postErrorMessage(message:String)
    fun getState(): Statelike
}