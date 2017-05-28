package com.congwiny.basic

/**
 * Created by congwiny on 2017/5/28.
 */
/***
 * 对集合进行迭代
 */

fun main(args: Array<String>) {
    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }

    //使用lambda表达式来过滤(filter)和映射(map)集合：
    val fruits = listOf("banana", "avocado", "apple", "kiwi")

    /**
     * APPLE
     * AVOCADO
     */
    fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }


}