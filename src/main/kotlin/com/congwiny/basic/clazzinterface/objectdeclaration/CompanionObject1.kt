package com.congwiny.basic.clazzinterface.objectdeclaration

/**
 * Kotlin中的类不能拥有静态成员；
 * Java的"static"关键字并不是Kotlin语言的一部分；
 *
 * Kotlin使用包级别函数能够替代大部分Java的静态方法
 * 使用对象声明在有些情况下也能够替代Java的静态方法和静态字段
 *
 * 在大多数情况下，推荐使用顶层函数。但是顶层函数不能访问类的private成员.
 * 我们知道，要访问类中的private成员，只能是把一个函数写到类里面去，这不就成了类的方法了吗？
 * 这种情况需要类的实例来调用这个函数了，就不能达到想Java一样调用static方法的效果了。
 *
 * 那么该怎么做呢？
 * 使用类的声明--companion object（伴生对象）
 * 这样做就可直接通过【容器类的名称】来访问这个【伴生对象】的方法和属性了，
 * 不再需要显示地指明伴生对象的名称。最终的语法看起来非常想Java中的静态方法调用。
 *
 * 伴生对象也是单例的。
 *
 * //kotlin的设计，都和设计模式相关啊
 * 伴生对象可以访问类中的所有private成员，包括private构造方法，它是显示工厂模式的理想选择。
 * （工厂模式，类的实例不能从外部创建，只能从内部生产，防止外部创建的问题发生）
 */
class A {
    companion object { //不再需要显示地指明伴生对象的名称
        fun bar() {
            println("Companion object called")
        }
    }
}

fun main(args: Array<String>) {
    //看起来非常想Java中的静态方法调用
    A.bar()

    //使用工厂方法来替代多个从构造方法
    val subscribingUser = User.newSubscribingUser("bob@gmail.com")
    val facebookUser = User.newFacebookUser(4)
    println(subscribingUser.nickname)

    //可以通过两种方式来调用fromJSON
    val worker1 = Worker.Loader.fromJSON("{name:'Kitty'}")
    val worker2 = Worker.fromJSON("{name:'Kitty'}")
}

//定义一个拥有多个从构造方法的类
class User0 {
    val nickname: String

    constructor(email: String) {
        nickname = email.substringBefore('@')
    }

    constructor(facebookId: Int) {
        nickname = facebookId.toString()
    }
}

/**
 * User的实例通过工厂方法来创建，而不是通过多个从构造方法
 *
 * 此外要注意：
 * 1.工厂方法能够返回声明这个方法的类（伴生对象的容器类）的子类
 * 2.通过工厂方法能够避免创建新的对象（使用自动缓存的对象）
 * 3.伴生对象成员在子类中不能被重写
 *
 * 是不是感觉伴生对象大法好了。。
 */
class User private constructor(val nickname: String) {
    //将主构造方法标记为私有
    //伴生对象声明
    companion object {
        fun newSubscribingUser(email: String) =
                User(email.substringBefore('@'))

        //用工厂方法通过Facebook账号来创建一个新用户
        fun newFacebookUser(accountId: Int) =
                User(accountId.toString())

    }
}

/**
 * 伴生对象是声明在类中的普通对象。它可以有名字，有扩展函数或属性，它还可以实现一个接口
 * 在大多数情况下，通过【（包含伴生对象）的类】的名字来引用伴生对象，所以不必关心它的名字
 * 如果，你需要也可以指明，就像下面代码的companion object Loader
 * 如果你省略了伴生对象的名字，默认的名字会分配为Companion
 */
class Worker(val name: String) {
    companion object Loader {
        fun fromJSON(jsonText: String): Worker = Worker(jsonText)
    }
}

/**
 * 在伴生对象中实现接口
 * 可以直接将【包含伴生对象的类】的名字当作【实现了该接口的对象实例】来使用
 *
 * 注意：
 * 1.如果伴生对象没有命名，在Java代码中它可以通过Companion引用来访问
 * Teacher.Companion.fromJson(...) //java代码
 * 2.Kotlin可以访问在Java类中声明的静态方法和字段，使用与Java相同的语法
 */
interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Teacher(val name: String) {
    //实现接口的伴生对象
    companion object : JSONFactory<Teacher> {
        override fun fromJSON(jsonText: String): Teacher = Teacher(jsonText)
    }
}

fun <T> loadFromJSON(factory: JSONFactory<T>) {//传入实现了该接口的对象实例
    //...
}

fun testLoadFromJSON() {
    //可以直接将【包含伴生对象的类】的名字当作【实现了该接口的对象实例】来使用
    loadFromJSON(Teacher) //Teacher类的名字被当作JSONFactory的实例
}
