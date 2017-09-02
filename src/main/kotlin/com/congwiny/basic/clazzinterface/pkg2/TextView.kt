package com.congwiny.basic.clazzinterface.pkg2

import com.congwiny.basic.clazzinterface.pkg1.Clickable
import com.congwiny.basic.clazzinterface.pkg1.Focusable

/**
 * Kotlin使用 ":" 代替Java的extends和implements关键字
 * 和Java一样，一个类可以实现多个接口，但是只能继承一个类
 *--------------
 * 在Java中，类和方法默认是open的
 *  （如果类不声明private或者final默认是可以被继承的
 *    如果方法不声明private或者final默认是可被重写的
 *   ）
 * 而Kotlin中默认都是final的（类不可被继承，方法不可被重写）
 * --------------
 * 使用open修饰符修饰类的话，就标识此类可被其他类继承，否则不能（类默认final修饰）
 * 使用open修复符修饰方法，就标识此方法可被子类重写，否则不能（方法默认final修饰）
 * （如果方法是重写父类的（有override标注），那么这个方法是可被子类重写的）
 *
 */
open class TextView(override val clickEnable: Boolean) : Clickable,Focusable {

    /**
     * 在Kotlin中使用override来标注被重写的父类或者接口的方法和属性
     * 在Kotlin中使用override修饰符是强制要求的
     */
    override fun click() = println("I was clicked")

    /**
     * 此类同时实现了Clickable,Focusable接口，两个接口中都有showOff方法默认实现，
     * 必须实现自己的showOff方法，不然会报错
     *
     * override标注的方法默认是被重写的（open的），但是前面加上final修饰，就不可被重写了
     */
    final override fun showOff() {
        /**
         * 使用尖括号加上父类型名字的"super"表明了你想要调用哪一个父类的方法
         * （在Java中，Clickable.super.showOff())
         */
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

    /**
     * 此方法默认被final修饰，不能被子类重写
     * 如果加上open就可以被重写了
     */
    fun showOn(){

    }

}