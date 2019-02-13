package generics

/**
 * 逆变：反转子类型化关系
 *
 * 逆变的概念可以被看成是协变的镜像：
 * 对一个逆变类来说，它的子类型化关系与用作类型实参的类的子类型化关系是相反的。
 *
 * 一个在类型参数上“逆变”的类是这样的一个泛型类（我们以Consumer<T>为例），
 * 对这种类来说，下面的描述是成立的：
 * 如果B是A的子类型，那么Consumer<A>就是Consumer<B>的子类型。
 * 类型参数A 和B 交换了位置，所以我们说子类型化被反转了。
 * 例如，Consumer<Animal>就是Consumer<Cat>的子类型。
 *
 * 在类型参数T 上的关键字意味着子类型化被反转了，而且T 只能用在in 位置。
 */

fun main(args: Array<String>) {
    val strings = listOf("abc", "acd")

    /**
     * 一个为特定类型的值定义的比较器显然可以比较该类型任意子类型的值。
     *     例如，如果有一个Comparator<Any>，可以用它比较任意具体类型的值。
     */
    val anyComparator = Comparator<Any> { e1, e2 ->
        e1.hashCode() - e2.hashCode()
    }
    /**
     * public fun <T> Iterable<T>.sortedWith(comparator: Comparator<in T>): List<T>
     *     这个接口方法只是消费类型为T 的值。这说明T只在 in 位置使用，因此它的声明之前用了in 关键宇。
     *
     *     sortedWith 函数期望一个Comparator<String > （一个可以比较字符串的比较器），
     *     传给它一个能比较更一般的类型的比较器是安全的。
     *     如果你要在特定类型的对象上执行比较，可以使用能处理该类型或者它的超类型的比较器。
     *     这说明Comparator<Any>是Comparator<String>的子类型，其中Any是String的超类型。
     *     不同类型之间的子类型关系和这些类型的比较器之间的子类型化关系截然相反。
     */
    strings.sortedWith(anyComparator) //可以用任意对象的比较器比较具体对象，比如字符串
}
