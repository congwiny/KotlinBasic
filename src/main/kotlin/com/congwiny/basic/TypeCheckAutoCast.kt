package com.congwiny.basic

/**
 * Created by congwiny on 2017/5/28.
 */
/**
 * is 运算符用于检查一个实例对象是否属于一个类型
 * 如果一个不可变的局部变量（local variable）或者属性被判断属于某个特定的类型
 * 那么检测后的分支中可以直接当作该类型使用，无需显式转换
 */
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        //obj在这个分支中已经被转换为了String类型
        return obj.length
    }
    //在离开类型检测分支后，obj仍然是'Any'类型
    return null
}

//或者，可以这么写：
fun getStringLength2(obj: Any): Int? {
    if (obj !is String) return null
    return obj.length
}