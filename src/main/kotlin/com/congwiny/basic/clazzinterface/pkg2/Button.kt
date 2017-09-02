package com.congwiny.basic.clazzinterface.pkg2

/**
 * 在Java中，类和方法默认是open的
 *  （如果类不声明private或者final默认是可以被继承的
 *    如果方法不声明private或者final默认是可被重写的
 *   ）
 * 而Kotlin中默认都是final的（类不可被继承，方法不可被重写）
 */
class Button : TextView(false) {


}