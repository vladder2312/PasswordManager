package com.vladder2312.passwordmanager.ui.generator

import com.arellomobile.mvp.MvpView

interface GeneratorView : MvpView {

    fun initViews()
    fun initListeners()
    fun setPassword(password: String)
}