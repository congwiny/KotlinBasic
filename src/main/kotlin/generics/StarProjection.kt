package generics

import java.util.*

/**
 * 星号投影：使用 * 代替类型参数
 *
 * Kotlin 的MyType<*>对应于Java 的MyType<?>
 *
 * 当类型实参的信息并不重要的时候，可以使用星号投影的语法：
 * 不需要使用任何在签名中引用类型参数的方法，或者只是读取数据而不关心它的具体类型。
 */

fun main(args: Array<String>) {
    val list: MutableList<Any?> = mutableListOf('a', 1, "qwe")
    val chars = mutableListOf('a', 'b', 'c')
    val unknownElements: MutableList<*> = if (Random().nextBoolean()) list else chars
    /**
     * Out-projected type 'MutableList<*>' prohibits the use of 'public abstract fun add(element: E): Boolean'
     * defined in kotlin.collections.MutableList
     *
     * MutableList<*>投影成了MutableList<out Any?> ：
     * 当你没有任何元素类型信息的时候，读取Any?类型的元素仍然是安全的，
     * 但是向列表中写入元素是不安全的。（因为不知道是哪个类型）
     */
    //unknownElements.add(42)

    printFirst2(listOf("Svetlana", "Dmitry"))
}

fun <T> printFirst1(list: List<T>) { //每一种列表都是可能的实参
    if (list.isNotEmpty()) {
        println(list.first()) //first()现在返回的是T的值
    }
}

/**
 * 星号投影的语法很简沽，但只能用在对泛型类型实参的确切值不感兴趣的地方：
 * 只是使用生产值的方法，而且不关心那些值的类型。
 */
fun printFirst2(list: List<*>) { //每一种列表都是可能的实参
    if (list.isNotEmpty()) { //isNotEmpty没有使用泛型类型参数
        println(list.first()) //first()现在返回的是Any?，但是这里足够了
    }
}



