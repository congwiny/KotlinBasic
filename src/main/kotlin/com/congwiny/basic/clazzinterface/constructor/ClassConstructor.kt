package com.congwiny.basic.clazzinterface.constructor


/**
 * 被括号围起来的语句块叫做主构造方法
 * 它主要有两个目的：
 * 1.表明构造方法的参数
 * 2.定义使用这些参数初始化的属性
 *
 * 在这个例子中，可以看到两个新的Kotlin关键字：constructor和init
 * constructor关键字用来声明一个主构造方法或者从构造方法
 * init关键字用来引入一个初始化语句块，这个语句块在类被创建时执行，
 * 并会与主构造方法一起使用。可以在一个类中声明多个初始化语句块
 * （因为主构造方法有语法限制，不能包含初始化代码，所以就用到了初始化语句块）
 */
class User1 constructor(_nickname: String) {
    //定义一个参数的主构造方法
    val nickname: String

    init {
        //初始化属性
        nickname = _nickname
    }
}

//如果主构造方法没有注解或可见性修饰符，可以去掉constructor关键字
class User2(_nickname: String) { //带一个参数的主构造方法
    val nickname = _nickname //用参数来初始化属性
}

/**
 * "val"以为着类中相应的属性会使用构造方法的参数来初始化
 * 以上User类的声明都是等价的，这个使用了最简洁的语法
 */
class User3(val nickname: String)

/**
 * 为构造方法参数提供一个默认值。
 *
 * 另外，如果所有的构造方法的参数都有默认值，
 * 编译器会自动生成一个额外的不带参数的构造方法，属性值都使用默认值。
 */
open class User4(val nickname: String,
                 val isSubscribed: Boolean = true)

fun main(args: Array<String>) {
    //创建对象时，只需要直接调用构造方法，不需要new关键字
    val alice = User4("Alice")
    println(alice.isSubscribed)
    //可以显示地为某些构造方法参数标明名称
    val carol = User4("Carol", isSubscribed = false)
    println(carol.isSubscribed)
}

/**
 * 子类继承父类时，主构造方法同样需要初始化父类（调用父类的构造方法）
 */
class TwitterUser(nickname: String) : User4(nickname)

/**
 * 如果没有给一个类声明任何的构造方法，将会生成一个不带任何参数的默认构造方法
 */
open class Button

/**
 * 必须显示地调用父类的构造方法，即使它没有任何的参数。
 * 这就是为什么在父类名称后面还需要加一个空的括号。(表示调用父类构造方法)
 *
 * 注意：接口没有没有构造方法，所以在实现一个接口的时候，不需要在接口名称后面再加上括号
 */
class RadioButton : Button()

/**
 * 如果你想要确保你的类不被其他代码实例化，
 * 必须把构造方法标记为private
 *
 * private构造方法的替代方案：
 * 在Java中，可以通过private构造方法禁止实例化这个类来表示一个更通用的意思：
 *  这个类是一个静态使用工具成员的容器或者单例的。
 *
 * Kotlin针对上述Java的这种目的具有内建的语言级别的功能：
 * 1.可以使用顶层函数作为静态使用工具
 * 2.要想表示单例，可以使用对象声明(object)
 */
class Secretive private constructor() {}//主构造方法标记为private

class Context
class AttributeSet

/**
 * 使用多个从构造方法来扩展一个框架类，以便于通过不同方式来初始化类。
 * 只要需要，可以声明任意多个从构造方法
 */
open class View {
    //从构造方法
    constructor(ctx: Context) {
        //some code
    }

    constructor(ctx: Context, attr: AttributeSet) {
        //some code
    }
}

/**
 * 说明：如果类没有主构造方法，那么每个从构造方法必须初始化基类或者委托为另一个这样做了的构造方法
 */
class MyButton : View {
    constructor(ctx: Context)
            : super(ctx) { //super关键字调用父类的构造方法
        //..
    }

    constructor(ctx: Context, attr: AttributeSet)
            : super(ctx, attr) {
        //..
    }

    /**
     * 使用this关键字，调用你自己类的另一个构造方法（委托给这个类的另一个构造方法）
     */
    constructor(ctx: Context, attr: AttributeSet, style: Int)
            : this(ctx, attr){
        //..
    }

}