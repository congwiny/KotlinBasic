package lambda

/**
 * Lambda 表达式，或简称 lambda ，本质上就是可以传递给其他函数的一小段代码 。
 *有了 Lambda，可以轻松地把通用的代码结构抽取成库函数， Kotlin 标准库就大量地使用了它们。
 * Lambda可以与任意Java库一起使用。
 **/

/**
 *函数式编程提供了另外一种解决问题的方法：把函数当作值来对待。可以直接传递函数，
 *而不需要先声明一个类再传递这个类的实例 。 使用 lambda 表达式之后 ，
 *代码会更加简洁。都不需要声明函数了 ： 相反，可以高效地直接传递代码块作为函数参数。
 */

data class Person(val name: String, val age: Int)

fun findTheOldest(people: List<Person>) {
    var maxAge = 0
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}

fun main(args: Array<String>) {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    findTheOldest(people)

    val people2 = listOf(Person("Alice", 29), Person("Bob", 31))
    /**
     * maxBy函数可以在任何集合上调用，且只需要一个实参：
     * 一个函数，指定比较哪个值来找到最大元素。
     * 花括号中的代码｛ it.age ｝就是实现了这个逻辑的lambda。
     */
    println(people2.maxBy { it.age })
    //如果lambda刚好是函数或者属性的委托，可以用成员引用替换。
    println(people.maxBy(Person::age))
}