package com.congwiny.basic

/**
 * Created by congwiny on 2017/5/28.
 */

fun main(args: Array<String>) {
    //for 循环的使用
    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }

    //while 循环的使用
    /**
     * item at 0 is apple
     * item at 1 is banana
     * item at 2 is kiwi
     */
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }

}
