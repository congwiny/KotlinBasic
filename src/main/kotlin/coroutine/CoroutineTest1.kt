package coroutine

import kotlinx.coroutines.*

//fun main() {
//    test1()
//    test2()
//    test3()
//}

/**
 *  delay 是一个特殊的 挂起函数 ，它不会造成线程阻塞，但是会 挂起 协程，并且只能在协程中使用。
 */
fun test1() {
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!") // 在延迟后打印输出
        println("thread = ${Thread.currentThread()}") //thread = Thread[DefaultDispatcher-worker-1,5,main]
    }
    println("Hello,") // 协程已在等待时主线程还在继续
    println("thread = ${Thread.currentThread()}") //thread = Thread[main,5,main]
    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
}

/**
 * 可以将 GlobalScope.launch { …… } 替换为 thread { …… }，将 delay(……) 替换为 Thread.sleep(……) 达到同样目的
 * 但delay函数不会阻塞线程，而sleep函数会阻塞线程
 */
fun test2() {
    object : Thread() {
        override fun run() {
            sleep(1000L)
            println("World!")
        }
    }.start()
    println("Hello,")
}

/**
 * 让我们显式使用 runBlocking 协程构建器来阻塞：
 * 与之前结果是相似的，但是这些代码只使用了非阻塞的函数 delay。
 * 调用了 runBlocking 的主线程会一直 阻塞 直到 runBlocking 内部的协程执行完毕。
 */
fun test3() {
    GlobalScope.launch {  // 在后台启动一个新的协程并继续
        delay(1000L)
        println("World!")
        println("thread = ${Thread.currentThread()}") //thread = Thread[DefaultDispatcher-worker-2,5,main]
    }
    println("Hello,") // 主线程中的代码会立即执行

    runBlocking {// 但是这个表达式阻塞了主线程
        delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
        println("thread = ${Thread.currentThread()}") //thread = Thread[main,5,main]
    }
}

/**
 * 使用更合乎惯用法的方式重写，使用 runBlocking 来包装 main 函数的执行。
 * runBlocking<Unit> { …… } 作为用来启动顶层主协程的适配器。
 * 我们显式指定了其返回类型 Unit，因为在 Kotlin 中 main 函数必须返回 Unit 类型
 */
fun main1() = runBlocking<Unit> {// 开始执行主协程
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L)
        println("World!")
        println("thread = ${Thread.currentThread()}") //thread = Thread[DefaultDispatcher-worker-1,5,main]
    }
    println("Hello,") // 主协程在这里会立即执行
    delay(2000L)      // 延迟 2 秒来保证 JVM 存活
    println("thread = ${Thread.currentThread()}") //thread = Thread[main,5,main]
}


/**
 * 延迟一段时间来等待另一个协程运行并不是一个好的选择。
 * 让我们显式（以非阻塞方式）等待所启动的后台 Job 执行结束：
 */
fun main() = runBlocking {
    //sampleStart
    // 启动一个新协程并保持对这个作业的引用
    val job = GlobalScope.launch {
        delay(1000L)
        println("World!")
        println("thread = ${Thread.currentThread()}") //Thread[DefaultDispatcher-worker-1,5,main]
    }
    println("Hello,")
    println("thread = ${Thread.currentThread()}") //Thread[main,5,main]
    job.join() // 等待直到子协程执行结束
    //sampleEnd
    //现在，结果仍然相同，但是主协程与后台作业的持续时间没有任何关系了。好多了。
}