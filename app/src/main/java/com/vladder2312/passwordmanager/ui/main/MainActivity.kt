package com.vladder2312.passwordmanager.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.passwordmanager.R
import com.vladder2312.passwordmanager.domain.Account
import com.vladder2312.passwordmanager.ui.account.AccountActivity
import com.vladder2312.passwordmanager.ui.add_account.AddAccountActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.surfstudio.android.easyadapter.EasyAdapter

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private val adapter = EasyAdapter()
    private val accountListController = AccountListContoller { startAccountActivity(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
        initListeners()

        presenter.updateData()
    }

    override fun setAccounts(accounts: List<Account>){
        adapter.setData(accounts, accountListController)
        swipe_refresh.isRefreshing = false
    }

    override fun startAccountActivity(account: Account){
        val intent = Intent(applicationContext, AccountActivity::class.java)
        intent.putExtra("id", account.id)
        intent.putExtra("username", account.username)
        intent.putExtra("password", account.password)
        intent.putExtra("siteURL", account.siteURL)
        intent.putExtra("siteName", account.siteName)
        intent.putExtra("comment", account.comment)
        startActivity(intent)
    }

    override fun startAddAccountActivity(){
        startActivity(Intent(applicationContext, AddAccountActivity::class.java))
    }

    private fun initRecycler(){
        recycler.layoutManager = LinearLayoutManager(applicationContext)
        recycler.adapter = adapter
    }

    private fun initListeners(){
        site_edit_text.addTextChangedListener { presenter.siteTextChanged(it.toString()) }
        find_button.setOnClickListener { presenter.findButtonClicked() }
        add_button.setOnClickListener { startAddAccountActivity() }
        swipe_refresh.setOnRefreshListener { presenter.updateData() }
    }
}