package com.d.tickettoride.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.d.tickettoride.R
import com.d.tickettoride.presenters.RegisterPresenter
import com.d.tickettoride.presenters.ipresenters.IRegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import com.d.tickettoride.util.afterTextChanged
import com.d.tickettoride.views.iviews.IRegisterView

class RegisterActivity : AppCompatActivity(), IRegisterView {

    private val registerPresenter: IRegisterPresenter = RegisterPresenter(this)

    private fun passwordsValid() : Boolean {
        return (register_confirm_password.text.toString().isNotEmpty() && register_password.text.toString().isNotEmpty()) &&
                (register_confirm_password.text.toString() == register_password.text.toString())
    }

    private fun checkEnableRegister() {
        if (passwordsValid()) {
            enableRegister(register_username.text.toString().isNotEmpty())
        }
        else {
            enableRegister(false)
        }
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
            checkEnableRegister()
        }
        register_password.afterTextChanged {
            checkEnableRegister()
        }
        register_username.afterTextChanged {
            checkEnableRegister()
        }
        register_server_host.afterTextChanged {
            registerPresenter.setHost(it)
        }
        register_server_port.afterTextChanged {
            registerPresenter.setPort(it)
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
