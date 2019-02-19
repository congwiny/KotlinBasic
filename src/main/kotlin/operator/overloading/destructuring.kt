package operator.overloading

/**
 * DESTRUCTURING DECLARATIONS AND COMPONENT FUNCTIONS 解构声明和组件函数
 *
 * 解构声明: 这个功能允许你展开单个复合值，并使用它来初始化多个单独的变量。
 *
 * 不可能定义无限数量的componentN函数，标准库只允许使用此语法来访问一个对象的前五个元素。
 * 让一个函数能返回多个值有更简单的方法，是使用标准库中的Pair和Triple类。
 * 在语义表达上这种方式会差一点，因为这些类也不知道它返回的对象中包含什么，
 * 但因为不需要定义自己的类所以可以少写代码。
 **/

fun main(args: Array<String>) {
    val p = Point(10, 20)
    //声明变量x、y ，然后用p的组件来初始化
    val (x, y) = p
    println(x) //10
    println(y) //20

    val p2 = Point2(30, 40)
    val (x2, y2) = p2
    println(x2) //30
    println(y2) //40

    /**
     *  如果在解构声明中你不需要某个变量，那么可以用下划线取代其名称（自 1.1 起）
     *  对于以这种方式跳过的组件，不会调用相应的 componentN() 操作符函数。
     */
    val (_, yy) = p2
    println("y=$yy") //40

    /**
     * 解构声明主要使用场景之一，是从一个函数返回多个值，这个非常有用。
     * 如果要这样做，可以定义一个数据类来保存返回所需的值，并将它作为函数的返回类型。
     * 在调用函数后，可以用解构声明的方式，来轻松地展开它，使用其中的值。
     **/
    val (name, ext) = splitFilename2("example.kt") //使用解构声明来展开这个类
    println(name) //example
    println(ext) //kt

    /**
     * 解构声明不仅可以用作函数中的顶层语句，还可以用在其他可以声明变量的地方，例如in循环。
     */
    val map = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
    //Oracle -> Java
    //JetBrains -> Kotlin
    printEntries(map)
    /**
     * 在 lambda 表达式中解构（自 1.1 起）
     * 你可以对 lambda 表达式参数使用解构声明语法。
     * 如果 lambda 表达式具有 Pair 类型（或者 Map.Entry 或任何其他具有相应 componentN 函数的类型）的参数，
     * 那么可以通过将它们放在括号中来引入多个新参数来取代单个新参数。
     *
     * 注意声明两个参数和声明一个解构对来取代单个参数之间的区别：
     * { a //-> …… } // 一个参数
     * { a, b //-> …… } // 两个参数
     * { (a, b) //-> …… } // 一个解构对
     * { (a, b), c //-> …… } // 一个解构对以及其他参数
     */
    println(map.mapValues { (key, value) -> "$key -> $value!" })//{Oracle=Oracle -> Java!, JetBrains=JetBrains -> Kotlin!}
    //如果解构的参数中的一个组件未使用，那么可以将其替换为下划线，以避免编造其名称：
    println(map.mapValues { (_, value) -> value })//{Oracle=Java, JetBrains=Kotlin}
    //你可以指定整个解构的参数的类型或者分别指定特定组件的类型：
    println(map.mapValues { (_, value): Map.Entry<String, String> -> "$value!" })
    println(map.mapValues { (_, value: String) -> "$value!" })
}

/**
 * 这个简单的例子用到了两个Kotlin 约定：
 * 一个是迭代一个对象，另一个是用于解构声明。
 *
 * Kotlin标准库给map增加了一个扩展的iterator函数，用来返回map条目的迭代器。
 * 因此，与Java不同的是，可以直接迭代map。
 * 它还包含Map.Entry上的扩展函数component1和component2，分别返回它的键和值。
 * 实际上，for循环被转换成了这样的代码：
 * for (entry in map.entries) {
    val key = entry.component1()
    val value = entry.component2()
    // ...
  }
 */
fun printEntries(map: Map<String, String>) {
    for ((key, value) in map) {
        println("$key -> $value")
    }
}

fun splitFilename(fullName: String): NameComponents {
    val result = fullName.split('.', limit = 2)
    return NameComponents(result[0], result[1])//返回一个数据类型的实例
}


fun splitFilename2(fullName: String): NameComponents {
    //componentN函数在数组和集合上也有定义，上面splitFilename函数可以这么改进
    val (name, extension) = fullName.split('.', limit = 2)
    return NameComponents(name, extension)
}

/**
 * 对于数据类，编译器为每个在主构造方法中声明的属性生成一个componentN函数。
 */
data class Point2(val x: Int, val y: Int)

/**
 * 声明一个数据类来持有值
 */
data class NameComponents(val name: String, val extension: String)
