package com.congwiny.basic

/**
 * Created by congwiny on 2017/5/28.
 */
fun describe(obj: Any): String =
        //这表达式好流弊啊
        when (obj) {
            1 -> "one"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a String"
            else -> "Unknown"
        }

fun main(args: Array<String>) {
    println(describe(1))
    println(describe("Hello"))
    println(describe(1000L))
    println(describe(2))
    println(describe("other"))

    testInOperator()
}

//in 运算符
fun testInOperator() {
    /**
     * 使用in运算符来检测某个数字是否在指定区间内
     */
    val x = 10
    val y = 9
    if (x in 1..y + 1) {
        println("first in range")
    }

    //检测某个数字
    var list = listOf("a","b","c")
    if (-1 in 0..list.lastIndex){
        println("-1 is out of range")
    }

    //list.indices -> 0..size-1
    if (list.size !in list.indices){
        println("list size is out of valid list indices range too")
    }

    //in 区间迭代
    for (x in 1..5){
        print(x)
    }
    println()
    //或数列迭代 step
    for (x in 1..10 step 2){
        print(x)
    }
    println()
    //递减 downTo
    for (x in 9 downTo 0 step 3){
        print(x)
    }

}


