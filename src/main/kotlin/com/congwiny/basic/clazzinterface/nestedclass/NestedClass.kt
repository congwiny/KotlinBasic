package com.congwiny.basic.clazzinterface.nestedclass

import java.io.Serializable

interface State : Serializable

interface View{
    fun getCurrentState(): State
    fun restoreState(state: State){}
}

class Button : View{
    override fun getCurrentState(): State = ButtonState()

    override fun restoreState(state: State) {
        super.restoreState(state)
    }

    /**
     * 此类没有显示修饰符，称为嵌套类。
     * 嵌套类与Java中的static嵌套类是一样的
     * 不会存储（持有）外部类的引用
     */
    class ButtonState : State{}//对应Java中的static class ButtonState

    /**
     * 此类有inner修饰符，称为内部类
     * 内部类与Java中的非static内部类是一样的
     * 会存储（持有）外部类的引用
     */
    inner class ButtonState2 : State{
        /**
         * 在内部类中获取外部类的实例
         */
        fun getOuterReference(): Button = this@Button
    }//对应Java中的class ButtonState2

}