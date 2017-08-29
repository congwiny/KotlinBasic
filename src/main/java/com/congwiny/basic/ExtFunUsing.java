package com.congwiny.basic;

import strings.ExtFunStringKt;
import view.Button;
import view.ExtFunViewKt;
import view.View;

public class ExtFunUsing {
    public static void main(String[] args) {
        /**
         * 在Java中调用Kotlin的扩展函数。
         *
         * 调用这个类的静态方法（因为扩展函数被声明为顶层函数，所以，它将被编译为一个静态函数）
         * ，并把接收者对象作为第一个参数传进去即可
         */
        char c = ExtFunStringKt.firstChar("Java");

        View view = new Button();
        /**
         * 扩展函数并不存在重写，因为Kotlin会把它们当做静态函数来对待
         */
        ExtFunViewKt.showOff(view);//I am a View

    }
}
