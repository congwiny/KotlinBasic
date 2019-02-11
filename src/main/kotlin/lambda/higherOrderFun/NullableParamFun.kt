package lambda.higherOrderFun

/**
 * 函数类型的 可空参数
 */

fun <T> Collection<T>.joinToString3(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = "",
        transform: ((T) -> String)? = null //声明一个函数类型的可空参数
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        //使用安全调用语法调用函数
        val str = transform?.invoke(element)
                ?: element.toString() //使用Elvis运算符处理回调没有被指定的情况
        result.append(str)
    }
    result.append(postfix)
    return result.toString()
}

fun main(args: Array<String>) {
    val letters = listOf("Alpha", "Beta")
    println(letters.joinToString3()) //Alpha, Beta

    println(letters.joinToString3 { it.toLowerCase() }) //alpha, beta

    println(letters.joinToString3(separator = "! ", postfix = "! ",
            transform = { it.toUpperCase() })) //ALPHA! BETA!
}
