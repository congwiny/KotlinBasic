package operator.overloading

class Point(val x: Int, val y: Int) { //x ,y 都是val不支持改动，只支持访问
    //重写在Any中定义的方法
    override fun equals(other: Any?): Boolean {
        if (other === this) return true //优化：检查参数是否与this是同一个对象
        if (other !is Point) return false //检查参数类型
        return other.x == x && other.y == y //智能转换为Point来访问x、y属性
    }
}