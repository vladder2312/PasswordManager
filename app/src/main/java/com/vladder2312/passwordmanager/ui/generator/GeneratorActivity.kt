package com.vladder2312.passwordmanager.ui.generator

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.passwordmanager.R

class GeneratorActivity : MvpAppCompatActivity(), GeneratorView {

    @InjectPresenter
    lateinit var presenter: GeneratorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generator)
        initListeners()
    }

    fun initListeners(){

    }
}