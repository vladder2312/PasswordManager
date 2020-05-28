package com.vladder2312.passwordmanager.utils

import com.vladder2312.passwordmanager.domain.Complexity

class PasswordGenerator(
    var length: Int,
    var digits: Boolean,
    var symbols: Boolean,
    var rus: Boolean,
    var eng: Boolean,
    var key: String?,
    var complexity: Complexity
) {

    fun generate(): String {
        return when (complexity) {
            Complexity.RANDOM -> {
                RandomGenerator().generate()
            }
            Complexity.SHIFT -> {
                ShiftGenerator().generate()
            }
            Complexity.BYKEY -> {
                ByKeyGenerator().generate()
            }
        }
    }

    inner class RandomGenerator() {

        var selected: Int = 0

        init {
            if (digits) selected++
            if (symbols) selected++
            if (rus) selected++
            if (eng) selected++
        }

        fun generate(): String {
            val password = mutableListOf<Char>()
            for (i in 0..length) password.add('0')
            while (password.contains('0')) {
                if (digits) addDigits(password)
                if (symbols) addSymbols(password)
                if (eng) addEnglishLetters(password)
                if (rus) addRussianLetters(password)
            }
            return password.joinToString(separator = "")
        }

        private fun addRussianLetters(password: MutableList<Char>) {
            for (i in 0..length) {
                if ((0 until selected).random() == 0) {
                    password[i] = (('а'..'я') + ('А'..'Я')).random()
                }
            }
        }

        private fun addEnglishLetters(password: MutableList<Char>) {
            for (i in 0..length) {
                if ((0 until selected).random() == 0) {
                    password[i] = (('a'..'z') + ('A'..'Z')).random()
                }
            }
        }

        private fun addDigits(password: MutableList<Char>) {
            for (i in 0..length) {
                if ((0 until selected).random() == 0) {
                    password[i] = ('1'..'9').random()
                }
            }
        }

        private fun addSymbols(password: MutableList<Char>) {
            for (i in 0..length) {
                if ((0 until selected).random() == 0) {
                    password[i] = ('!'..'}').random()
                }
            }
        }
    }

    inner class ShiftGenerator() {

        fun generate(): String {
            return ""
        }
    }

    inner class ByKeyGenerator() {

        fun generate(): String {
            return ""
        }
    }
}