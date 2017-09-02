package com.congwiny.basic.clazzinterface.pkg2

/**
 * 在Kotlin中，同Java一样，可以将一个类声明为abstract的，
 * 这种类不能被实例化
 *
 * 抽象类和其抽象函数默认都是open的，非抽象函数默认是final的
 */
abstract class View {

    /**
     * 这个函数时抽象的。必须被子类实现
     */
    abstract fun layout()

    /**
     * 抽象类中的非抽象函数默认为final，
     * 此处标注为open的，子类就可以重写了
     */
    open fun onLayout(){

    }

    /**
     * 抽象类中的非抽象函数默认为final，子类无法重写
     */
    fun draw(){

    }
}