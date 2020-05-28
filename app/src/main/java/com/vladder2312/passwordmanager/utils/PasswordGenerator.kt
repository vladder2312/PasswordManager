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

        init {
            length = key!!.length
        }

        fun generate(): String {
            val password = mutableListOf<Char>()
            key!!.toCharArray().toCollection(password)
            for (i in 0 until length) {
                password[i] = password[i] + 3
            }
            return password.joinToString(separator = "")
        }
    }

    inner class ByKeyGenerator() {

        init {
            length = key!!.length
        }

        fun generate(): String {
            val password = mutableListOf<Char>()
            key!!.toCharArray().toCollection(password)
            shake(password)
            inverse(password)
            return password.joinToString(separator = "")
        }

        private fun shake(password: MutableList<Char>): MutableList<Char> {
            for (i in 0 until length) {
                val randomIndex = (0 until length).random()
                val current = password[i]
                password[i] = password[randomIndex]
                password[randomIndex] = current
            }
            return password
        }

        private fun inverse(password: MutableList<Char>): MutableList<Char> {
            var a: Int
            var b: Int
            var c: Int
            loop@ for (i in 0 until length) {
                c = password[i].toInt()
                when (password[i]) {
                    in '0'..'9' -> {
                        a = '0'.toInt(); b = '9'.toInt()
                    }
                    in 'A'..'Z' -> {
                        a = 'A'.toInt(); b = 'Z'.toInt()
                    }
                    in 'a'..'z' -> {
                        a = 'a'.toInt(); b = 'z'.toInt()
                    }
                    in 'А'..'Я' -> {
                        a = 'А'.toInt(); b = 'Я'.toInt()
                    }
                    in 'а'..'я' -> {
                        a = 'а'.toInt(); b = 'я'.toInt()
                    }
                    else -> continue@loop
                }
                password[i] = (a + b - c).toChar()
            }
            return password
        }
    }
}