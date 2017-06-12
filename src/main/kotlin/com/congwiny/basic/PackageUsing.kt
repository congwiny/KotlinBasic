package com.congwiny.basic

/**
 * Created by congwiny on 2017/6/12.
 * 1.默认导入
 * 有多个包会默认导入到每个Kotlin文件中
 *  - kotlin.*
    - kotlin.annotation.*
    - kotlin.collections.*
    - kotlin.comparisons.*（⾃ 1.1 起）
    - kotlin.io.*
    - kotlin.ranges.*
    - kotlin.sequences.*
    - kotlin.text.*

    根据⽬标平台还会导⼊额外的包：
    JVM:
        - java.lang.*
        - kotlin.jvm.*
    JS:
        - kotlin.js.*

 * 2.导入
 * 除了默认导⼊之外，每个⽂件可以包含它⾃⼰的导⼊指令。导⼊语法在语法中讲述。
    可以导⼊⼀个单独的名字，如.
    import foo.Bar // 现在 Bar 可以不⽤限定符访问

    也可以导⼊⼀个作⽤域下的所有内容（包、类、对象等）:
    import foo.* // “foo”中的⼀切都可访问

    如果出现名字冲突，可以使⽤ as 关键字在本地重命名冲突项来消歧义：
    import foo.Bar // Bar 可访问
    import bar.Bar as bBar // bBar 代表“bar.Bar”

* 关键字 import 并不仅限于导⼊类；也可⽤它来导⼊其他声明：
    顶层函数及属性
    在对象声明中声明的函数和属性;
    枚举常量
* 与 Java 不同，Kotlin 没有单独的 "import static" 语法；所有这些声明都⽤ import 关键字导⼊。
 *------------------------
 * 顶层声明的可⻅性
 * 如果顶层声明是 private 的，它是声明它的⽂件所私有的（参⻅ 可⻅性修饰符）
 */


