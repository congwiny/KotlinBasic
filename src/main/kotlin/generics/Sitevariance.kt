package generics

/**
 * 使用点变型：在类型出现的地方指定变型
 *
 * Kotlin 的使用点变型直接对应Java 的限界通配符。
 * Kotlin 中的MutableList<out T>和Java中的MutableList<? extends T>是一个意思。
 * in 投影的MutableList<in T>对应到Java的MutableList<? super T>。
 */

/**
 * 带不变型类型参数的数据拷贝函数
 */
fun <T> copyData0(source: MutableList<T>, destination: MutableList<T>) {
    for (item in source) {
        destination.add(item)
    }
}

/**
 * 带不变型类型参数的数据拷贝函数
 *
 * 支持不同类型的列表，引入第二个泛型参数
 */
//来源的元素类型应该是目标元素类型的子类型
fun <T : R, R> copyData(source: MutableList<T>, destination: MutableList<R>) {
    for (item in source) {
        destination.add(item)
    }
}
/** ---------------------更优雅的方式，不需要引入第二个泛型参数---------------------------*/
/**
 * 带out投影类型参数的数据拷贝函数
 *
 * 可以为类型声明中类型参数任意的用法指定变型修饰符，这些用法包括：
 * 形参类型、局部变量类型、函数返回类型， 等等。
 * 这里发生的一切被称作类型投影：我们说source 不是一个常规的MutableList ，
 * 而是一个投影（受限）的MutableList 。只能调用返回类型是泛型类型参数的那些方法，
 * 或者严格地讲，只在out 位置使用它的方法。
 * 编译器禁止调用使用类型参数做实参（类型）的那些方法（在in位置使用类型参数）：
 */
fun <T> copyData1(source: MutableList<out T>, destination: MutableList<T>) {
    for (item in source) {
        destination.add(item)
    }
}

/**
 * 带in投影类型参数的数据拷贝函数
 */
//运行目标元素的类型是来源元素类型的超类型
fun <T> copyData2(source: MutableList<T>, destination: MutableList<in T>) {
    for (item in source) {
        destination.add(item)
    }
}

fun main(args: Array<String>) {
    val ints = mutableListOf(1, 2, 3)
    val anyItems = mutableListOf<Any>()
    copyData2(ints, anyItems) //可以调用这个函数，因为Int是Any的子类型
    println(anyItems)
}
