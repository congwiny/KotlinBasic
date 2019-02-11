package lambda.higherOrderFun

/**
 * filter函数以一个判断式作为参数。
 * 判断式的类型是一个函数，以字符作为参数并返回boolean类型的值。
 */
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0..length - 1) {
        val element = get(index)
        //调用 作为参数 传递给“predicate”的 函数
        if (predicate(element)) sb.append(element)

    }
    return sb.toString()
}

fun main(args: Array<String>) {
    /**
     * 传递一个lambda作为“predicate”参数
     */
    println("ab1c".filter { it in 'a'..'z' }) //abc
    println("ab1c".filter { it !in 'a'..'z' }) //1
}