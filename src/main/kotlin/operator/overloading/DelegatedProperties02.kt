package operator.overloading

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Reusing property accessor logic: Delegated Properties
 * 重用属性访问的逻辑：委托属性
 **/

fun main(args: Array<String>) {
    val p = People("congwiny", 28, 2000)
    p.addPropertyChangeListener(PropertyChangeListener { event ->
        println("Property ${event.propertyName} changed " +
                "from ${event.oldValue} to ${event.newValue}")
    })
    p.age = 29
    p.salary = 3000

    //---------------------
    val p2 = People2("congwiny", 29, 3000)
    p2.addPropertyChangeListener(PropertyChangeListener { event ->
        println("Property ${event.propertyName} changed " +
                "from ${event.oldValue} to ${event.newValue}")
    })
    p2.age = 30
    p2.salary = 4000
}

class People2(
        val name: String, age: Int, salary: Int
) : PropertyChangeAware() {

    private val observer = { prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
    /**
     * 你不用手动去实现可观察的属性逻辑，可以使用Kotlin 标准库，
     * 它已经包含了类似于ObservableProperty的类
     */
    var age: Int by Delegates.observable(age, observer) //使用Delegates.observable来实现属性修改的通知
    var salary: Int by Delegates.observable(salary, observer)
}

class People(
        val name: String, age: Int, salary: Int
) : PropertyChangeAware() {

    /**
     * 通过关键字by, Kotlin编译器会自动执行之前版本的代码中手动完成的操作。
     *
     * by 右边的表达式不一定是新创建的实例，也可以是函数调用、另一个属性或
     * 任何其他表达式，只要这个表达式的值，是能够被编译器用正确的参数类型来调用getValue和setValue的对象。
     *
     * 与其他约定一样，getValue和setValue可以是对象自己声明的方法或扩展函数。
     *
     * 这里只展示了如何使用类型为Int 的委托属性。委托属性机制其实是通用的，适用于任何其他类型。
     */

    var age: Int by ObservableProperty(age, changeSupport)
    var salary: Int by ObservableProperty(salary, changeSupport)
}

/**
 * 见识Kotlin 委托属性的神奇了。来看看代码变短多少？
 */
class ObservableProperty(
        var propValue: Int, val changeSupport: PropertyChangeSupport
) {
    /**
     * 1.按照约定的需要，getValue和setValue函数被标记了operator。
     *
     * 2.这些函数加了两个参数： 一个用于接收属性的实例，用来设置或读取属性，
     * 另一个用于表示属性本身。这个属性类型为KProperty。
     *
     * 3.把name属性从主构造方法中删除了，因为现在已经可以通过KProperty访问属性名称。
     **/
    operator fun getValue(p: People, prop: KProperty<*>): Int = propValue

    operator fun setValue(p: People, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}