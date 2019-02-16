package annotations

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

/**
 *Kotlin支持的使用点目标的完整列表如下：
 *  • property -- Java 的注解不能应用这种使用点目标。
 *  • field -- 为属性生成的字段。
 *  • get -- 属性的getter 。
 *  • set -- 属性的setter 。
 *  • receiver -- 扩展函数或者扩展属性的接收者参数。
 *  • param -- 构造方法的参数。
 *  • setparam -- 属性se 忧er 的参数。
 *  • delegate -- 为委托属性存储委托实17u 的字段。
 *  • file -- 包含在文件中声明的顶层函数和属性的类。
 *      比如：使用@file:JvmName注解，修改文件类名
 */
class HasTempFolder {

    /**
     * 因为@Rule被应用到了字段上，而宇段默认是私有的。
     * 要把它应用到（公有的）getter上，要显式地写出来：@get:Rule
     * “@get”使用get点目标，“Rule”注解名称
     */
    @get:Rule  //注解的是getter，而不是属性。
    val folder = TemporaryFolder()


    @Test
    fun testUsingTempFolder() {
        val createdFile = folder.newFile("myfile.txt")
        val createdFolder = folder.newFolder("subfolder")

        // ...
    }

}