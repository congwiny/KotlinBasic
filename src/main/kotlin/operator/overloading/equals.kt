package operator.overloading

fun main(args: Array<String>) {
    println(Point(10, 20) == Point(10, 20)) //true
    println(Point(10, 20) != Point(5, 5)) //true
    println(null == Point(1, 2)) //false
}