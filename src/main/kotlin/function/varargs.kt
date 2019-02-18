package function

/**
 * 可变数量的参数（Varargs）
 */
fun main(args: Array<String>) {
    val b = arrayOf("b", "c", "d")
    hello(*b)

    val a = arrayOf(1, 2, 3)
    /**
     * 当我们调用 vararg-函数时，我们可以一个接一个地传参，例如 asList(1, 2, 3)，
     * 或者，如果我们已经有一个数组并希望将其内容传给该函数，
     * 我们使用伸展（spread）操作符（在数组前面加 *）
     */
    val list = asList(-1, 0, *a, 4) //允许将可变数量的参数传递给函数
    println(list) //[-1, 0, 1, 2, 3, 4]

    /**
     * 可以通过使用星号操作符将可变数量参数（vararg） 以命名形式传入：
     */
    val list2 = asList(ts = *a)
    println(list2) //[1, 2, 3]
}

/**
 * 函数的参数（通常是最后一个）可以用 vararg 修饰符标记：
 */
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array（ts变量具有类型 Array<out T>）
        result.add(t)
    return result
}

fun hello(vararg args: String) {
    for (obj in args) {
        println(obj)
    }
}