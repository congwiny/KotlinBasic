package generics

/**
 * 协变：保留子类型化关系
 *
 * 变型的概念描述了拥有相同基础类型和不同类型实参的（泛型）类型之间是如何关联的：
 * 例如， List<String>和List<Any>之间如何关联。
 *
 * 不变型：
 * 一个泛型类 -- 例如， MutableList --
 * 如果对于任意两种类型A和B, MutableList<A>既不是MutableList<B>的子类型也不是它的超类型，
 * 它就被称为在该类型参数上是不变型的。Java中所有的类都是不变型的。
 *
 * 协变：
 * Kotlin 中的List 接口表示的是只读集合。如果A 是B 的子类型，那么List <A>就是List<B>的子类型。
 * 这样的类或者接口被称为协变的。
 */

fun printContents(list: List<Any>) {
    println(list.joinToString())
}

fun addAnswer(list: MutableList<Any>) {
    list.add(42)
}

/**
 * 一个协变类是一个泛型类（我们以Producer<T>为例〉，如果A是B 的子类型，
 * 那么Producer<A>就是Producer<B>的子类型。我们说子类型化被保留了。
 * 例如，Producer<Cat>是Producer<Animal>的子类型，因为Cat是Animal的子类型。
 * 在Kotlin中，要声明类在某个类型参数上是可以协变的，
 * 在该类型参数的名称前加上“out”关键字即可
 *
 * 让类在某个类型参数变为协变，限制了该类中对该类型参数使用的可能性。
 * 要保证类型安全，它只能用在所谓的out位置，意味着这个类只能生产类型T的值而不能消费它们。
 *
 * 如果函数是把T当成返回类型，我们说它在out 位置。这种情况下，该函数生产类型为T的值。
 * 如果T用作函数参数的类型，它就在in 位置。这样的函数消费类型为T的值。
 *
 * 类型参数T上的关键宇out 有两层含义：
 * 1、子类型化会被保留（ Producer<Cat>是Producer<Animal> 的子类型）
 * 2、T只能用在out位置
 *
 * Kotlin的List是只读的，所以它只有一个返回类型为T的元素的方法get，
 * 而没有定义任何把类型为T 的元素存储到列表中的方法。因此，它也是协变的。
 *
 * 不能把MutableList<T>在它的类型参数上声明成协变的，
 * 因为它既含有接收类型为T的值作为参数的方法，也含有返回这种值的方法。
 * （因此，T出现在in和out两种位置上）。
 *
 * 注意，构造方法的参数既不在in 位置，也不在out 位置。
 * 即使类型参数声明成了out ，仍然可以在构造方法参数的声明中使用它，
 * 构造方法不是那种在实例创建之后还能调用的方法，因此它不会有潜在的危险。
 *
 * 位置规则只覆盖了类外部可见的（ public 、protected 和internal) API 。
 * 私有方法的参数既不在in 位置也不在out 位置。
 * 变型规则只会防止外部使用者对类的误用但不会对类自己的实现起作用。
 */
interface Producer<out T> { //类被声明成在T上协变，把T当成返回类型
    fun produce(): T
}


fun main(args: Array<String>) {
    /**
     * 这里字符串列表可以正常工作。函数把每个元素都当成Any 对待，而且
     * 因为每一个字符串都是Any ，这是完全安全的。
     */
    printContents(listOf("abc", "bac"))

    /**
     * 你声明了一个类型为MutableList<String>的变量strings ，
     * 然后尝试把它传给这个函数。假设编译器接收了，你就能在字符串列表中添加一个整型，
     * 这会导致当你在运行时尝试访问列表中的字符串的时候出现异常。
     * 正因如此， 这次调用不会通过编译。
     * 这个例子展示了当期望的是MutableList<Any> 的时候把一个MutableList<String>当作实参传递是不安全的，
     * Kotlin 编译器正确地禁止了它。
     * val strings = mutableListOf("abc", "bac")
     * addAnswer(strings) //Type mismatch.
     *
     * 如果函数添加或者替换了列表中的元素就是不安全的，
     * 因为这样会产生类型不一致的可能性。否则它就是安全的。
     *
     * 如果函数接收的是只读列表，可以传递具有更具体的元素类型的列表。
     * 如果列表是可变的，你就不能这样做。
     */


}

