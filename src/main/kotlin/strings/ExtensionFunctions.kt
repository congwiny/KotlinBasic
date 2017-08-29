@file:JvmName("ExtString")
package strings

/**
 * 扩展String的API，提供lastChar函数。。牛逼了
 * 可将lastChar函数作为String类的成员函数，只不过定义在类的外面了。
 *
 * fun String(接收者类型).lastChar(): Char = this(接收者对象，this可省略).get(this(接收者对象).length - 1)
 * 接收者类型是由扩展函数定义的，接收者对象是该类型的一个实例。
 *
 * 注意：扩展函数不能访问【被扩展类】的私有或者受保护的成员
 *
 */
fun String.lastChar(): Char = this.get(this.length - 1)

fun String.firstChar(): Char = get(0)