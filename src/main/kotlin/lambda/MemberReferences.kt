package lambda


fun sendEmail(person: Person, message: String) {
    println("send Email message: $message to ${person.name}")
}

var action = { person: Person, message: String ->
    //lambada委托给sendEmail函数
    sendEmail(person, message)
}

var nextAction = ::sendEmail //成员引用

var action2 = { person: Person, message: String ->
    nextAction(person, message)
}

//构造方法引用存储
val createPerson = ::Person

//扩展函数
fun Person.isAdult() = age >= 21

/**
 * 尽管isAdult不是Person类的成员，还是可以通过引用访问它，这和访问实例的成员没什么两样
 */
val predicate = Person::isAdult

fun main(args: Array<String>) {
    action(Person("xiaoyu", 23), "hello Person")
    action2(Person("chong", 24), "hello Person2")

    //构造方法引用 延迟执行创建类实例动作
    val p = createPerson("shiyi", 22)
    println(p)

    println(predicate(p)) //true

    //绑定成员引用（使用成员引用语法捕捉特定实例对象上的方法引用 kotlin1.1支持）
    val dmitrysAgeFunction = p::age
    println(dmitrysAgeFunction())//22
}