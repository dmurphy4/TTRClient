package com.d.tickettoride.presenters.ipresenters

import com.d.tickettoride.model.PlayerInfo

interface IStatsPresenter {
    fun getStatsList(): ArrayList<PlayerInfo>
}