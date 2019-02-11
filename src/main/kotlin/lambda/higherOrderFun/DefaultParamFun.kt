package lambda.higherOrderFun

/**
 * 函数类型的 参数默认值
 */

fun <T> Collection<T>.joinToString1(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)//使用默认的toString方法将对象转换为字符串
    }
    result.append(postfix)
    return result.toString()
}

/**
 * 传递一个lambda 去指定如何将对象转换为字符串
 *
 * 声明函数类型的默认值并不需要特殊的语法 -- 只需要把lambda 作为值放在＝号后面。
 */
fun <T> Collection<T>.joinToString2(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = "",
        transform: (T) -> String = { it.toString() } //声明一个以lambda为默认值的函数类型的参数
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(transform(element)) //调用
    }
    result.append(postfix)
    return result.toString()
}

fun main(args: Array<String>) {
    val letters = listOf("Alpha", "Beta")
    println(letters.joinToString2()) //Alpha, Beta

    println(letters.joinToString2 { it.toLowerCase() }) //alpha, beta

    println(letters.joinToString2(separator = "! ", postfix = "! ",
            transform = { it.toUpperCase() })) //ALPHA! BETA!
}