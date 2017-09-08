package com.congwiny.basic.clazzinterface.objectdeclaration

import java.awt.Frame
import java.awt.Window
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

/**
 * 对象表达式：改变写法的匿名内部类
 * object关键字不仅仅能用来声明单例式的对象，还能用来 声明【匿名对象】
 * 匿名对象替代了Java中的匿名内部类的用法
 *
 * 注意：
 * 1.与Java匿名内部类不同之处：
 *     1).Java匿名内部类只能扩展一个类或实现一个接口，
 *      而Kotlin的匿名对象可以以实现多个接口或者不实现接口
 *     2).Kotlin的匿名对象可访问创建它的函数中的变量（Java也可访问），
 *     并且被访问的变量没有被限制在final的，
 *     而且还可以在对象表达式中修改被访问变量的值（Java做不到）
 *
 * 2.与对象声明不同之处：
 *      对象声明是单例的，
 *      而匿名对象不是单例的（每次对象表达式被执行都会创建一个新的对象的实例）
 *
 * 3.对象表达式在 需要在匿名对象 中 重写多个方法 时是最有用的。
 */


fun main(args: Array<String>) {
    val window = Frame()

    window.addMouseListener(
            //对象表达式声明了一个类并且创建了一个类的实例
            object : MouseAdapter() {
                //声明一个继承MouseAdapter的匿名对象
                //重写MouseAdapter的方法
                override fun mouseClicked(e: MouseEvent) {
                    //..
                }

                override fun mouseEntered(e: MouseEvent) {
                    //..
                }
            })
    /**
     *  通常来说，匿名对象是不需要名字的，因为这个对象用作一个函数调用的参数。
     *  如果你需要给对象分配一个名字，可以将其存储到一个变量中
     */
    val listener = object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent?) {
            //..
        }

        override fun mouseEntered(e: MouseEvent) {
            //..
        }
    }
}

/**
 * 与Java的匿名类一样，在对象表达式的代码可以访问【创建它的函数】中的【变量】
 * 但是与Java不同，访问并没有限制在final变量，
 * 还可以在对象表达式中修改被访问变量的值。
 */
fun countClicks(window: Window) {
    var clickCount = 0 //声明局部变量，非final修饰

    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
            clickCount++ //更新变量的值
        }
    })
}