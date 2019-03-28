package com.d.tickettoride.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.d.tickettoride.presenters.EndGamePresenter
import com.d.tickettoride.presenters.ipresenters.IEndGamePresenter
import com.d.tickettoride.views.iviews.IEndGameView
import kotlinx.android.synthetic.main.activity_end_game.*

class EndGameActivity : AppCompatActivity(), IEndGameView {

    val endGamePresenter: IEndGamePresenter = EndGamePresenter(this)

    override fun displayErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun displayWinner(username: String, points: Int) {
        end_game_title.text = "$username won with $points points!"
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
        when(row) {
            1 -> displayRow1(username, claimedPts, destinationPts, negDestinationPts, mostRoutePoints, totalPoints)
            2 -> displayRow2(username, claimedPts, destinationPts, negDestinationPts, mostRoutePoints, totalPoints)
            3 -> displayRow3(username, claimedPts, destinationPts, negDestinationPts, mostRoutePoints, totalPoints)
            4 -> displayRow4(username, claimedPts, destinationPts, negDestinationPts, mostRoutePoints, totalPoints)
            5 -> displayRow5(username, claimedPts, destinationPts, negDestinationPts, mostRoutePoints, totalPoints)
        }
    }

    override fun goBackToChooseGameActivity() {
        val intent = Intent(this, ChooseGameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    private fun displayRow1(username: String, claimedPts: Int, destinationPts: Int, negDestinationPts: Int,
                            mostRoutePoints: Int, totalPoints: Int) {
        place_1.text = "1st"
        username_1.text = username
        claimed_1.text = claimedPts.toString()
        destination_1.text = destinationPts.toString()
        lost_destination_1.text = negDestinationPts.toString()
        most_routes_1.text = mostRoutePoints.toString()
        total_1.text = totalPoints.toString()
    }

    private fun displayRow2(username: String, claimedPts: Int, destinationPts: Int, negDestinationPts: Int,
                            mostRoutePoints: Int, totalPoints: Int) {
        place_2.text = "2nd"
        username_2.text = username
        claimed_2.text = claimedPts.toString()
        destination_2.text = destinationPts.toString()
        lost_destination_2.text = negDestinationPts.toString()
        most_routes_2.text = mostRoutePoints.toString()
        total_2.text = totalPoints.toString()
    }

    private fun displayRow3(username: String, claimedPts: Int, destinationPts: Int, negDestinationPts: Int,
                            mostRoutePoints: Int, totalPoints: Int) {
        place_3.text = "3rd"
        username_3.text = username
        claimed_3.text = claimedPts.toString()
        destination_3.text = destinationPts.toString()
        lost_destination_3.text = negDestinationPts.toString()
        most_routes_3.text = mostRoutePoints.toString()
        total_3.text = totalPoints.toString()
    }

    private fun displayRow4(username: String, claimedPts: Int, destinationPts: Int, negDestinationPts: Int,
                            mostRoutePoints: Int, totalPoints: Int) {
        place_4.text = "4th"
        username_4.text = username
        claimed_4.text = claimedPts.toString()
        destination_4.text = destinationPts.toString()
        lost_destination_4.text = negDestinationPts.toString()
        most_routes_4.text = mostRoutePoints.toString()
        total_4.text = totalPoints.toString()
    }

    private fun displayRow5(username: String, claimedPts: Int, destinationPts: Int, negDestinationPts: Int,
                            mostRoutePoints: Int, totalPoints: Int) {
        place_5.text = "5th"
        username_5.text = username
        claimed_5.text = claimedPts.toString()
        destination_5.text = destinationPts.toString()
        lost_destination_5.text = negDestinationPts.toString()
        most_routes_5.text = mostRoutePoints.toString()
        total_5.text = totalPoints.toString()
    }
}