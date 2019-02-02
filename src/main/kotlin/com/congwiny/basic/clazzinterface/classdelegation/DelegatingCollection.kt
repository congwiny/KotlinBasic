package com.congwiny.basic.clazzinterface.classdelegation

/**
 * 装饰器模式
 * 这种模式的本质
 * 1.创建一个新类
 * 2.实现与原始类一样的接口
 * 3.将原来类的实例作为一个字段保存
 *
 * 这个新类拥有同样行为的方法不用被修改，只需要直接转发到原始类的实例。
 * 装饰器模式：组合的方式扩展原有类的功能，比继承更好。
 * (继承相比装饰更加臃肿,因为要扩展某个功能，就得继承父类，然后覆盖重写，导致这个体系更加臃肿。)
 *
 * 这种方式的一个缺点是需要相当多的样板代码。
 * 好消息是，Kotlin提供了类委托机制，见DelegatingCollection2
 */
class DelegatingCollection<T> : Collection<T> {

    private val innerList = arrayListOf<T>()

    override val size: Int = innerList.size

    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)

    override fun isEmpty(): Boolean = innerList.isEmpty()

    override fun contains(element: T): Boolean = innerList.contains(element)

    override fun iterator(): Iterator<T> = innerList.iterator()
}

/**
 * 类委托
 * 无论什么时候实现一个接口，你都可以使用 by 关键字将接口的实现委托到另一个对象。
 * 上面代码所有的方法实现都消失了，编译器会生成它们。
 * 现在，当你需要修改某些方法的行为时，你可以重写它们。
 */
class DelegatingCollection2<T>(innerList: Collection<T> = ArrayList<T>()
) : Collection<T> by innerList {
    //...
}


class CountingSet<T>(val innerList: MutableCollection<T> = HashSet<T>()
) : MutableCollection<T> by innerList {//将MutableCollection的实现委托给innerSet
    var objectsAdd = 0

    /**
     * 下面两个方法，通过重写add和addAll方法来计数，
     * 并将MutableCollection接口剩下的实现 委托给 被你包装的容器(innerSet)
     */
    override fun add(element: T): Boolean {//重写方法，不使用委托
        objectsAdd++
        return innerList.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {//重写方法，不使用委托
        objectsAdd += elements.size
        return innerList.addAll(elements)
    }
}

fun main(args: Array<String>) {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1, 1, 2))
    println("${cset.objectsAdd} objects were added, ${cset.size} remain")
    //3 objects were added, 2 remain
}