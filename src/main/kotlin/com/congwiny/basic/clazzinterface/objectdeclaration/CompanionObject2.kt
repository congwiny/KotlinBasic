package com.congwiny.basic.clazzinterface.objectdeclaration

/**
 * 伴生对象的扩展
 * 伴生对象上也可以定义扩展函数。
 * 比如说：如果类C中有一个伴生对象，并且在C.Companion上定义一个扩展函数func,
 * 可以通过C.func()来调用这个扩展函数。
 *
 * 这么做目的是为了把一个模块和某种特定的数据格式解耦。
 */

/**
 * 为了能够让你的类定义扩展，必须在其中声明一个伴生对象，即使是空的
 */
class Person1(val firstName: String, val lastName: String) {
    companion object{//声明一个空的伴生对象

    }
}

//声明一个扩展函数
fun Person1.Companion.fromJSON(json:String){
    //...
}

fun main(args: Array<String>) {
    //使用Person1类名调用扩展的函数，而不是用Person1类的对象哦
    val p = Person1.fromJSON("{name:'congwiny'}")
}
