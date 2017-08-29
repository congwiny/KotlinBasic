package com.congwiny.basic

import text.lastChar2

fun main(args: Array<String>) {
    val sb = StringBuilder("Kotlin")
    //像成员一样访问它
    println(sb.lastChar2) //n

    val sb2 = StringBuilder("Kotlin?")
    sb2.lastChar2 = '!'
    println(sb2) //Kotlin!
}