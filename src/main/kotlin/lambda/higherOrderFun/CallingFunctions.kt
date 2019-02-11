package lambda.higherOrderFun

/**
 * 调用作为参数的函数
 */

fun twoAndThree(operation: (Int, Int) -> Int) { //函数类型参数
    /**
     * 调用 作为参数 的函数和调用普通函数的语法是一样的：
     * 把括号放在函数名后，并把参数放在括号内。
     */
    val result = operation(2, 3)
    println("The result is $result")
}

fun main(args: Array<String>) {
    twoAndThree { a, b -> a + b } //The result is 5
    twoAndThree { a, b -> a * b } //The result is 6
}