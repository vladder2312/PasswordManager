package com.vladder2312.passwordmanager.ui.generator


class GeneratorModel {

    enum class Complexity{
        RANDOM, SHIFT, BYKEY
    }

    lateinit var key : String
    lateinit var comlexity : Complexity
    var length = 0
    var symbols = false
    var digits = false
    var russian = false
    var english = false
}