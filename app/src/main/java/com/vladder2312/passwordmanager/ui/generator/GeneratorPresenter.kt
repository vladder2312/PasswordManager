package com.vladder2312.passwordmanager.ui.generator

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

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
        model.digits = isChecked
    }

    fun englishChecked(isChecked: Boolean) {
        model.english = isChecked
    }

    fun complexitySelected(complexity: Int) {
        when (complexity) {
            0 -> {
                model.comlexity = GeneratorModel.Complexity.RANDOM
            }
            1 -> {
                model.comlexity = GeneratorModel.Complexity.SHIFT
            }
            2 -> {
                model.comlexity = GeneratorModel.Complexity.BYKEY
            }
        }
    }

    fun generatePassword() {

    }
}