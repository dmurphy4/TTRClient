package com.d.tickettoride.presenters

import com.d.tickettoride.model.PlayerSummary
import com.d.tickettoride.model.RootModel
import com.d.tickettoride.presenters.ipresenters.IEndGamePresenter
import com.d.tickettoride.views.EndGameActivity

class EndGamePresenter(private val endGameActivity: EndGameActivity) : IEndGamePresenter {

    override fun fetchEndGameData() {
        val players = RootModel.instance.gameSummary!!.players

        endGameActivity.displayWinner(players[0].username, players[0].totalPoints)

        var player: PlayerSummary?
        for (i in 0..players.size) {
            player = players[i]
            endGameActivity.displayPlayerStats(i, player.username, player.ptsFromClaimedRoutes, player.ptsFromDestinations,
                player.ptsReducedFromDestinations, player.ptsFromMostClaimedRoutes, player.totalPoints)
        }
    }
}