package com.congwiny.basic

import com.google.gson.Gson
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by congwiny on 2017/6/7.
 */

fun main(args: Array<String>) {

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
    println(files?.size ?: "empty")

    //if null 执行一个语句
    //val data = files?:throw IllegalStateException("files is empty")

    //if not null 执行代码
    val tempFile = File("idioms")
    tempFile?.let {
        println("if not null 执行代码块")
    }


    /**
     * 总结：
     * 1. "?." if not null运算符,放置在一个对象后面（object ?. xxx）
     *     object?.let{
     *          //execute this block if not null
     *     }
     *
     * 2.?: if null运算符
     *  object ?. xxx ?: yyy (if not null else)
     *  object?:zzz (if null executing a statement)
     *
     */

    //返回when表达式(Return on when statement)
    println(transform("blue"))

    //try/catch 表达式，碉堡了。。
    tryCatchTest()

    //if表达式
    foo(1)

    println(arrayOfMinusOnes(3)[1])

    println(theAnswer())

    println(transform3("red"))

    //对一个对象实例调用多个方法(With)
    val myTurtle = Turtle()
    with(myTurtle) {
        //画一个100像素的正方形
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }

    //java7 的try with resources
    //nio
    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
    stream.buffered().reader().use{
        reader -> println(reader.readText())
    }
    /**
     * //try-with-resources语法。最后br会自动释放资源
        public static String processFile() throws IOException {
            String filePath = "/home/congwiny/IdeaProjects/JavaBasic/src/main/resources/hello.txt";
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine();
        }
    }
     */

    //使用可空布尔。还可以是其他类型的可空
    val b:Boolean?= true
    if (b==true){

    }else{
        // `b` is false or null
    }

}

//对于需要【泛型信息】的【泛型函数】的便捷形式
// public final class Gson {
//      ...
//      public <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
//      ...

//reified 具体化的 //TODO 我日，泛型还没学习，写的啥玩意，看不懂
//inline fun<reified T: Any> Gson.fromJson(json): T = this.fromJson(json,T::class.java)



class Turtle {
    fun penDown() {}
    fun penUp() {}
    fun turn(degress: Double) {}
    fun forward(pixels: Double) {}
}

fun transform3(color: String): Int = transform(color)

//单表达式函数与其他Idioms用法一起使用能简化代码，例如和when表达式一起使用：
fun transform2(color: String): Int = when (color) {
    "red" -> 0
    "blue" -> 1
    "green" -> 2
    else -> throw IllegalStateException("invalid color param")
}


//单表达式(Single-expression function)，等价于(equivalent to)以下代码
fun theAnswer() = 42

//fun theAnswer():Int{
//    return 42
//}

fun transform(color: String): Int {
    return when (color) {
        "red" -> 0
        "green" -> 1
        "blue" -> 2
        else -> -1
    }
}

/**
 * 整形数组里面的元素都-1
 */
fun arrayOfMinusOnes(size: Int): IntArray {
    //返回类型为Unit的方法的Builder风格用法
    return IntArray(size).apply { fill(-1) }
}

fun foo(param: Int) {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else {
        "three"
    }

    println("result==" + result)
}


fun tryCatchTest() {
    /**
     * result的取值是最后一个表达式的value
     * 输出1111
     */
    val result = try {
        File("adda")
        listOf("1", "2", "3")
        Integer.decode("1111")
    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }

    println(result)
}


