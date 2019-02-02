package operator.overloading

import java.time.LocalDate

/**
 * 在Kotlin中， for循环中也可以使用in运算符，和做区间检查一样。
 * 但是在这种情况下它的含义是不同的：它被用来执行迭代。
 * 这意味着一个诸如for(x in list) ｛．．．｝将被转换成list.iterator()的调用，
 * 然后就像在Java中一样，在它上面重复调用hasNext和next方法。
 *
 * 在Kotlin中，这也是一种约定，这意味着iterator方法可以被定义为扩展函数。
 * 这就解释了为什么可以遍历一个常规的Java字符串：标准库已经为CharSequence定义了一个扩展函数iterator ，而它是String的父类：
 * operator fun CharSequence.iterator(): CharIterator
 **/

fun main(args: Array<String>) {
    for (c in "abc") {
        println(c)
    }

    val newYear = LocalDate.ofYearDay(2017, 1)
    val daysOff = newYear.minusDays(1)..newYear //返回一个LocalDate区间
    for (dayOff in daysOff) { //对应的iterator函数实现后， 遍历daysOff
        //2016-12-31
        //2017-01-01
        println(dayOff)
    }
}

//迭代LocalDate泛型区间的函数
operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
        object : Iterator<LocalDate> { //这个对象实现了遍历LocalDate元素的Iterator
            var current = start
            override fun hasNext() =
            //注意，这里日期用到了compareTo约定
                    current <= endInclusive

            //在修改前返回当前日期作为结果
            override fun next() = current.apply {
                //把当前日期增加一天
                current = plusDays(1)
            }
        }
