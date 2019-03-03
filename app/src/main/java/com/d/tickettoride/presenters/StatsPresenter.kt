package com.d.tickettoride.presenters

import com.d.tickettoride.presenters.ipresenters.IStatsPresenter
import com.d.tickettoride.service.ChatService
import com.d.tickettoride.views.iviews.IChatView
import com.d.tickettoride.views.iviews.IStatsView

class StatsPresenter(private val statsFragment: IStatsView,
                     private val chatService: StatsService = StatsService.instance)
                    : IStatsPresenter {
}