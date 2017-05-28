package com.congwiny.basic

/**
 * Created by congwiny on 2017/5/27.
 */
fun parseInt(str:String):Int?{

    return str.toIntOrNull()
}

fun printProduct(arg1:String,arg2:String){
    //注意：val这里代表的是常量，不能再次赋值，类似java中的final
    val x = parseInt(arg1)
    // x=22 这里编译器报错；如果使用var的话就没问题
    val y = parseInt(arg2)
    //这里的println编译器是报错的，因为没有判空（parseInt都标记了 ？）
    //println(x*y)
    if (x!=null&&y!=null){
        //在null检查之后，x,y会自动被转换为非null的值
        println(x*y)
    }else{
        println("either '$arg1' or '$arg2' is not a number")
    }
    /**
     * 小节：有些函数的返回值声明上标注了？，就代表着，这个函数可能会返回null
     * 这个时候，你需要取主动判断这个函数的返回值是不是为null，如果不去判断的话
     * 编译器就会报错。
     * */

}

fun main(args:Array<String>){
    printProduct("11","12")
}


