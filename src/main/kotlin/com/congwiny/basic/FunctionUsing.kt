/**
 * 使用@file:JvmName注解，修改文件类名
 * 必须将这句话放到文件的开头
 */
@file:JvmName("StringFunctions")

package com.congwiny.basic

//相当于Java中的public static final
const val UNIX_LINE_SEPARATOR = "\n"
val name = "congwiny"

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3)
    println(list)
    //在编译器里能看到参数的名字，但是在源文件中无法看到对应的参数名是什么
    println(joinToString(list, "; ", "(", ")"))

    //使用命名参数（不去查看函数的声明就知道对应的参数）
    println(joinToString(list, separator = "; ", prefix = "(", postfix = ")"))


    println(CallFunction.joinToString(list, "; ", "(", ")"))
    //named arguments are not allowed for non-Kotlin functions
    //println(CallFunction.joinToString(list, separator = "; ", prefix = "(", postfix = ")"))

    //使用函数的默认参数值
    println(joinToString2(list))
    println(joinToString2(list,"; "))
    println(joinToString2(list,prefix = "(",postfix = ")"))
}


fun <T> joinToString(collection: Collection<T>,
                     separator: String,
                     prefix: String,
                     postfix: String): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}


/**
 * 指定默认参数值的函数
 *
 * 在Java中调用Kotlin方法时，由于Java没有参数默认值的概念，
 * 使用@JvmOverloads注解，让编译器生成多个Java重载函数。
 * String joinToString(Collection<T> collection, String separator, String prefix, String postfix);
 * String joinToString(Collection<T> collection, String separator, String prefix);
 * String joinToString(Collection<T> collection, String separator);
 * String joinToString(Collection<T> collection);
 */
@JvmOverloads
fun <T> joinToString2(collection: Collection<T>,
                     separator: String = ", ",
                     prefix: String = "",
                     postfix: String = ""): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}