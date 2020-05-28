package com.vladder2312.passwordmanager.ui.generator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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
        setContentView(R.layout.activity_generator)
        initViews()
        initListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_generator, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        if (item.title == "Готово") {
            if (password_gen.text.toString() != "") {
                setResult(
                    Activity.RESULT_OK,
                    Intent().putExtra("password", password_gen.text.toString())
                )
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initViews() {
        title = "Генерация пароля"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val complexities = resources.getStringArray(R.array.complexities)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, complexities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        complexity_spinner_gen.adapter = adapter
        complexity_spinner_gen.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    presenter.complexitySelected(position)
                }
            }
    }

    override fun initListeners() {
        length_gen.addTextChangedListener {
            if (length_gen.text.toString() != "") {
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
        generate_button_gen.setOnClickListener {
            when (complexity_spinner_gen.selectedItemPosition) {
                0 -> {
                    if (length_gen.text.toString() == "" || length_gen.text.toString() == "0") {
                        Toast.makeText(
                            applicationContext,
                            "Введите длину пароля",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (!rus_checkbox_gen.isChecked && !eng_checkbox_gen.isChecked) {
                        Toast.makeText(
                            applicationContext,
                            "Выберите хотя бы один язык",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else presenter.generatePassword()
                }
                1,2 -> {
                    if (genkey_gen.text.toString().trim() == "") {
                        Toast.makeText(
                            applicationContext,
                            "Введите ключ генерации",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else presenter.generatePassword()
                }
            }
        }
    }

    override fun setPassword(password: String) {
        password_gen.setText(password)
    }
}