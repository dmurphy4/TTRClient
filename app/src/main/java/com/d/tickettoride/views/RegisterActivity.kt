package com.d.tickettoride.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.d.tickettoride.R
import com.d.tickettoride.presenters.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), IRegisterView {

    private val registerPresenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        signup_button.setOnClickListener {
            registerPresenter.sendRegisterRequest(register_username.text.toString(),
                                                  register_password.text.toString(),
                                                  register_confirm_password.text.toString())
        }
    }

    override fun startChooseGameActivity() {
        startActivity(Intent(this, ChooseGameActivity::class.java))
    }

    override fun displayErrorMesage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
