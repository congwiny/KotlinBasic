package coroutine

import kotlinx.coroutines.*

/**
 * 使用结构化并发， 我们可以在执行操作所在的指定作用域内启动协程。
 */

/**
 * 我们使用 【runBlocking 协程构建器】将 main 函数转换为协程。
 *
 * 包括 runBlocking 在内的每个协程构建器都将 CoroutineScope 的实例添加到其代码块所在的作用域中。
 * 我们可以在这个作用域中启动协程而无需显式 join 之，
 * 因为 外部协程（示例中的 runBlocking）直到在其作用域中启动的所有协程都执行完毕后才会结束。
 */
fun main2() = runBlocking {
    // this: CoroutineScope

    launch { // 在 runBlocking 作用域中启动一个新协程
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}

/**
 * 作用域构建器
 *
 * 使用 coroutineScope 构建器声明自己的作用域。
 * 它会创建一个协程作用域并且在所有已启动子协程执行完毕之前不会结束。
 * runBlocking 与 coroutineScope 的主要区别在于后者 在等待所有子协程执行完毕时不会阻塞当前线程。
 */
// this: CoroutineScope
fun main3() = runBlocking {
    launch {
        delay(200L)
        println("Task from runBlocking, thread=${Thread.currentThread()}")
    }

    // 创建一个协程作用域
    coroutineScope {
        launch {
            delay(500L)
            //thread=Thread[main,5,main]
            println("Task from nested launch, thread=${Thread.currentThread()}")
        }

        delay(100L)
        //thread=Thread[main,5,main]
        println("Task from coroutine scope, thread=${Thread.currentThread()}") // 这一行会在内嵌 launch 之前输出
    }

    //thread=Thread[main,5,main]
    println("Coroutine scope is over, thread=${Thread.currentThread()}") // 这一行在内嵌 launch 执行完毕后才输出
}

/**
 * 提取函数重构
 */
fun main() = runBlocking {
    launch {
        //在协程内部可以像普通函数一样使用挂起函数
        doWorld()
    }
    println("Hello,")
}

/**
 * 将 launch { …… } 内部的代码块提取到独立的函数中
 */
suspend fun doWorld() {// 这是你的第一个挂起函数
    delay(1000L)
    println("World!")
}
