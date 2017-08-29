package com.congwiny.basic;

import text.ExtAttrStringBuilderKt;

public class ExtAttrUsing {
    public static void main(String[] args) {
        //Java 访问扩展属性时，应显式地调用它的getter函数
        char c = ExtAttrStringBuilderKt.getLastChar2(new StringBuilder("abc"));
        System.out.println(c);
    }
}
