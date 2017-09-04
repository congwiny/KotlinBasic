package android.support.v4

/**
 * Kotlin中的可见性修饰符和Java中的类似
 * 同样可使用public，private，protected。
 * 如果省略了默认可见性修饰符，声明就是public的。
 * 而在Java中的默认可见性是包私有。
 *
 * Kotlin提供了一个新的修饰符，internal。
 * 此修饰符表示"只在模块内部可见"
 * internal可见性的优势在于它提供了对模块实现细节的真正封装。
 *
 * Kotlin中允许在顶层声明中使用private可见性。(类，函数，属性)
 * 意味着只能在声明它们的文件中可见。
 *
 * 下面这个类，声明为internal就只能在当前模块(module)中使用。
 * 不能被其他模块访问到。
 */
internal class TalkativeButton {
    private fun yell() = println("Hey")
    protected fun whisper() = println("Let's talk")
}

/**
 * 编译出错。
 * Kotlin禁止从public函数giveSpeech去引用低可见类型TalkativeButton(可见性internal)
 * 简单的说就是当前【类或函数】要保持与被引用的【类或函数】的可见性要一致。
 * 这个例子可以这么解决，既可以把函数改为internal，也可以把类改成public的
 * 另外低可见性可访问高可见性。
 *
    fun TalkativeButton.giveSpeech(){

    }
 */

/**
 * 注意：
 * 1.protected修饰符在Java和Kotlin中不同的行为。
 * 在Java中，在同一个包中可访问一个protected修饰的成员。
 * 而在Kotlin中，protected成员只能在类和其子类中可见。
 * 另外，类的扩展函数不能访问它的private和protected成员。
 *
 * 2.如果在Kotlin中声明了一个private的类，那么在Java中使用此类时，
 * 会把这个Kotlin类当成一个包私有声明的类来使用。
 *
 * 3.有时你能从Java代码中访问一些你不能从Kotlin中访问的东西。
 * 比如，可以从另一个模块的Java代码中访问Kotlin中的internal类或顶层声明，
 * 抑或从同一个包的Java代码中访问一个Kotlin中的protected的成员
 *
 * 4.在Kotlin中一个外部类不能看到其内部类中的private成员，但在Java中是可以的。
 */
