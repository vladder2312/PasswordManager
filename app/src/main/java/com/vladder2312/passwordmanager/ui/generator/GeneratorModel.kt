package com.vladder2312.passwordmanager.ui.generator

import com.vladder2312.passwordmanager.domain.Complexity


class GeneratorModel {

    var comlexity = Complexity.RANDOM
    var key = ""
    var length = 0
    var symbols = false
    var digits = false
    var russian = false
    var english = false
}