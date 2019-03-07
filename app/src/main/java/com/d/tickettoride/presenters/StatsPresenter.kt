package com.d.tickettoride.presenters

import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.ipresenters.IStatsPresenter
import com.d.tickettoride.service.StatsService
import com.d.tickettoride.views.iviews.IStatsView

class StatsPresenter(private val statsFragment: IStatsView,
                     private val statsService: StatsService = StatsService.instance)
                    : IStatsPresenter {

    init {
        val rootModel = RootModel.instance
        rootModel.game!!.onStatsChanged = { _, new ->
            if (new) {
                statsFragment.updateStats()
                rootModel.game!!.statsChanged = false
            }
        }
    }

}