package com.vladder2312.passwordmanager.ui.account

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.passwordmanager.R
import com.vladder2312.passwordmanager.domain.Account
import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : MvpAppCompatActivity(), AccountView {

    @InjectPresenter
    lateinit var presenter: AccountPresenter
    private lateinit var doneButton: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_account, menu)
        doneButton = menu.getItem(1)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.change_account_button -> {
                changeMode(true)
                true
            }
            R.id.delete_account_button -> {
                showDialog()
                true
            }
            R.id.done_account_button -> {
                changeMode(false)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViews() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra("siteName")
        site_name_account.setText(intent.getStringExtra("siteName"))
        site_url_account.setText(intent.getStringExtra("siteURL"))
        login_account.setText(intent.getStringExtra("username"))
        password_account.setText(intent.getStringExtra("password"))
        comment_account.setText(intent.getStringExtra("comment"))
    }

    private fun changeMode(on: Boolean) {
        doneButton.isVisible = on
        site_name_account.isEnabled = on
        site_url_account.isEnabled = on
        login_account.isEnabled = on
        password_account.isEnabled = on
        comment_account.isEnabled = on
        if (!on) presenter.updateAccount(
            Account(
                intent.getStringExtra("id")!!,
                login_account.text.toString(),
                password_account.text.toString(),
                site_url_account.text.toString(),
                site_name_account.text.toString(),
                comment_account.text.toString()
            )
        )
    }

    private fun showDialog(){
        AlertDialog.Builder(this)
            .setMessage("Вы уверены, что хотите удалить этот аккаунт?")
            .setPositiveButton("Да") { _: DialogInterface, _: Int ->
                presenter.deleteAccount(intent.getStringExtra("id")!!)
                finish()
            }
            .setNegativeButton("Нет") { _: DialogInterface, _: Int -> }
            .show()
    }
}