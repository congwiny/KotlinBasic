package com.congwiny.basic

import strings.lastChar
import collections.joinToString3
import view.*


/**
 * 使用关键字as来修改导入的类或者函数名称
 * 当你在不同的包中，有一些重名的函数时，在导入时使用关键字as解决命名冲突
 * 或者你可以选择用全名来指出这个类或者函数
 */
import strings.firstChar as first

fun main(args:Array<String>){
    //像调用类的普通成员函数一样去调用这个函数
    println("Kotlin".lastChar())
    println("Kotlin".first())

    val list = listOf(1,2,3)
    println("list join to String: ${list.joinToString3(list," ")}")


    val view: View = Button()
    view.showOff()//I am a View

    val button = Button()
    button.showOff()//I am a Button
}