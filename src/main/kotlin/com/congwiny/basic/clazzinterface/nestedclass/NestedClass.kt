package com.congwiny.basic.clazzinterface.nestedclass

import java.io.Serializable

interface State : Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}

interface OnClickListener {
    fun onClick(v: View?)
}

class Button : View {

    override fun getCurrentState(): State = ButtonState()
    private var mPrivateFlags: Int = 1
    var mListener: OnClickListener? = null

    override fun restoreState(state: State) {
        super.restoreState(state)
    }

    /**
     * 此类没有显示修饰符，称为嵌套类。
     * 嵌套类与Java中的static嵌套类是一样的
     * 不会存储（持有）外部类的引用
     */
    class ButtonState : State {} //对应Java中的static class ButtonState

    /**
     * 此类有inner修饰符，称为内部类
     * 内部类与Java中的非static内部类是一样的
     * 会存储（持有）外部类的引用
     */
    inner class ButtonState2 : State { //对应Java中的class ButtonState2
        /**
         * 在内部类中获取外部类的实例
         */
        fun getOuterReference(): Button = this@Button

        fun accessOutter() {
            println("accessOuter flag=$mPrivateFlags")
        }
    }

    fun setOnClickListener() {
        /**
         * 关于匿名内部类（Annonymous Inner Class）：
         * 匿名内部类就是没有名字的内部类。既然是内部类，那么它自然也可以访问外部类的变量。
         */
        mListener = object : OnClickListener {
            override fun onClick(v: View?) {
                println("onClick mPrivateFlags=$mPrivateFlags")
            }
        }
    }
}

fun main(args: Array<String>) {
    //嵌套类不需要构造外部类对象
    val state = Button.ButtonState()

    //内部类要先构造外部类对象
    val state2 = Button().ButtonState2()
    state2.accessOutter() //accessOuter flag=1

    val btn = Button()
    btn.setOnClickListener()
    btn.mListener?.onClick(null) //onClick mPrivateFlags=1
}