package com.d.tickettoride.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.d.tickettoride.presenters.EndGamePresenter
import com.d.tickettoride.presenters.ipresenters.IEndGamePresenter
import com.d.tickettoride.views.iviews.IEndGameView

class EndGameActivity : AppCompatActivity(), IEndGameView {

    val endGamePresenter: IEndGamePresenter = EndGamePresenter(this)



    override fun displayErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun displayWinner(username: String, points: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayPlayerStats(
        row: Int,
        username: String,
        claimedPts: Int,
        destinationPts: Int,
        negDestinationPts: Int,
        mostRoutePoints: Int,
        totalPoints: Int
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goBackToChooseGameActivity() {
        startActivity(Intent(this, ChooseGameActivity::class.java))
    }
}