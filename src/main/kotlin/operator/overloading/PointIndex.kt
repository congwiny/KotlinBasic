package operator.overloading

//实现get的约定方法
operator fun Point.get(index: Int): Int {
    return when (index) {
        0 -> x
        1 -> y
        else ->
            throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

data class MutablePoint(var x: Int, var y: Int) //var可以被改动

//实现set的约定方法。
// set的最后一个参数用了接收赋值语句中（等号）右边的值，其他参数作为方括号内的下标
// x[a,b]=c  --->  x.set(a,b,c)
operator fun MutablePoint.set(index: Int, value: Int) {
    when (index) {
        0 -> x = value
        1 -> y = value
        else ->
            throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

fun main(args: Array<String>) {
    val p1 = Point(10, 20)
    println(p1[1]) //20

    val p2 = MutablePoint(10, 20)
    p2[1] = 42
    println(p2) //MutablePoint(x=10, y=42)

}