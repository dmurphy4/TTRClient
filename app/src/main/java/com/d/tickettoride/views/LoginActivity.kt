package com.d.tickettoride.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.d.tickettoride.R
import com.d.tickettoride.presenters.LoginPresenter
import com.d.tickettoride.util.afterTextChanged
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ILoginView {

    private val loginPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        button_login.isEnabled = false
        button_login.setOnClickListener {
            loginPresenter.sendLoginRequest(login_username.text.toString(),
                                            login_password.text.toString())
        }
        button_register.setOnClickListener {
            loginPresenter.registerButtonClicked()
        }
        login_password.afterTextChanged {
            button_login.isEnabled = login_password.text.toString().isNotEmpty() && login_username.text.toString().isNotEmpty()
        }
        login_username.afterTextChanged {
            button_login.isEnabled = login_password.text.toString().isNotEmpty() && login_username.text.toString().isNotEmpty()
        }
    }

    override fun startChooseGameActivity() {
        startActivity(Intent(this, ChooseGameActivity::class.java))
    }

    override fun startRegisterActivity() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun displayErrorMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun enableLogIn(enable: Boolean) {
        button_login.isEnabled = enable
    }
}
