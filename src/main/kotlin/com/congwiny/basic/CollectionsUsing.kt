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

    //map
    //遍历map (Traversing a map of pairs)
    //可变的HashMap
    val map = hashMapOf<String, Int>()
    map.put("one", 1)
    map.put("two", 2)

    for ((k, v) in map) {
        System.out.println("$k -> $v")
    }

    map["one"]=3
    System.out.println("map[one] after modify= ${map["one"]}")


    //只读的map
    val map2 = mapOf("one" to 1,"two" to 2)
    //只能访问map，不能map2["one"]=1
    println(map2["one"])

    val aaa: String = "asd"
}