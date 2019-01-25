package com.d.tickettoride.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.d.tickettoride.R
import kotlinx.android.synthetic.main.activity_choose_game.*

class ChooseGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_game)

        number_picker.minValue = 2
        number_picker.maxValue = 5

        
    }
}
