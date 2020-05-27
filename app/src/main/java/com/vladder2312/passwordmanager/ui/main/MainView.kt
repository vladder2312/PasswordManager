package com.vladder2312.passwordmanager.ui.main

import com.arellomobile.mvp.MvpView
import com.vladder2312.passwordmanager.domain.Account

interface MainView : MvpView {

    fun setAccounts(accounts: List<Account>)
    fun startAccountActivity(account: Account)
    fun startAddAccountActivity()
}