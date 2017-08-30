package com.congwiny.basic;

public class RegexUsing {
    public static void main(String[] args) {
        /**
         * 注意这个Java中的split的参数是一个正则表达式。（"."表示任何字符的正则表达式）
         * 所以最后得到的是一个空数组。length=0
         */
        System.out.println("12.345-6.A".split(".").length);

        /**
         * [12, 345, 6, A]
         * [12, 345, 6, A]
         */
        RegexUsingKt.main(new String[]{"h"});
    }
}
