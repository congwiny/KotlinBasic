package collections
/**
 * 给集合类添加一个扩展函数，这样集合对象就可以向使用集合类的成员函数去调用joinToString了
 *
 * 接收者类型Collection必须放在collections的包下
 */
fun <T> Collection<T>.joinToString3(collection: Collection<T>,
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
