package com.congwiny.basic

import java.lang.IllegalArgumentException

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
    print(boxedB == anotherBoxedB) // ！！！输出“true”！！！
    print(boxedB === anotherBoxedB) // ！！！输出“false”！！！

    //显示转换
    val c: Byte = 1
    // val i:Int = c //错误，较小的类型不能隐式转换为较大的类型
    //可以显示转换来拓宽数字
    val i: Int = c.toInt()
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
    /***
     * 这是完整的位运算列表（只⽤于 Int 和 Long ）：
    显式转换
    ———————
    运算
    shl(bits) ‒ 有符号左移 (Java 的 << )
    shr(bits) ‒ 有符号右移 (Java 的 >> )
    ushr(bits) ‒ ⽆符号右移 (Java 的 >>> )
    and(bits) ‒ 位与
    or(bits) ‒ 位或
    xor(bits) ‒ 位异或
    inv() ‒ 位⾮
     */
    val x = (1 shl 2) and 0x000FF000

    //字符
    //字符用Char类型表示，他们不能直接当做数字
    fun check(c: Char) {
        // if(c==1){//错误，类型不兼容

        //}

        /***
         * 字符字面值用单引号括起来
         *
         * 特殊字符可以⽤反斜杠转义。⽀持这⼏个转义序列：\t 、\b 、\n 、\r 、\' 、\" 、\\ 和 \$ 。
         * 编码其他字符要⽤Unicode 转义序列语法：'\uFF00'
         */

        if (c == '1') {

        }
    }

    /**
     * 布尔。布尔用Boolean类型表示，它有两个值：true和false
     * 若需要可空引用，布尔会被装箱
     *
     * 内置的布尔运算有：
    || ‒ 短路逻辑或
    && ‒ 短路逻辑与
    ! - 逻辑⾮
     */


    //数组--------------------------------------------------------
    /**
     * 数组在 Kotlin 中使⽤ Array 类来表⽰，
     * 它定义了 get 和 set 函数（按照运算符重载约定这会转变为 [] ）和 size 属性，
     * 以及⼀些其他有⽤的成员函数：
     *      val size: Int
            operator fun get(index: Int): T
            operator fun set(index: Int, value: T): Unit
            operator fun iterator(): Iterator<T>
     */
    // arrayOf() 来创建⼀个数组并传递元素值给它
    val arr = arrayOf(10, 20, 30)
    println(arr[1]) //20

    //可以⽤于创建⼀个指定⼤⼩、元素都为空的数组。
    val arrayOfNulls = arrayOfNulls<String>(9)
    arrayOfNulls.iterator().forEach { print(it) }

    println()
    /**
     *    另⼀个选项是⽤接受数组⼤⼩和⼀个函数参数的⼯⼚函数,
     *    ⽤作参数的函数能够返回 给定索引的每个元素初始值：
     */
    // 创建⼀个 Array<String> 初始化为 ["0", "1", "4", "9", "16"]
    val asc = Array(5, { i -> (i * i).toString() })
    asc.iterator().forEach { print(it) }

    /**
     * 注意: 与 Java 不同的是，Kotlin 中数组是不型变的（invariant）。
     * 这意味着 Kotlin 不让我们把 Array<String> 赋值给 Array<Any> ，
     * 以防⽌可能的运⾏时失败（但是你可以使⽤ Array<out Any> , 参⻅类型投影）。
     *
     * Kotlin 也有⽆装箱开销的专⻔的类来表⽰原⽣类型数组: ByteArray 、ShortArray 、IntArray 等等。
     * 这些类和 Array 并没有继承关系，但它们有同样的⽅法属性集。
     * 它们也都有相应的⼯⼚⽅法:
        val x: IntArray = intArrayOf(1, 2, 3)
        x[0] = x[1] + x[2]
     */

    //字符串--------------------------------------------
    /**
     * 字符串⽤ String 类型表⽰。字符串是不可变的。
     * 字符串的元素⸺字符可以使⽤索引运算符访问: s[i] 。
     * 可以⽤ for 循环迭代字符串:
     */
    val str = "abcdefg"
    for (c in str){
        println(c)
    }

    /**
     * Kotlin 有两种类型的字符串字⾯值:
     *      转义字符串 ->包含转义字符，转义采用传统的反斜杠方式
     *
     *      原生字符串 ->可以包含换行和任意文本。使用三个引号(""")分界符括起来，
     *      内部没有转义并且可以包含换⾏和任何其他字符
     */
    val s = "Hello world!\n"

    val text = """
                for (c in "foo")
                    print(c)
                """
    println(text)

    //你可以通过 trimMargin() 函数去除前导空格：
    val text2 = """
        |Tell me and I forget.
        |Teach me and I remember.
        |Involve me and I learn.
        |(Benjamin Franklin)
        """.trimMargin()
    //默认 | ⽤作边界前缀，
    // 但你可以选择其他字符并作为参数传⼊，⽐如 trimMargin(">") 。
    println(text2)

    /**
     * 字符串模板
     * 字符串可以包含模板表达式 ，即⼀些⼩段代码，会求值并把结果合并到字符串中。
     * 模板表达式以美元符（ $ ）开头，由⼀个简单的名字构成:
     *
     * 原⽣字符串和转义字符串内部都⽀持模板。
     */
    val i1 = 10
    val s1 = "i = $i1" // 求值结果为 "i = 10"

    //或者⽤花括号扩起来的任意表达式:
    val s2 = "abc"
    val str1 = "$s2.length is ${s2.length}" // 求值结果为 "abc.length is 3"

    //如果你需要在原⽣字符串中表⽰字⾯值 $ 字符（它不⽀持反斜杠转义），
    // 你可以⽤下列语法：
    val price = """
    ${'$'}9.99
    """
    println(price)
}


//显示把字符转换为Int数字
//当需要可空引⽤时，像数字、字符会被装箱。装箱操作不会保留同⼀性【个人认为是值相等+类型相等】
fun decimalDigitValue(c: Char): Int {
    if (c !in '0'..'9')
        throw IllegalArgumentException("Out of range")
    return c.toInt() - "0".toInt() //显示转换为数字
}