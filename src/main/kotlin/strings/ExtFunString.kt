package strings

/**
 * 扩展String的API，提供lastChar函数。。牛逼了
 * 可将lastChar函数作为String类的成员函数，只不过定义在类的外面了。
 *
 * fun String(接收者类型).lastChar(): Char = this(接收者对象，this可省略).get(this(接收者对象).length - 1)
 * 接收者类型是由扩展函数定义的，接收者对象是该类型的一个实例。
 *
 * 注意：
 * 1.扩展函数不能访问【被扩展类】的私有或者受保护的成员
 *
 * 2.某个接收者类型必须放在其对应的package包下，否则不起作用
 * 比如接收者类型String必须放在strings的包下
 *
 * 3.扩展函数不可重写。扩展函数并不是类的一部分，它是声明在类之外的（只属于类的静态函数）。
 * 尽管可以给基类和子类都分别定义一个同名的扩展函数，当这个函数被调用时，
 * 函数的调用是由该变量的静态类型所决定的，而不是这个变量的运行时类型。(非多态)
 *
 * 4.如果一个类的成员函数和扩展函数有相同的签名，成员函数往往会被优先使用
 *
 * 扩展函数无非就是静态函数的一个高效的语法糖
 *
 */

fun String.lastChar(): Char = this.get(this.length - 1)

fun String.firstChar(): Char = get(0)