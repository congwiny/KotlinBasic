package lambda.higherOrderFun

fun processTheAnswer(f: (Int) -> Int) {
    println(f(42))
}

fun main(args: Array<String>) {
    val strings = listOf("42")
    strings.forEach { s ->
        println(s)
    }
}