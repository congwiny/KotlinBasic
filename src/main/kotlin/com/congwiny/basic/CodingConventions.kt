package com.congwiny.basic

/**
 * Created by congwiny on 2017/6/8.
 * 编码规范
 *
 * 命名风格（Naming Style）
 * 如果拿不准的时候，默认使⽤Java的编码规范，⽐如
 *  使⽤驼峰法命名（并避免命名含有下划线）
    类型名以⼤写字⺟开头
    ⽅法和属性以⼩写字⺟开头
    使⽤ 4 个空格缩进
    公有函数应撰写函数⽂档，这样这些⽂档才会出现在 Kotlin Doc 中
 */

//Colon(冒号)
/**
 * 以前自己写的时候都不注意这个细节，以后要严格遵守呀
 * 冒号前有空格
 *      类型 : 超类型
 * 冒号前无空格
 *      类型实例: 类型
 * */
interface Foo<out T : Any>: Bar{
    fun foo(a: Int): T
}

interface Bar

//lambda表达式
/**
 * 在lambda表达式中，大括号左右要加空格，分隔参数与代码体的箭头左右也要加空格
    lambda表达式应尽可能不要写在圆括号中
 */
fun main(args: Array<String>) {
    val list = arrayListOf(1,5,21,3)
    //在⾮嵌套的短lambda表达式中，最好使⽤约定俗成的默认参数 it 来替代显式声明参数名 。
    //在嵌套的有参数的lambda表达式中，参数应该总是显式声明。
    list.filter { it>10 } .map { element -> element*2 }
}

//类头格式化

//有少数⼏个参数的构造函数的类可以写成⼀⾏：
class Person(id: Int, name: String)

/**
 * 具有较⻓类头的类应该格式化，以使每个主构造函数参数位于带有缩进的单独⼀⾏中。
 * 此外，右括号应该另起⼀⾏。
 * 如果我们使⽤继承，那么超类构造函数调⽤或者实现接⼝列表 应位于与括号相同的⾏上：
 * */
/**
class Person2(
        id: Int,
        name: String,
        surname: String
) : Human(id, name) {
// ……
}
 */

//对于多个接⼝，应⾸先放置超类构造函数调⽤，然后每个接⼝应位于不同的⾏中：
//构造函数参数可以使⽤常规缩进或连续缩进（双倍的常规缩进）。

/**
 * class Person(
        id: Int,
        name: String,
        surname: String
        ) : Human(id, name),
            KotlinMaker {
            // ……
        }
 */

//如果函数返回Unit类型，该返回类型应该省略
fun foo(){ //省略了 ":Unit"

}

//TODO Functions VS Properties