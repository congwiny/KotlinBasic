package generics

/**
 * 声明带实化类型参数的函数
 */

/**
 * 通常情况下都是这样，只有一种例外可以避免这种限制：
 * 内联函数。内联函数的类型形参能够被实化，意味着你可以在运行时引用实际的类型实参。
 *
 * 如果用inline 关键字标记一个函数，编译器会把每一次函数调用都换成函数实际的代码实现。
 * 使用内联函数还可能提升性能，如果该函数使用了lambda实参：lambda 的代码也会内联，
 * 所以不会创建任何匿名类。
 *
 * inline 函数大显身手的另一种场景：它们的类型参数可以被实化。
 */
//fun <T> isA(value: Any) = value is T //Cannot check for instance of erased type: T

/**
 * isA 函数声明成inline并且用reified标记类型参数，
 * 你就能够用该函数检查value是不是T的实例。
 */
inline fun <reified T> isA(value: Any) = value is T

fun main(args: Array<String>) {
    println(isA<String>("abc")) //true
    println(isA<String>(123)) //false
}