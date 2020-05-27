package com.vladder2312.passwordmanager.ui.account

import com.arellomobile.mvp.MvpView

interface AccountView : MvpView {
    fun initViews()
    fun changeMode(on: Boolean)
    fun showDialog()
}