package lambda.higherOrderFun

import java.io.BufferedReader
import java.io.FileReader

/**
 * Kotlin 中并没有try-with-resource等价的语法，
 * 因为通过使用一个带有函数类型参数的函数（接收lambda 参数）可以无缝地完成相同的事情。
 * 这个Kotlin 标准库中的函数叫作use。
 */

/**
 * 现在使用use 函数将TryWithResource的代码重写为Kotlin 代码。
 */
fun readFirstLineFromFile(path: String): String {
    BufferedReader(FileReader(path)).use { br ->
        return br.readLine()
    }
}

fun main(args: Array<String>) {
    println(readFirstLineFromFile("src/hello.txt"))
}
