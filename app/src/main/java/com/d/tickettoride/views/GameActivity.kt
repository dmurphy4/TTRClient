package com.d.tickettoride.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import com.d.tickettoride.R
import com.d.tickettoride.model.PlayerColor
import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.presenters.GamePresenter
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.views.iviews.IGameView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_game.*
import org.jetbrains.anko.contentView

class GameActivity : AppCompatActivity(), IGameView {

    private val gamePresenter: IGamePresenter = GamePresenter(this)

    // Popup window values need to be stored as data members. Trying to use the
    // kotlinx imports gives null pointer exception
    private lateinit var buttonChooseDest: Button
    private lateinit var popupWindow: PopupWindow
    private val statsFragment = StatsFragment()
    private val chatFragment = ChatFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val inputStream = resources.openRawResource(R.raw.board)
        val boardString = inputStream.bufferedReader().use { it.readText() }
        val board = Gson().fromJson(boardString, Board::class.java)
        game_board.cities = board.cities
        game_board.setRouteData(board.routes)
        game_board.invalidate()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_frame, statsFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        button_claim_route.setOnClickListener {
            game_board.changeRoutePaintToClaimed(10, PlayerColor.GREEN)
            game_board.invalidate()
        }

        button_stats.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.content_frame, statsFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        button_chat.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.content_frame, chatFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onStart() {
        super.onStart()
        displayDestPickPopup()
    }

    override fun onDestroy() {
        super.onDestroy()
        // In case the back button is pressed, dismiss the popup window to avoid window leak error
        popupWindow.dismiss()
    }

    override fun displayDestPickPopup() {
        val popupView = LayoutInflater.from(baseContext).inflate(R.layout.popup_destination_pick, null)
        popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            false
        ).apply {
            elevation = 20f
        }

        buttonChooseDest = popupView.findViewById<Button>(R.id.button_choose_dest)
        buttonChooseDest.setOnClickListener {
            popupWindow.dismiss()
            enableClaimButton(true)
        }

        // Showing the popup window in .post ensures it isn't displayed until the activity is ready
        game_board.post {
            popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0)
        }
    }

    override fun enableClaimButton(enable: Boolean) {
        button_claim_route.isEnabled = enable
    }

    override fun displayErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun enableChooseDestButton(enable: Boolean) {
        buttonChooseDest.isEnabled = enable

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // val mapFragment = supportFragmentManager
        //     .findFragmentById(R.id.map) as SupportMapFragment
        // mapFragment.getMapAsync(this)
    }

    override fun dismissDestPickPopup() {
        popupWindow.dismiss()
    }
}
