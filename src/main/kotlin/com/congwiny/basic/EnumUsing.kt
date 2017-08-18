package com.congwiny.basic

enum class Color(val r:Int,val g:Int,val b:Int){
    RED(255,0,0),
    ORANGE(255,165,0),
    YELLOW(255,255,0),
    GREEN(0,255,0),
    BLUE(0,0,255);

    fun rgb() = (r*256+g)*256+b
}

fun main(args: Array<String>) {
    //color rgb = 255
    println("color rgb = ${Color.BLUE.rgb()}")
}