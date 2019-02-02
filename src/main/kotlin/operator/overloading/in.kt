package operator.overloading

data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point): Boolean {
    return p.x in upperLeft.x until lowerRight.x &&
            p.y in upperLeft.y until lowerRight.y
}

fun main(args: Array<String>) {
    val rect = Rectangle(Point(10, 20), Point(50, 50))
    // in 运算符用于检查某个对象是否属于“集合”，相应的函数叫做contains
    println(Point(20, 30) in rect) //true
}
