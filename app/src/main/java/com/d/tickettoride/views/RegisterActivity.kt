package com.d.tickettoride.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.d.tickettoride.R
import com.d.tickettoride.presenters.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import com.d.tickettoride.util.afterTextChanged

class RegisterActivity : AppCompatActivity(), IRegisterView {

    private val registerPresenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        button_signup.isEnabled = false

        button_signup.setOnClickListener {
            registerPresenter.sendRegisterRequest(register_username.text.toString(),
                                                  register_password.text.toString())
        }
        register_confirm_password.afterTextChanged { x ->
            if (x == register_password.text.toString()) {
                button_signup.isEnabled = true
            }
        }
    }

    override fun startChooseGameActivity() {
        startActivity(Intent(this, ChooseGameActivity::class.java))
    }

    override fun displayErrorMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun enableRegister(enable: Boolean) {
        button_signup.isEnabled = enable
    }
}
