package com.congwiny.basic

/**
 * Created by congwiny on 2017/6/12.
 */


fun main(args: Array<String>) {

    //在 Kotlin 中，if是⼀个表达式，即它会返回⼀个值。
    // 因此就不需要三元运算符（条件 ? 然后 : 否则），因为普通的 if 就能胜任这个⻆⾊。

    // 传统⽤法
    val a = 1
    val b = 3

    var max1 = a
    if (a < b) max1 = b

// With else
    var max2: Int
    if (a > b) {
        max2 = a
    } else {
        max2 = b
    }

    /**作为表达式
     *
     * 如果你使⽤ if 作为表达式⽽不是语句（例如：返回它的值或者 把它赋给变量），
     * 该表达式需要有 else 分⽀。
     */
    val max = if (a > b) a else b

    //if的分⽀可以是代码块，最后的表达式作为该块的值：
    val max3 = if (a > b) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }

    //when表达式
    //when取代了类C语言的switch操作符，最简单形式如下
    when(max){
        1 -> println("max==1")
        3 -> println("max==3")
        else ->{
            println ("neither 1 nor 3")
        }
    }
    //如果很多分⽀需要⽤相同的⽅式处理，则可以把多个分⽀条件放在⼀起，⽤逗号分隔：
    when (max) {
        0, 1 -> println("x == 0 or x == 1")
        else -> println("otherwise")
    }

    //我们可以⽤任意表达式（⽽不只是常量）作为分⽀条件
    when (max) {
        parseInt("3") -> println("s encodes x")
        else -> println("s does not encode x")
    }

    //我们也可以检测⼀个值在（in）或者不在（!in）⼀个区间或者集合中：
    val validNumbers = arrayOf(1,2,3)
    when (max) {
        in 4..10 -> println("x is in the range")
        in validNumbers -> println("x is valid")
        !in 10..20 -> println("x is outside the range")
        else -> println("none of the above")
    }

    println(hasPrefix("prefix_abc"))


    /**
     * when 也可以⽤来取代 if-else if链。
     * 如果不提供参数，所有的分⽀条件都是简单的布尔表达式，
     * ⽽当⼀个分⽀的条件为真时则执⾏该分⽀：
     */
    val str = "abc"
    when {
        str.startsWith("a") -> print("aaa")
        str.startsWith("b") -> print("bbb")
        else -> print("str is funny")
    }


    //For循环（For Loops）
    /**
     * for 循环可以对任何提供迭代器（iterator）的对象进⾏遍历，语法如下:
     * for (item in collection) print(item)
     *
     * 循环体可以是⼀个代码块。
        for (item: Int in ints) {
            // ……
        }
     */

    /**
     *对数组的 for 循环会被编译为并不创建迭代器的基于索引的循环。
     *如果你想要通过索引遍历⼀个数组或者⼀个 list，你可以这么做：
     */
    val array = arrayOf(1,2,3)
    //注意这种“在区间上遍历”会编译成优化的实现⽽不会创建额外对象。
    for (i in array.indices) {
        print(array[i])
    }

    println()
    //库函数with index...牛逼哄哄啊
    for ((index, value) in array.withIndex()) {
        println("the element at $index is $value")
    }

    //while和do...while 和java一样
    //break和continue也与java一样

    //返回和跳转
    //Kotlin 有三种结构化跳转表达式：
    /**
     * return。默认从最直接包围它的函数或者匿名函
     * break。终⽌最直接包围它的循环。
     * continue。继续下⼀次最直接包围它的循环。
     */

    //所有这些表达式都可以⽤作更⼤表达式的⼀部分：
    //TODO 这不知道啥意思。。。
    val s = "abc"?:return

    //return at labels (return又多了那么多玩法。。。)
    /**
     * Kotlin 有函数字⾯量、局部函数和对象表达式。
     * 因此 Kotlin 的函数可以被嵌套。
     * 标签限制的 return 允许我们从外层函数返回。
     * 最重要的⼀个⽤途就是从lambda 表达式中返回
     */
    foo2()

    /**
     *如果我们需要从 lambda 表达式中返回，我们必须给它加标签并⽤以限制 return。
     */
    foo3()

    /**
     * 通常情况下使⽤隐式标签更⽅便。
     * 该标签与接受该 lambda 的函数同名。
     */
    foo4()

    /**
     * 我们⽤⼀个匿名函数替代 lambda 表达式。
     * 匿名函数内部的 return 语句将从该匿名函数⾃⾝返回
     */
    foo5()

    /**
     * Note:
     * 当要返⼀个回值的时候，解析器优先选⽤【标签限制的】return，即
        return@a 1
        意为“从标签 @a 返回 1”，⽽不是“返回⼀个标签标注的表达式 (@a 1) ”。
     */
}

fun foo5(){
    val ints = arrayOf(1,2,0,3)
    ints.forEach(fun(value:Int){
        if (value==0) return
        println("funfun"+value)
    })
}

fun foo4(){
    val ints = arrayOf(1,2,0,3)
    ints.forEach{
        if (it==2) return@forEach
        println("lit@"+it)
    }
}

/**
 * lit@1
 * lit@0
 * lit@3
 */

fun foo3(){
    val ints = arrayOf(1,2,0,3)
    ints.forEach lit@ {
        if (it==2) return@lit
        println("lit@"+it)
    }
}

fun foo2() {
    val ints = arrayOf(1,2,0,3)
    ints.forEach {
        if (it == 0)
        /**
         * 这个 return 表达式从最直接包围它的函数即 foo 中返回。
         * 注意，这种【⾮局部的返回】只⽀持传给【内联函数的】 lambda 表达式。
         */
        return
        println(it)
    }
}


/**
 * 另⼀种可能性是检测⼀个值是（is）或者不是（!is）⼀个特定类型的值。
 * 注意：由于智能转换，你可以访问该类型的⽅法和属性⽽⽆需 任何额外的检测。
 */
fun hasPrefix(x: Any) = when(x) {
    is String -> x.startsWith("prefix")
    else -> false
}