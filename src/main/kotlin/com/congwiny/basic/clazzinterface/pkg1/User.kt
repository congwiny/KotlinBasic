package com.congwiny.basic.clazzinterface.pkg1

interface User {
    /**
     * 接口中可以声明抽象属性。
     * 这意味着实现User接口的类需要提供一个取得nickname值的方式（实现这个抽象属性）
     * 要么这个在实现类中支持字段，要么通过实现类的getter，要么赋值
     * 接口并不包含任何状态，因此只有实现这个接口的类在需要的情况下才会存储值。
     */
    val nickname: String //nickname这个属性需要在子类中重写
    /**
     * 除了抽象属性声明外，接口还可以包含具有getter和setter的属性，
     * 只要他们没有引用一个支持字段（支持字段需要在接口中存储状态，而这是不允许的）
     * 下面例子属性没有支持字段：结果值在每次访问时通过计算得到（支持字段-->使用field访问）
     */
    val qqemail: String
        get() = nickname + "@qq.com"//自定义getter属性可以被继承
}

//主构造方法属性实现抽象属性
class PrivateUser(override val nickname: String) : User

class SubscribingUser(val email: String) : User {
    //自定义getter实现抽象属性
    override val nickname: String
        get() = email.substringBefore('@')
}

class FacebookUser(val accoutId: Int) : User {
    override val nickname = "FaceBook" //属性初始化实现抽象属性
}

class Home(val name: String) {
    /**
     * 在setter的函数体中，使用了特殊的标识符field来访问支持字段的值。
     * 在getter中只能读取值，而在setter中既能读取也能修改它。
     */
    var address: String = "unspecified"
        set(value) {
            println("""Address was changed for $name:
            "$field" -> "$value".""".trimIndent())
            field = value //【显示地引用其他对象】
        }
}

fun main(args: Array<String>) {
    /**
     * Address was changed for congwiny:
    "unspecified" -> "qingnianlu 502".
     */
    val home = Home("congwiny")
    home.address = "qingnianlu 502"

    /**
     * 有支持字段的属性和没有的有什么区别？
     * 访问属性的方式不只有支持字段一种，还有自定义getter的方式.
     * 如果你的属性【显示地引用其他对象】或者使用默认的访问器实现，编译器会为属性生成支持字段。
     */

    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Hello")
    println(lengthCounter.counter) //5
}

class LengthCounter {
    //访问器的可见性默认与属性的可见性相同
    var counter: Int = 0
        private set //修改访问器的可见性为private(意味着，不能在类的外部修改此属性)

    fun addWord(word: String) {
        counter += word.length
    }
}