package com.congwiny.basic.clazzinterface.nestedclass

interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

/**
 * 这个方法中的when表达式不得不加一个else默认分支，很不方便。
 * 更重要的是，当你新添加了一个子类，编译器是不能发现有地方改变了。
 * 如果你忘记了添加一个新的分子，就会走else的流程。这有可能导致潜在的bug
 */
fun eval(e: Expr): Int =
        when (e) {
            is Num -> e.value
            is Sum -> eval(e.right) + eval(e.left)
            else ->
                throw IllegalArgumentException("Unknown expression")
        }


/**
 *  Kotlin为解决上述问题，提供了sealed类（密封类）
 *  为父类添加一个sealed修饰符，对可能创建的子类做出严格的限制：
 *      所有的直接子类必须嵌套在父类中 (kotlin 1.0版本)
 *  sealed修饰符隐含的说明了这个类是一个open类，你不需要显示地添加open修饰符
 */
sealed class Expr2{
    //将所有可能的类作为嵌套类列出
    class Num2(val value: Int): Expr2()

    class Sum2(val left: Expr2, val right: Expr2) : Expr2()
}

/**
 * Kotlin 1.1版本，允许在同一个文件的任何位置定义sealed类的子类
 */
class Num2(val value: Int): Expr2()

class Sum2(val left: Expr2, val right: Expr2) : Expr2()

/**
 * 如果你在when表达式中处理所有sealed类的子类，你就不需要提供默认的分支（else分支）
 * when表达式涵盖了所有可能的情况，所以不再需要"else"分支
 */
fun eval2(e: Expr2): Int =
        when (e) {
            is Expr2.Num2 -> e.value
            is Expr2.Sum2 -> eval2(e.right) + eval2(e.left)
            is Num2 -> e.value
            is Sum2 -> eval2(e.right) + eval2(e.left)
        }