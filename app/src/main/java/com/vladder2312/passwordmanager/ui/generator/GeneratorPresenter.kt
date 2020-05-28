package com.vladder2312.passwordmanager.ui.generator

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladder2312.passwordmanager.domain.Complexity
import com.vladder2312.passwordmanager.utils.PasswordGenerator

@InjectViewState
class GeneratorPresenter : MvpPresenter<GeneratorView>() {

    val model = GeneratorModel()

    fun lengthChanged(length: Int) {
        model.length = length
    }

    fun keyChanged(key: String) {
        model.key = key
    }

    fun symbolsChecked(isChecked: Boolean) {
        model.symbols = isChecked
    }

    fun digitsChecked(isChecked: Boolean) {
        model.digits = isChecked
    }

    fun russianChecked(isChecked: Boolean) {
        model.russian = isChecked
    }

    fun englishChecked(isChecked: Boolean) {
        model.english = isChecked
    }

    fun complexitySelected(complexity: Int) {
        when (complexity) {
            0 -> {
                model.comlexity = Complexity.RANDOM
            }
            1 -> {
                model.comlexity = Complexity.SHIFT
            }
            2 -> {
                model.comlexity = Complexity.BYKEY
            }
        }
    }

    fun generatePassword() {
        val generator = PasswordGenerator(
            model.length,
            model.digits,
            model.symbols,
            model.russian,
            model.english,
            model.key,
            model.comlexity
        )
        viewState.setPassword(generator.generate())
    }
}