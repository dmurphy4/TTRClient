package com.d.tickettoride.presenters.ipresenters

import com.d.tickettoride.model.gameplay.Board

interface IGamePresenter {
    fun getBoard(): Board

}