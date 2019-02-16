package annotations

/**
 * Kotlin 提供了各种注解来控制Kotlin编写的声明如何编译成字节码并暴露给Java调用者。
 * 其中一些注解代替了Java 语言中对应的关键字：
 * 比如，注解@Volatile 和@Strictfp 直接充当了Java的关键字volatile和Strictfp的替身。
 * 其他的注解则是被用来改变Kotlin 声明对Java 调用者的可见性：
 *  @JvmName 将改变由Kotlin 生成的Java 方法或字段的名称。
 *  @JvmStatic 能被用在对象声明或者伴生对象的方法上，把它们暴露成Java的静态方法。
 *  @JvmOverloads 指导Kotlin 编译器为带默认参数值的函数生成多个重载（函数）。
 *  @JvmField 可以应用于一个属性，把这个属性暴露成一个没有访问器的公有Java 字段。
 */
class A {
    companion object { //不再需要显示地指明伴生对象的名称
        /**
         * @JvmStatic 能被用在对象声明或者伴生对象的方法上，把它们暴露成Java 的静态方法。
         */
        @JvmStatic
        fun bar() {
            println("Companion object called")
        }
    }
}

fun main(args: Array<String>) {
    //在kotlin中，不需要@JvmStatic注解也是可以这么写的
    A.bar()
}