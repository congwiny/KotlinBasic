package operator.overloading

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

/**
 * 以下示例看得出在Kotlin中，委托属性是如何工作的。
 *
 * 你创建了一个保存属性值的类，并在修改属性时自动触发更改通知。
 * 但是需要相当多的样板代码来为每个属性创建ObservableProperty实例。
 *
 * Kotlin的委托属性功能可以让你摆脱这些样板代码。
 */

fun main(args: Array<String>) {
    val p01 = People01("congwiny", 28, 2000)
    p01.addPropertyChangeListener(PropertyChangeListener { event ->
        println("Property ${event.propertyName} changed " +
                "from ${event.oldValue} to ${event.newValue}")
    })
    p01.age = 29
    p01.salary = 3000
}

class People01(
        val name: String, age: Int, salary: Int
) : PropertyChangeAware() {

    val _age = ObservableProperty01("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) {
            _age.setValue(value)
        }

    val _salary = ObservableProperty01("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) {
            _salary.setValue(value)
        }
}

class ObservableProperty01(
        val propName: String, var propValue: Int,
        val changeSupport: PropertyChangeSupport
) {
    fun getValue(): Int = propValue

    fun setValue(newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName, oldValue, newValue)
    }
}