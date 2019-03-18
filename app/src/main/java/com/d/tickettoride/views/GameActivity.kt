package com.d.tickettoride.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import com.d.tickettoride.R
import com.d.tickettoride.presenters.GamePresenter
import com.d.tickettoride.presenters.ipresenters.IGamePresenter
import com.d.tickettoride.views.iviews.IGameView
import kotlinx.android.synthetic.main.activity_game.*
import org.jetbrains.anko.contentView

/*
 * The GameActivity class is responsible for displaying all data associated with game play. It contains
 * fragments which handle some of the displays separately.
 *
 * Operations are provided for the presenter to modify important aspects of the display, such
 * as managing the popup window for choosing destination cards, showing error messages, enabling and disabling
 * buttons, and changing the way routes are drawn on the game board.
 *
 * Domain:
 *      Card boxes: views that display the number of each type of train car card
 *      Chat/Stats buttons: buttons for switching which fragment is displayed in the drawer
 *      CheckBox 1-3: view for selecting of destination cards
 *      ChooseDestButton: button for submitting chosen destination cards
 *      Destination 1-3: view for displaying destination card information
 *      GameBoard: view that displays map with routes and cities
 *      GamePresenter: assists with setting and retrieving model data
 *      PopupWindow: view for displaying destination cards
 */
class GameActivity : AppCompatActivity(), IGameView {

    private val gamePresenter: IGamePresenter = GamePresenter(this)
    private val statsFragment = StatsFragment()
    private val chatFragment = ChatFragment()

    // kotlinx imports can't be used for popup window, so store them here
    private lateinit var buttonChooseDest: Button
    private lateinit var destination1: TextView
    private lateinit var destination2: TextView
    private lateinit var destination3: TextView
    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox
    private lateinit var checkBox3: CheckBox
    private lateinit var popupWindow: PopupWindow

    /*
     * Creates the game activity.
     *
     * @param savedInstanceState Bundle of saved data, can be null
     *
     * @pre None
     *
     * @post Activity is created
     * @post Board data is set
     * @post Game board is drawn
     * @post Right side drawer displays stats fragment
     * @post Listeners for all buttons are set
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        setBoardData()
        drawBoard()
        replaceDrawerFragment(statsFragment)

        button_stats.setOnClickListener { replaceDrawerFragment(statsFragment) }
        button_chat.setOnClickListener { replaceDrawerFragment(chatFragment) }
        button_draw_destinations.setOnClickListener { gamePresenter.drawDestinationCards() }
        button_show_dest.setOnClickListener{
            val message: String = gamePresenter.getDestCards()
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
        button_show_num_dest.setOnClickListener{
            val num: Int = gamePresenter.getNumDestCards()
            Toast.makeText(this, num.toString(), Toast.LENGTH_LONG).show()
        }
        button_phase_2.setOnClickListener { gamePresenter.testPhase2() }
    }

    /*
     * Starts the activity
     *
     * @pre Activity is created
     *
     * @post Views in the popup window are initialized and have listeners
     */
    override fun onStart() {
        super.onStart()
        setPopupWindowVariables()
        buttonChooseDest.setOnClickListener { submitDestinationCards() }
    }

    /*
     * Destroys the activity
     *
     * @pre Activity is running
     *
     * @post Popup window is dismissed and activity no longer exists in memory
     */
    override fun onDestroy() {
        super.onDestroy()
        // In case the back button is pressed, dismiss the popup window to avoid window leak error
        popupWindow.dismiss()
    }

