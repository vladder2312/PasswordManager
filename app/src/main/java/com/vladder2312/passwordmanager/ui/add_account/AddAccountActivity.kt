package com.vladder2312.passwordmanager.ui.add_account

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.passwordmanager.R
import com.vladder2312.passwordmanager.domain.Account
import kotlinx.android.synthetic.main.activity_add_account.*

class AddAccountActivity : MvpAppCompatActivity(), AddAccountView {

    @InjectPresenter
    lateinit var presenter: AddAccountPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_account)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_account, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title == "Готово") {
            val name = site_name_add_account.text.toString()
            val url = site_url_add_account.text.toString()
            val password = password_add_account.text.toString()
            val login = login_add_account.text.toString()
            val comment = comment_add_account.text.toString()
            if (name != "" && url != "" && password != "" && login != "" && comment != "") {
                presenter.saveAccount(
                    Account(
                        System.currentTimeMillis().toString(),
                        login,
                        password,
                        url,
                        name,
                        comment
                    )
                )
                finish()
            } else {
                Toast.makeText(applicationContext, "Введите данные во все поля", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}