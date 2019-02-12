package generics

/**
 * 类型参数约束
 */
fun <T : Number> oneHalf(value: T): Double {
    return value.toDouble() / 2.0
}

fun <T : Comparable<T>> max(first: T, second: T): T {
    return if (first > second) first else second
}

/**
 *  如果同一类型参数需要多个上界，我们需要一个单独的 where子句：
 */
fun <T> ensureTrailingPeriod(seq: T)
        where T : CharSequence, T : Appendable {
    //调用CharSequence接口定义的扩展函数
    if (!seq.endsWith('.')) {
        //调用Appendable接口的方法
        seq.append('.')
    }
}

/**
 * 没有指定上界的类型形参将会使用 Any? 这个默认的上界
 */
class Processor<T> {
    fun process(value: T) {
        value?.hashCode() //"value"是可空的，所以要用安全调用
    }
}

/**
 * 如果你想保证替换类型形参的始终是非空类型，可以通过指定一个约束来实现。
 * 如果你除了可空性之外没有任何限制，可以使用Any 代替默认的Any？作为上界：
 */
class Processor2<T : Any> { //指定非空上界
    fun process(value: T) { //类型T的值现在是非空的
        value.hashCode()
    }
}

fun main(args: Array<String>) {
    println(oneHalf(3)) //1.5
    println(max("kotlin", "java")) //kotlin

    val helloWorld = StringBuilder("Hello World")
    ensureTrailingPeriod(helloWorld)
    println(helloWorld) //Hello World.

    val nullableStringProcessor = Processor<String?>() //可空类型"String?"被用来替换T
    nullableStringProcessor.process(null) //使用"null"作为"value"实参的代码可以编译
}