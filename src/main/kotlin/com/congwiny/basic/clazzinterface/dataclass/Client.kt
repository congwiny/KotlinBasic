package com.congwiny.basic.clazzinterface.dataclass

class Client(val name: String, val postalCode: Int) {
    //重写toString方法
    override fun toString(): String = "Client(name=$name,postalCode=$postalCode)"
}

fun main(args: Array<String>) {
    val client0 = Client("Alice", 34444)
    println(client0)
    val client1 = Client("Alice", 34444)
    /**
     * 在Kotlin中，"=="运算符是比较两个对象的默认方式：本质上说它就是通过调用
     * equals来比较两个值的。要想进行引用比较，可以使用===运算符，这与Java中的
     * "=="比较对象引用的效果一模一样
     */
    println(client0 == client1)   //false


    val client2 = Client2("Alice", 34444)
    val client3 = Client2("Alice", 34444)
    println(client2 == client3)   //true

    /**
     * 这个例子结果输出为false。
     * 为什么这样，因为Client2类缺少了hashCode方法。
     * 违反了通用的hashCode契约：
     *      如果两个对象相等，它们必须有着相同的hash值。
     * 要修复这个问题，可以向Client2中添加hashCode的实现。
     *
     *  processed set是一个HashSet。
     *  在HashSet中的对象比较是一种优化过的方式：
     *      首先，比较它们的hash值，然后只有当它们相等时才会去比较真正的值。
     *
     */
    val processed = hashSetOf(Client2("Alice", 34444))
    println(processed.contains(Client2("Alice", 34444)))//false

    val bob = Client2("Bob",1111000)
    println(bob.copy(postalCode = 2222000))//Client(name=Bob,postalCode=2222000)
}


class Client2(val name: String, val postalCode: Int) {
    /**
     * 重写equals方法
     * "Any"是java.lang.Object的模拟：Kotlin中所有类的父类
     * "Any?"表示可空类型，意味着"other"是可以为空的
     */
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client2)
            return false
        //检查对应的属性是否相等
        return name == other.name &&
                postalCode == other.postalCode
    }

//    override fun hashCode(): Int = name.hashCode() * 31 + postalCode

    //重写toString方法
    override fun toString(): String = "Client(name=$name,postalCode=$postalCode)"

    //建立副本通常是修改实例的好选择：副本有这单独的生命周期而且不会影响代码原有实例
    fun copy(name: String = this.name, postalCode: Int = this.postalCode) =
            Client2(name, postalCode) //手动实现copy
}

/**
 * 上面重写toString,equals,hashCode比较麻烦。
 * 好消息是，在Kotlin中，你不必再去生成这些方法了。
 * 如果为你的类添加data修饰符，必要的方法将会自动生成好.
 *
 * equals和hashCode方法 会使用 所有在主构造方法中声明的属性 进行计算。
 * 自动生成的equals方法会检测所有的属性的值是否相等。
 * hashCode方法会返回一个根据所有属性生成哈希值。
 *
 * 注意：没有在主构造方法中声明的数学将不会加入到equals和hashCode的计算中去。
 */
data class Client4(val name: String, val postalCode: Int)