package com.congwiny.basic.clazzinterface.objectdeclaration

import java.io.File

class Person

/**
 * Kotlin中，object这个关键字声明一个类并同时创建一个实例
 *
 * 【对象声明】通过object关键字引入。
 * 【对象声明】将类声明与该类的单一实例声明结合到了一起。
 * 与类一样，一个【对象声明】也可以包含属性、方法、初始化语句块等的声明。
 * 唯一不允许的就是构造方法（包括主构造方法和从构造方法）
 * 与普通类的实例不同，【对象声明】在定义的时候就立即创建了。
 *
 * 【对象声明】允许你使用对象名加"."字符的方式来调用方法和访问属性
 */
object Payroll { //对象声明，创建单例易如反掌
    val allEmployees = arrayListOf<Person>()
    fun calculateSalary() {
        for (person in allEmployees) {
            //...
        }
    }
}

fun main(args: Array<String>) {
    //【对象声明】调用方法和属性
    Payroll.allEmployees.add(Person())
    Payroll.calculateSalary()

    println(CaseInsensitiveFileComparator.compare(
            File("/User"), File("/Home")))

    val files = listOf(File("/Z"), File("/a"))
    /**
     * 单例对象,在Kotlin中使用 "类名" 表示即可。
     * 在Java中要使用 "类名.INSTANCE"
     */
    println(files.sortedWith(CaseInsensitiveFileComparator))

    val students = listOf(Student("Bob"), Student("Alice"))
    println(students.sortedWith(Student.NameComparator))
}

/**
 * 【对象声明】同样可以继承自类和接口
 *
 * 比较器通常来说不存储任何数据，所以通常只需要一个单独的 Comparator实例
 * 来以特定的 方式 比较对象。这是一个非常完美的对象声明的使用场景。
 *
 * 意思就是说只需要单独一个Comparator实例就有比较的功能了，不需要创建多个实例。
 */
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = false)
    }
}

/**
 * 可在类中使用【对象声明】，这样的对象同样只有一个单一实例
 */
data class Student(val name: String) {
    //对象声明，单一实例
    object NameComparator : Comparator<Student> {
        override fun compare(o1: Student, o2: Student): Int =
                o1.name.compareTo(o2.name)
    }
}