package com.congwiny.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CallFunction {

    public static void main(String[] args) {
        //FunctionUsingKt.joinToString()

        List<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");

        System.out.println(arrayList);

        String str = StringFunctions.joinToString(arrayList, "; ", "(", ")");
        System.out.println(str);
        //编译错误
        //FunctionUsingKt.joinToString(arrayList, separator = "; ", prefix = "(", postfix = ")");

        //调用Kotlin的提供默认参数值的函数
        System.out.println(StringFunctions.joinToString2(arrayList));
        //访问Kotlin中的常量
        System.out.println(StringFunctions.UNIX_LINE_SEPARATOR);
        //使用get访问器访问顶层属性
        String name = StringFunctions.getName();
    }

    public static <T> String joinToString(Collection<T> collection,
                                          String separator,
                                          String prefix,
                                          String postfix) {
        StringBuilder result = new StringBuilder(prefix);

        Iterator it = collection.iterator();

        for (int index = 0; index < collection.size(); index++) {
            if (index > 0) {
                result.append(separator);
            }
            result.append(it.next());
        }
        result.append(postfix);
        return result.toString();
    }
}
