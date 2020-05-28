package com.vladder2312.passwordmanager.ui.generator

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.widget.addTextChangedListener
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vladder2312.passwordmanager.R
import kotlinx.android.synthetic.main.activity_generator.*

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

    override fun initListeners() {
        length_gen.addTextChangedListener {
            if(length_gen.text.toString() != ""){
                presenter.lengthChanged(Integer.parseInt(length_gen.text.toString()))
            } else {
                presenter.lengthChanged(0)
            }
        }
        genkey_gen.addTextChangedListener {
            presenter.keyChanged(it.toString())
        }
        symbols_checkbox_gen.setOnCheckedChangeListener { _, isChecked ->
            presenter.symbolsChecked(isChecked)
        }
        digits_checkbox_gen.setOnCheckedChangeListener { _, isChecked ->
            presenter.digitsChecked(isChecked)
        }
        rus_checkbox_gen.setOnCheckedChangeListener { _, isChecked ->
            presenter.russianChecked(isChecked)
        }
        eng_checkbox_gen.setOnCheckedChangeListener { _, isChecked ->
            presenter.englishChecked(isChecked)
        }
        complexity_spinner_gen.setOnItemClickListener { _, _, position, _ ->
            presenter.complexitySelected(position)
        }
        generate_button_gen.setOnClickListener {
            presenter.generatePassword()
        }
    }
}