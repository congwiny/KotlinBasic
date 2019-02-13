package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 协变
 *
 * 参考：https://segmentfault.com/a/1190000005337789
 */
public class Covariance {

    /**
     * main 方法中的第一行，创建了一个 Apple 数组并把它赋给 Fruit 数组的引用。
     * 这是有意义的，Apple 是 Fruit 的子类，一个 Apple 对象也是一种 Fruit 对象，
     * 所以一个 Apple 数组也是一种 Fruit 的数组。这称作“数组的协变”，
     * Java 把数组设计为协变的，对此是有争议的，有人认为这是一种缺陷。
     *
     * 尽管 Apple[] 可以 “向上转型” 为 Fruit[]，但数组元素的实际类型还是 Apple，
     * 我们只能向数组中放入 Apple或者 Apple 的子类。
     * 在上面的代码中，向数组中放入了 Fruit 对象和 Orange 对象。
     * 对于编译器来说，这是可以通过编译的，但是在运行时期，
     * JVM 能够知道数组的实际类型是 Apple[]，所以当其它对象加入数组的时候就会抛出异常。
     *
     */
    public static void main(String[] args) {
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple(); // OK
        fruit[1] = new Jonathan(); // OK
        // Runtime type is Apple[], not Fruit[] or Orange[]:
        try {
            // Compiler allows you to add Fruit:
            fruit[0] = new Fruit(); // ArrayStoreException
        } catch(Exception e) { System.out.println(e); }
        try {
            // Compiler allows you to add Oranges:
            fruit[0] = new Orange(); // ArrayStoreException
        } catch(Exception e) { System.out.println(e); }
    }

    /**
     * Output:
     * java.lang.ArrayStoreException: Fruit
     * java.lang.ArrayStoreException: Orange
     */



    /**
     * 泛型设计的目的之一是要使这种运行时期的错误在编译期就能发现，看看用泛型容器类来代替数组会发生什么：
     *
     * // Compile Error: incompatible types:
     * ArrayList<Fruit> flist = new ArrayList<Apple>();
     * 上面的代码根本就无法编译。
     * 当涉及到泛型时， 尽管 Apple 是 Fruit 的子类型，但是 ArrayList<Apple> 不是 ArrayList<Fruit> 的子类型，泛型不支持协变
     *
     * 从上面我们知道，List<Number> list = ArrayList<Integer> 这样的语句是无法通过编译的，
     * 尽管 Integer 是 Number 的子类型。那么如果我们确实需要建立这种 “向上转型” 的关系怎么办呢？
     * 这就需要通配符来发挥作用了。
     */
    static void wildcard(){
        // Wildcards allow covariance:
        List<? extends Fruit> flist = new ArrayList<Apple>();//利用 <? extends Fruit> 形式的通配符，可以实现泛型的向上转型：
        // Compile Error: can’t add any type of object:
        // flist.add(new Apple());
        //flist.add(new Fruit());
        // flist.add(new Object());
        flist.add(null); // Legal but uninteresting
        // We know that it returns at least Fruit:
        Fruit f = flist.get(0);
        /**
         * 如果调用某个返回 Fruit 的方法，这是安全的。
         * 因为我们知道，在这个 List 中，不管它实际的类型到底是什么，但肯定能转型为 Fruit，所以编译器允许返回 Fruit。
         */
    }

    static void wildcard2(){
        List<? extends Fruit> flist = Arrays.asList(new Apple());
        Apple a = (Apple)flist.get(0); // No warning
        /**
         * 但是 flist 却可以调用 contains 和 indexOf 方法，它们都接受了一个 Apple 对象做参数。
         * 如果查看 ArrayList 的源代码，可以发现 add() 接受一个泛型类型作为参数，
         * 但是 contains 和 indexOf 接受一个 Object 类型的参数
         */
        flist.contains(new Apple()); // Argument is ‘Object’
        flist.indexOf(new Apple());
        /**
         * flist 的类型是 List<? extends Fruit>，
         * 泛型参数使用了受限制的通配符，所以我们失去了向这个 List 添加任何对象的能力，即使是 Object 也不行。
         */
        //flist.add(new Apple());   无法编译
    }

    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
        // apples.add(new Fruit()); // Error
    }
}


class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}