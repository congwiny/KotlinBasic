package com.congwiny.basic

/**
 * Created by congwiny on 2017/6/9.
 */
/**
 * 基本类型
 * 在Kotlin中，所有东西都是对象，在这个意义上讲，我们可以在任何变量上调用成员函数和属性
 * */

/***
 * 内置数字类型 6种
 * Type     Bit width
    Double  64
    Float   32
    Long    64
    Int     32
    Short   16
    Byte    8
 *  都是x2
 *  注意：在Kotlin中字符不是数字
 * */

/**
 * 十进制：123 (Long类型用大写)
 * 十六进制：0x0F
 * 二进制: ob0001001
 *
 * 注意：Kotlin不支持八进制
 * 浮点数
 * 默认Double类型：123.4 123.5e10
 * Float用f或者F标记: 123.5f
 * */

/**
 * kotlin支持 "==="
 * == (equals)  用于比较判断两者相等
 *
   ===用于严格比较判断两者严格相等
   ===严格比较，要求进行比较的操作数必须类型一致，不一致时返回flase。
 * **/

fun main(args: Array<String>) {
    val a: Int = 10000
    println(a === a) // 输出“true”
    print(a == a) // 输出“true”

    val boxedA: Int = a
    val anotherBoxedA: Int = a
    println(boxedA === anotherBoxedA) // ！！！输出“true”！！！
    println(boxedA == anotherBoxedA)// ！！！输出“true”！！！


    val boxedB: Int? = a
    val anotherBoxedB: Int? = a
    print(boxedB === anotherBoxedB) // ！！！输出“false”！！！

    //显示转换
    val c:Byte = 1
   // val i:Int = c //错误，较小的类型不能隐式转换为较大的类型
    //可以显示转换来拓宽数字
    val i:Int = c.toInt()
    /**
     * 每个数字类型⽀持如下的转换:
        c/i.toByte(): Byte
        c/i.toShort(): Short
        c/i.toInt(): Int
        c/i.toLong(): Long
        c/i.toFloat(): Float
        c/i.toDouble(): Double
        c/i.toChar(): Char
     */

    //运算时，类型会从上下文推断出来，隐士转换
    val l = 1L + 3 //Long +Int -->Long

    //运算
}
