package com.d.tickettoride.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.widget.*
import com.d.tickettoride.R
import com.d.tickettoride.model.PlayerColor
import com.d.tickettoride.model.gameplay.Board
import com.d.tickettoride.model.gameplay.DestinationCard
import com.d.tickettoride.model.gameplay.TrainCarCardHand
import com.d.tickettoride.model.gameplay.TrainCarCardType
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
    private lateinit var destination1: TextView
    private lateinit var destination2: TextView
    private lateinit var destination3: TextView
    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox
    private lateinit var checkBox3: CheckBox
    private lateinit var popupWindow: PopupWindow

    private lateinit var destinationCards: ArrayList<DestinationCard>

    private val statsFragment = StatsFragment()
    private val chatFragment = ChatFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val inputStream = resources.openRawResource(R.raw.board)
        gamePresenter.setBoard(inputStream.bufferedReader().use { it.readText() })
        val board = gamePresenter.getBoard()
        game_board.cities = board.cities
        game_board.setRouteData(board.routes)
        game_board.invalidate()

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_frame, statsFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        button_claim_route.setOnClickListener {
            game_board.changeRoutePaintToClaimed(10, PlayerColor.BLUE)
            game_board.invalidate()
        }

        button_draw_destinations.setOnClickListener {
            gamePresenter.drawDestinationCards()
        }

        button_phase_2.setOnClickListener {
            gamePresenter.testPhase2()
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
        val popupView = LayoutInflater.from(baseContext).inflate(R.layout.popup_destination_pick, null)
        popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            false
        ).apply {
            elevation = 20f
        }

        buttonChooseDest = popupView.findViewById(R.id.button_choose_dest)
        buttonChooseDest.setOnClickListener {
            val cards = ArrayList<Int>()
            if (checkBox1.isChecked) cards.add(destinationCards[0].id)
            if (checkBox2.isChecked) cards.add(destinationCards[1].id)
            if (checkBox3.isChecked) cards.add(destinationCards[2].id)

            gamePresenter.chooseDestinationCards(cards)
            popupWindow.dismiss()
            enableClaimButton(true)
        }

        destination1 = popupView.findViewById(R.id.text_dest_1)
        checkBox1 = popupView.findViewById(R.id.checkbox_dest_1)
        destination2 = popupView.findViewById(R.id.text_dest_2)
        checkBox2 = popupView.findViewById(R.id.checkbox_dest_2)
        destination3 = popupView.findViewById(R.id.text_dest_3)
        checkBox3 = popupView.findViewById(R.id.checkbox_dest_3)
    }

    override fun onDestroy() {
        super.onDestroy()
        // In case the back button is pressed, dismiss the popup window to avoid window leak error
        popupWindow.dismiss()
    }

    override fun displayDestPickPopup(cards:ArrayList<DestinationCard>) {
        destinationCards = cards
        destination1.text = "${gamePresenter.getCityFromGame(cards[0].city1).name} to ${gamePresenter.getCityFromGame(cards[0].city2).name} - ${cards[0].points} points"
        destination2.text = "${gamePresenter.getCityFromGame(cards[1].city1).name} to ${gamePresenter.getCityFromGame(cards[1].city2).name} - ${cards[1].points} points"
        destination3.text = "${gamePresenter.getCityFromGame(cards[2].city1).name} to ${gamePresenter.getCityFromGame(cards[2].city2).name} - ${cards[2].points} points"
        popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0)
    }

    override fun enableClaimButton(enable: Boolean) {
        button_claim_route.isEnabled = enable
    }

    override fun updateTrainCards(black: String, blue: String, green: String, orange: String,
                                  pink: String, red: String, white: String, yellow: String, locomotive: String) {
        box_black_cards.text = black
        box_blue_cards.text = blue
        box_green_cards.text = green
        box_orange_cards.text = orange
        box_pink_cards.text = pink
        box_red_cards.text = red
        box_white_cards.text = white
        box_yellow_cards.text = yellow
        box_locomotive_cards.text = locomotive
    }

    override fun displayErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun enableChooseDestButton(enable: Boolean) {
        buttonChooseDest.isEnabled = enable
    }

    override fun dismissDestPickPopup() {
        popupWindow.dismiss()
    }

    override fun drawRoute(id:Int, color:PlayerColor) {
        game_board.changeRoutePaintToClaimed(id, color)
        game_board.invalidate()
    }

    override fun updatePlayerTurn(player: String) {
        turn_indicator.text = "Turn: $player"
    }
}
