/**
 * 委托属性发挥作用的另一种常见用法，是用在有动态定义的属性集的对象中。
 * 这样的对象有时被称为自订（ expando ）对象
 */


fun main(args: Array<String>) {
    val p = Person()
    val data = mapOf("name" to "Dmitry", "company" to "JetBrains")
    for ((attrName, value) in data) {
        p.setAttribute(attrName, value)
    }
    println(p.name) //Dmitry

    //-------------------
    val p2 = Person2()
    p2.setAttribute("name", "Dmitry")
    p2.setAttribute("company", "JetBrains")
    /**
     * p.name隐藏了attributes.getValue(p, prop）的调用，这里变为_attributes[prop.name］
     */
    println(p2.name) //Dmitry
    println(p2.company) //JetBrains
}

class Person2 {
    private val _attributes = hashMapOf<String, String>()

    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    /**
     * 因为标准库己经在标准Map和MutableMap 接口上定义了getValue和setValue扩展函数，所以这里可以直接这样用。
     *
     * 属性的名称将自动用作在map中的键，属性值作为map 中的值。
     */
    val name: String by _attributes //把map作为委托属性
    val company: String by _attributes
}

class Person {
    private val _attributes = hashMapOf<String, String>()

    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    val name: String
        get() = _attributes["name"]!! //从map中手动检索
}