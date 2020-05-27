package com.vladder2312.passwordmanager.ui.generator

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.passwordmanager.R

class GeneratorActivity : MvpAppCompatActivity(), GeneratorView {

    @InjectPresenter
    lateinit var presenter: GeneratorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Генерация пароля"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_generator)
        initListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_generator, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title == "Готово") {
            TODO("Обработка нажатия на Готово")
        }
        return super.onOptionsItemSelected(item)
    }

    fun initListeners() {
        TODO("Обработка нажатия на Сгенерировать")
    }
}