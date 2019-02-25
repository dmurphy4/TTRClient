package com.d.tickettoride.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.d.tickettoride.R
import com.d.tickettoride.presenters.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import com.d.tickettoride.util.afterTextChanged
import com.d.tickettoride.views.iviews.IRegisterView

class RegisterActivity : AppCompatActivity(), IRegisterView {

    private val registerPresenter = RegisterPresenter(this)

    private fun passwordsMatch() : Boolean {
        return (register_confirm_password.text.toString().isNotEmpty() && register_password.text.toString().isNotEmpty()) &&
                (register_confirm_password.text.toString() == register_password.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        button_signup.isEnabled = false

        button_signup.setOnClickListener {
            registerPresenter.sendRegisterRequest(register_username.text.toString(),
                                                  register_password.text.toString())
        }
        register_confirm_password.afterTextChanged {
            if (passwordsMatch()) {
                button_signup.isEnabled = register_username.text.toString().isNotEmpty()
            }
            else {
                button_signup.isEnabled = false
            }
        }
        register_password.afterTextChanged {
            if (passwordsMatch()) {
                button_signup.isEnabled = register_username.text.toString().isNotEmpty()
            }
            else {
                button_signup.isEnabled = false
            }
        }
        register_username.afterTextChanged {
            if (passwordsMatch()) {
                button_signup.isEnabled = register_username.text.toString().isNotEmpty()
            }
            else {
                button_signup.isEnabled = false
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
