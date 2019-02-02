package operator.overloading

class Person(
        val firstName: String, val lastName: String
) : Comparable<Person> {
    //先比较名字中的姓氏，如果姓氏相同，再比较名字
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this, other,
                Person::lastName, Person::firstName)    //Person::lastName是一个lambda表达式（成员引用）
    }

}

fun main(args: Array<String>) {
    val p1 = Person("Alice", "Smith")
    val p2 = Person("Bob", "Johnson")
    println(p1 < p2) //false

    //所有Java中实现了Comparable接口的类，都可以在kotlin中使用简洁的运算符语法，不用再增加扩展函数
    println("abc" < "bac")
}