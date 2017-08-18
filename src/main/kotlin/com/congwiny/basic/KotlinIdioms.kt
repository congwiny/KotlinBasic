package com.congwiny.basic

/**
 * Created by congwiny on 2017/5/28.
 */
/***
 * 一些在Kotlin中广泛使用的语法习惯（习惯用法）
 */
//1.创建 DTOs（ POJOs/POCOs）
/**
 * 会为Customer类提供以下功能：
 * 所有属性的 getters （ 对于 var 定义的还有 setters）
 * equals()
 * hashCode()
 * toString()
 * copy()
 * 所有属性的 component1() 、 component2() ……等等（ 参见数据类）
 */
data class Customer(val name: String, val email: String)

//函数的默认参数
fun foo(a: Int = 0, b: String = "") {}


//过滤list
fun filterList() {
    val list = listOf(-1, -2, 0, 1, 3)
    //lambda表达式，取所有的正数
    val positives = list.filter { x -> x > 0 }
    //更短可以写成如下
    val positives2 = list.filter { it > 0 }
}

fun otherIdioms() {
    //String内插（String Interpolation[插入；篡改；添写]）
    val name = "congwiny"
    println("Name $name")

    //实例类型判断
    /**
     * when (x) {
     *      is Foo -> ...
     *      is Bar -> ...
     *      else -> ...
     *  }
     */


}

fun main(args: Array<String>) {
    otherIdioms()
}

