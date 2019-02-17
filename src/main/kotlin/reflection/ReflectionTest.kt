package reflection

import kotlin.reflect.KFunction2
import kotlin.reflect.full.memberProperties

var counter = 0

fun main(args: Array<String>) {
    val person = Person("Alice", 29)
    //返回一个KClass<Person>的实例
    val kClass = person.javaClass.kotlin
    println(kClass === Person::class) //true
    println(kClass.simpleName) //Person
    kClass.memberProperties.forEach {
        println(it.name)
    } //age, name

    //反射调用foo函数
    val kFunction = ::foo
    kFunction.call(42)
    kFunction.invoke(42)
    kFunction(42) //不用显示的invoke就可以调用kFunction

    /**
     * KFunction2的类型称为合成的编译器生成类型，在kotlin.reflect中找不到他们的声明。
     * 这意味着你可以使用任意数量参数的函数接口。
     * 合成类型的方式减小了kotlin-reflect.jar的尺寸，同时避免了对函数类型参数数量的人为限制。
     *
     * KFunctionN每一个类型都继承了KFunction并加上一个额外的成员invoke，它拥有数量刚好的参数。
     * 例如，KFunction2 声明了operator fun invoke (pl : P1, p2 : P2) : R,
     * 其中P1和P2 代表着函数的参数类型，而R代表着函数的返回类型。
     *
     * 因此，如果你有这样一个具体类型的KFunction ，它的形参类型和返回类型是确定的，
     * 那么应该优先使用这个具体类型的invoke 方法。
     * call 方法是对所有类型都有效的通用手段，但是它不提供类型安全性。
     */
    val kFunction2: KFunction2<Int, Int, Int> = ::sum
    println(kFunction2.invoke(1, 2) + kFunction2(3, 4)) //10

    /**
     * 你也可以在一个KProperty实例上调用call方法，它会调用该属性的getter。
     * 但是属性接口为你提供了一个更好的获取属性值的方式：get方法。
     *
     * 要访问get方法，你需要根据属性声明的方式来使用正确的属性接口。
     * 顶层属性表示为KProperty0接口的实例，它有一个无参数的get方法：
     */
    val kProperty = ::counter
    kProperty.setter.call(21) //通过反射调用setter，把21作为实参传递
    println(kProperty.call()) //通过“call”获取属性的值
    println(kProperty.get()) //通过“get”获取属性的值

    /**
     * 一个成员属性由KProperty1的实例表示，它拥有一个单参数的get方法。
     * 要访问该属性的值，必须提供你需要的值所属的那个对象实例。
     *
     * KProperty1是一个泛型类。变量memberProperty的类型是KProperty<Person, Int> ，
     * 其中第一个类型参数表示接收者的类型，而第二个类型参数代表了属性的类型。
     * 这样你只能对正确类型的接收者调用它的get方法。而memberProperty.get("Amanda"）这样的调用不会通过编译。
     * 还有一点值得注意，只能使用反射访问定义在最外层或者类中的属性，而不能访问函数的局部变量。
     * 如果你定义了一个局部变量x并试图使用::x来获得它的引用，你会得到一个编译期的错误：
     * "References to variables aren't supported yet" （现在还不支持对变量的引用〉。
     */
    val person2 = Person("Amanda", 28)
    val memberProperty = Person::age //在memberProperty变量中存储了一个指向属性的引用
    println(memberProperty.get(person2)) //memberProperty.get(person)来获取属于具体person实例的这个属性的值。
}

fun sum(x: Int, y: Int) = x + y

fun foo(x: Int) = println(x)

class Person(val name: String, val age: Int)