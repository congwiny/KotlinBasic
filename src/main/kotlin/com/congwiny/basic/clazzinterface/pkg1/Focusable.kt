package com.congwiny.basic.clazzinterface.pkg1

interface Focusable {
    fun setFocus(b: Boolean) =
            println("I ${if (b) "got" else "lost"} focus")
    fun showOff() = println("I am focusable")
}