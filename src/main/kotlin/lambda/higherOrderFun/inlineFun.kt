package lambda.higherOrderFun

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * lambda 表达式会被正常地编译成匿名类。
 * 这表示每调用一次lambda 表达式，一个额外的类就会被创建。
 * 并且如果lambda 捕捉了某个变量，那么每次调用的时候都会创建一个新的对象。
 * 这会带来运行时的额外开销，导致使用lambda 比使用一个直接执行相同代码的函数效率更低。
 *
 * 如果使用inline 修饰符标记一个函数，在函数被使用的时候编译器并不会生成函数调用的代码，
 * 而是使用函数实现的真实代码替换每一次的函数调用。
 *
 * 如果在两个不同的位置使用同一个内联函数，但是用的是不同的lambda ，
 * 那么内联函数会在每一个被调用的位置被分别内联。
 * 内联函数的代码会被拷贝到使用它的两个不同位置，并把不同的lambda 替换到其中。
 */

/**
 * 当一个函数被声明为inline 时，它的函数体是内联的
 * 换句话说，函数体会被直接替换到函数被调用的地方，而不是被正常调用。
 * 来看一个例子以便理解生成的最终代码。
 */
//Kotlin 标准库定义了另一个叫作withLock 的函数，它提供了实现同样功能的更符合语言习惯的API
inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}

fun foo(l: Lock) {
    println("Before sync")
    /**
     * lambda 表达式和synchronized 函数的实现都被内联了。
     * 由lambda 生成的字节码成为了函数调用者定义的一部分，
     * 而不是被包含在一个实现了函数接口的匿名类中。
     */
    synchronized(l) {
        println("Action")
    }
    println("After sync")
}


/**
 * 不是所有使用lambda 的函数都可以被内联。
 * 当函数被内联的时候，作为参数的lambda 表达式的函数体会被直接替换到最终生成的代码中。
 * 这将限制函数体中的对应（ lambda ）参数的使用。
 * 如果（ lambda ）参数被调用，这样的代码能被容易地内联。
 * 但如果（ lambda ）参数在某个地方被保存起来，以便后面可以继续使用，lambda 表达式的代码将不能被内联。
 * -----------------------------
 * 一般来说，参数如果被直接调用或者作为参数传递给另外一个inline 函数，它是可以被内联的。
 * 否则，编译器会禁止参数被内联并给出错误信息“Illegal usage of inline-parameter”（非法使用内联参数）。
 * -----------------------------
 * 编译器完全支持内联跨模块的函数或者第三方库定义的函数。
 * 也可以在Java 中调用绝大部分内联函数，但这些调用并不会被内联，而是被编译成普通的函数调用。
 */
class LockOwner(val lock: Lock) {
    fun runUnderLock(body: () -> Unit) {
        /**
         * 在这种情况下， lambda 的代码在内联函数被调用点是不可用的，因此并不会被内联。
         */
        synchronized(lock, body) //传递一个函数类型的变量（body）作为参数，而不是一个lambda

        /**
         *  编译后的字节码：
         *  class LockOwner(val lock: Lock) {
         *      //runUnderLock 函数会被编译成类似于以下函数的字节码:
         *      fun _runUnderLock_（ body: () -> Unit) {
         *          lock. lock()
         *          try {
         *              body() //body没有被内联，因为在调用的地方还没有lambda
         *          }
         *          finally {
         *              lock. unlock ()
         *          }
         *      }
         *  }
         */

    }
}

fun main(args: Array<String>) {
    val lock = ReentrantLock()
    foo(lock)

    val owner = LockOwner(ReentrantLock())
    owner.runUnderLock {
        println("runUnderLock")
    }
}


