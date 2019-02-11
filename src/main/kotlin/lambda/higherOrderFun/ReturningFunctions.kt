package lambda.higherOrderFun

/**
 * 返回函数的函数
 *
 * 声明一个返回另一个函数的函数，需要指定一个函数类型作为返回类型。
 *
 * 要返回一个函数，需要写一个return 表达式，跟上一个lambda 、一个成员引用，
 * 或者其他的函数类型的表达式，比如一个（函数类型的）局部变量。
 */

enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)

fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double { //声明一个返回函数的函数

    if (delivery == Delivery.EXPEDITED) {
        return { order -> 6 + 2.1 * order.itemCount } //返回lambda
    }
    return { order -> 1.2 * order.itemCount } //返回lambda
}

fun main(args: Array<String>) {
    //将返回的函数保存在变量中
    val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
    //调用返回的函数
    println("Shipping costs ${calculator(Order(3))}") //Shipping costs 12.3


    //demo2
    val contacts = listOf(Person("Dmitry", "Jemerov", "123-4567"),
            Person("Svetlana", "Isakova", null))

    val contactListFilters = ContactListFilters()
    with(contactListFilters) {
        prefix = "Dm"
        onlyWithPhoneNumber = true
    }

    //将getPredicate返回的函数作为参数传递给“filter”函数
    println(contacts.filter(contactListFilters.getPredicate()))
    //[Person(firstName=Dmitry, lastName=Jemerov, phoneNumber=123-4567)]
}


class ContactListFilters {

    var prefix: String = ""
    var onlyWithPhoneNumber: Boolean = false

    fun getPredicate(): (Person) -> Boolean { //声明一个返回函数的函数
        val startsWithPrefix = { p: Person ->
            p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix)
        }

        if (!onlyWithPhoneNumber) {
            return startsWithPrefix //返回一个函数类型的变量
        }

        return { startsWithPrefix(it) && it.phoneNumber != null }  //从这个函数返回一个lambda
    }
}


data class Person(
        val firstName: String,
        val lastName: String,
        val phoneNumber: String?
)