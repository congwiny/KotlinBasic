package com.congwiny.basic.clazzinterface.pkg1

/**
 * Kotlin的接口与Java8中的相似，
 * 包含抽象方法的定义以及非抽象方法的实现（与Java8中的默认方法类似）
 *
 * 使用interface关键字声明一个Kotlin的接口
 *
 * 接口中的成员始终是open的，不能将其声明为final的
 *
 */
interface Clickable {
    val clickEnable: Boolean

    //普通方法（抽象方法）的声明
    fun click()
    //带默认实现的方法（非抽象方法的实现）
    fun showOff() = println("I am clickable")
}