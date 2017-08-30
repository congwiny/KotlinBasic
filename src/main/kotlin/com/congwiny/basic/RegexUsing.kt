package com.congwiny.basic


fun main(args: Array<String>) {
    //显示地创建一个正则表达式。此方法和Java中的split是一样的，传入的是正则
    println("12.345-6.A".split("\\.|-".toRegex()))//[12, 345, 6, A]
    //指定多个分隔符。Kotlin中特有的split方法
    println("12.345-6.A".split(".", "-"))//[12, 345, 6, A]

    val path = "/Users/congwiny/kotlin/regex.txt"
    parsePath(path)

    /**
     * 使用了三重引号，不需要对任何字符串进行转义，包括反斜线。
     * 而且可以包含任何字符，包括换行符，从而可以简单地把包含换行符的文本嵌入到程序中
     * 解决了原本在Java中需要进行大量啰嗦的转义和字符串连接的问题
     *
     * "." 是我们给字符串内容添加的前缀，标记边距的结尾
     * 调用trimMargin来删除每行中的前缀和前面的空格
     */
    val kotlinLogo = """| //
                       .|//
                       .|/ \""".trimMargin(".")
    println(kotlinLogo)

    val price = """$ {'$'} 99.9"""// $ {'$'} 99.9
    println(price)

    //$ 符号字面值不支持反斜杠转义，必须使用三重引号和嵌入式表达式
    val price2 = """
    .${'$'}9.99
    """.trimMargin(".") //$9.99
    println(price2)

    parsePath2(path)

}

/**
 * 很简单的解析，比正则方便多了
 */
fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension")
}

//正则的形式
fun parsePath2(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, fileName, extension) = matchResult.destructured
        println("Dir: $directory, name: $fileName, ext: $extension")

    }
}