    /*
     * Displays a popup window which allows the user to select destination cards
     *
     * @param cards List of strings describing destination cards
     *
     * @pre length(cards) == 3
     *
     * @post Popup window shows on the screen with the three strings displayed in it
     */
    override fun displayDestPickPopup(cards: ArrayList<String>) {
        destination1.text = cards[0]
        destination2.text = cards[1]
        destination3.text = cards[2]
        popupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0)
    }

    /*
     * Closes the destination pick popup window if it's open
     *
     * @pre displayDestPickPopup() called
     *
     * @post Destination pick popup window is not shown on the screen
     */
    override fun dismissDestPickPopup() {
        popupWindow.dismiss()
    }

    /*
     * Enables or disables the claim button for click events on the screen
     *
     * @param enable Whether the button should be enabled or not
     *
     * @pre None
     *
     * @post Claim button is enabled (if enable = true) or disabled (if enable = false)
     */
    override fun enableClaimButton(enable: Boolean) {
        button_claim_route.isEnabled = enable
    }

    /*
     * Enables or disables the choose destination button for click events on the screen
     *
     * @param enable Whether the button should be enabled or not
     *
     * @pre None
     *
     * @post Choose destination button is enabled (if enable = true) or disabled (if enable = false)
     */
    override fun enableChooseDestButton(enable: Boolean) {
        buttonChooseDest.isEnabled = enable
    }

    /*
     * Updates the number of train car cards the user has on the screen
     *
     * @param black Number of black train car cards
     * @param blue Number of blue train car cards
     * @param green Number of green train car cards
     * @param orange Number of orange train car cards
     * @param pink Number of pink train car cards
     * @param red Number of red train car cards
     * @param white Number of white train car cards
     * @param yellow Number of yellow train car cards
     * @param locomotive Number of locomotive train car cards
     *
     * @pre 0 <= param <= 100 for each parameter
     *
     * @post Number that was passed in for each type of card shows up nicely on the screen
     */
    override fun updateTrainCards(black: Int, blue: Int, green: Int, orange: Int,
                                  pink: Int, red: Int, white: Int, yellow: Int, locomotive: Int) {
        box_black_cards.text = black.toString()
        box_blue_cards.text = blue.toString()
        box_green_cards.text = green.toString()
        box_orange_cards.text = orange.toString()
        box_pink_cards.text = pink.toString()
        box_red_cards.text = red.toString()
        box_white_cards.text = white.toString()
        box_yellow_cards.text = yellow.toString()
        box_locomotive_cards.text = locomotive.toString()
    }

    /*
     * Draws the route with the given id as a solid line in the given color
     *
     * @param id Route id number
     * @param color Color in which to draw the line
     *
     * @pre 0 <= id <= 77
     * @pre color is a 6 digit hexadecimal number preceded by # or any of
     *      "red", "blue", "black", "green", "yellow", or "gray"
     *
     * @post The route with given id shows up on the screen as a solid line in the given color
     */
    override fun drawRoute(id: Int, color: String) {
        game_board.changeRoutePaintToClaimed(id, color)
        game_board.invalidate()
    }

    /*
     * Displays a message (typically an error message) on the screen
     *
     * @param message The message to be displayed
     *
     * @pre None
     *
     * @post message can be seen on the screen
     */
    override fun displayErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /*
     * Sets board data in the model using the presenter
     *
     * @pre None
     *
     * @post Cities and routes data is set in the model
     */
    private fun setBoardData() {
        val citiesStream = resources.openRawResource(R.raw.cities)
        val routesStream = resources.openRawResource(R.raw.routes)
        gamePresenter.setBoard(citiesStream.bufferedReader().use { it.readText() },
            routesStream.bufferedReader().use { it.readText() })
    }

    /*
     * Draws the game board on the screen
     *
     * @pre setBoardData() has been called
     *
     * @post GameBoard object can be seen on the screen
     */
    private fun drawBoard() {
        val board = gamePresenter.getBoard()
        game_board.cities = board.cities
        game_board.setRouteData(board.routes)
        game_board.invalidate()
    }

    /*
     * Submits the checked destination cards to the server.
     *
     * @pre At least 2 destinations are checked
     *
     * @post Selected destination cards have been sent to the server
     * @post Popup window is not shown
     * @post Checkboxes in the popup window are unchecked
     */
    private fun submitDestinationCards() {
        val indexes = ArrayList<Int>()
        if (checkBox1.isChecked) indexes.add(0)
        if (checkBox2.isChecked) indexes.add(1)
        if (checkBox3.isChecked) indexes.add(2)

        if (indexes.size < 2) {
            displayErrorMessage(resources.getString(R.string.error_destinations_not_checked))
        } else {
            gamePresenter.chooseDestinationCards(indexes)

            checkBox1.isChecked = false
            checkBox2.isChecked = false
            checkBox3.isChecked = false

            popupWindow.dismiss()
            enableClaimButton(true)
        }
    }

    /*
     * Replaces the fragment in the right side drawer
     *
     * @param fragment The fragment to insert into the drawer
     *
     * @pre None
     *
     * @post fragment can be viewed in the right side drawer
     */
    private fun replaceDrawerFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    /*
     * Sets data members involving the popup window.
     *
     * @pre Activity is created
     *
     * @post All views associated with the destination pick popup window are initialized
     */
    private fun setPopupWindowVariables() {
        val popupView = LayoutInflater.from(baseContext).inflate(R.layout.popup_destination_pick, null)
        destination1 = popupView.findViewById(R.id.text_dest_1)
        checkBox1 = popupView.findViewById(R.id.checkbox_dest_1)
        destination2 = popupView.findViewById(R.id.text_dest_2)
        checkBox2 = popupView.findViewById(R.id.checkbox_dest_2)
        destination3 = popupView.findViewById(R.id.text_dest_3)
        checkBox3 = popupView.findViewById(R.id.checkbox_dest_3)
        buttonChooseDest = popupView.findViewById(R.id.button_choose_dest)

        popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            false
        ).apply {
            elevation = 20f
        }
    }
}
