package coroutine

import kotlinx.coroutines.*

/**
 * 协程很轻量：
 * 它启动了 10 万个协程，并且在一秒钟后，每个协程都输出一个点。
 * 现在，尝试使用线程来实现。会发生什么？（很可能你的代码会产生某种内存不足的错误）
 */
fun main4() = runBlocking {
    repeat(100_000) { // 启动大量的协程
        launch {
            delay(1000L)
            print(".")
        }
    }
}

/**
 * 在 GlobalScope 中启动的活动协程并不会使进程保活。它们就像守护线程。

以下代码在 GlobalScope 中启动了一个长期运行的协程，
该协程每秒输出“I'm sleeping”两次，之后在主函数中延迟一段时间后返回。
 */
fun main() = runBlocking {
    //sampleStart
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
            println("thread=${Thread.currentThread()}") //Thread[DefaultDispatcher-worker-1,5,main]
        }
    }
    //delay(1300L) // 在延迟后退出 (注释掉此行，主线程结束后，GlobalScope启动的协程就会退出)
    println("thread=${Thread.currentThread()}") //Thread[main,5,main]
//sampleEnd
}