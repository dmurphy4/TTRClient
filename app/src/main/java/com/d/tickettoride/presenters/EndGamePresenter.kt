package com.d.tickettoride.presenters

import com.d.tickettoride.model.PlayerSummary
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.ipresenters.IEndGamePresenter
import com.d.tickettoride.views.EndGameActivity

class EndGamePresenter(private val endGameActivity: EndGameActivity) : IEndGamePresenter {

    override fun fetchEndGameData() {
        val players = RootModel.instance.gameSummary!!.players

        endGameActivity.displayWinner(RootModel.instance.gameWinner!!, RootModel.instance.gameWinnerPoints)

        for (i in 0..(players.size - 1)) {
            val player: PlayerSummary = players[i]
            endGameActivity.displayPlayerStats(i, player.username, player.ptsFromClaimedRoutes, player.ptsFromDestinations,
                player.ptsReducedFromDestinations, player.ptsFromMostClaimedRoutes, player.totalPoints)
        }
    }
}