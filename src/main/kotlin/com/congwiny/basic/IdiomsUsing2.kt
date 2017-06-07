package com.congwiny.basic

import java.io.File

/**
 * Created by congwiny on 2017/6/7.
 */

fun main(args:Array<String>){

    //延迟属性（Lazy property）
    /**
     * 从网友得知：
     * lazy是Kotlin的属性代理的一个实例，提供延迟加载的机制，
     * p这个常量是在第一次使用的时候初始化,lazy默认是线程安全的
     * 就是在使用常量p的时候，才会执行花括号里面的代码
     */
    //val p : String by lazy {  } //TODO 这个玩不了，暂时还是不懂

    //if not null的缩写（shorthand）
    val files = File("Test").listFiles()
    //如果不为null，就输出数组的大小，为null的话输出null
    println(files?.size)

    //if not null and else缩写
    println(files?.size?:"empty")

}

