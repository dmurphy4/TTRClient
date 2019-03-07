package com.d.tickettoride.presenters.ipresenters

import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.City

interface IGamePresenter {
    fun getBoard(): Board
    fun getCityFromGame(id:Int) : City
}