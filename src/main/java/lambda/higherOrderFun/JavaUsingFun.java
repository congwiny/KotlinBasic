package lambda.higherOrderFun;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

import java.util.ArrayList;
import java.util.List;

/**
 * 在Java中使用 函数类型
 * 其背后的原理是，函数类型被声明为普通的接口：
 * 一个 函数类型 的 变量是FunctionN接口的一个实现。
 * Kotlin 标准库定义了一系列的接口，这些接口对应于不同参数数量的函数：
 * FunctionO<R> （没有参数的函数）、Function1<P1,R> （一个参数的函数），等等。
 * 每个接口定义了一个invoke 方法，调用这个方法就会执行函数。
 * <p>
 * 一个函数类型的变量就是实现了对应的FunctionN接口的实现类的实例，
 * 实现类的 invoke 方法包含了lambda 函数体。
 */
public class JavaUsingFun {
    public static void main(String[] args) {
        //Java 8的lambda会被自动转换为函数类型的值
        JavaUsingFunKt.processTheAnswer(number -> number + 1);

        //在Java 8 以前，可以传递一个实现了函数接口中的invoke方法的匿名类的实例
        JavaUsingFunKt.processTheAnswer(new Function1<Integer, Integer>() {
            @Override
            public Integer invoke(Integer number) {
                System.out.println("invoke before: " + number);
                return number + 1;
            }
        });

        /**
         * 在Java 中可以很容易地使用Kotlin 标准库中以lambda 作为参数的扩展函数。
         * 但是要注意它们看起来并没有Kotlin 中那么直观 一一
         * 必须要显式地传递一个接收者对象作为第一个参数
         */
        List<String> strings = new ArrayList<>();
        strings.add("42");
        /**
         * 在Java 中，函数或者lambda 可以返回Unit 。
         * 但因为在Kotlin 中Unit 类型是有一个值的，所以需要显式地返回它。
         */
        //可以在Java代码中使用Kotlin标准库中的函数
        CollectionsKt.forEach(strings, s -> {
            System.out.println(s);
            //必须要显示地返回一个Unit类型的值
            return Unit.INSTANCE;
        });
    }
}
