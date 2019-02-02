package operator.overloading

import java.time.LocalDate

fun main(args: Array<String>) {
    //rangeTo是Comparable的一个扩展函数
    //LocalDate实现了ChronoLocalDate接口，ChronoLocalDate接口继承自“Comparable”接口
    //所以能直接使用“..”运算符，不需要额外定义
    val now = LocalDate.now()
    val vacation = now..now.plusDays(10)
    println(now.plusWeeks(1) in vacation) //true

    //rangeTo运算符的优先级低于算术运算符，但是最好把参数括起来以免混淆
    val n = 9
    println(0..(n + 1)) //0..10

    //必须把区间表达式括起来才能调用forEach，不然编译不通过
    (0..n).forEach { print(it) } //0123456789
}