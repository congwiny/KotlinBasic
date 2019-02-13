package generics;

import java.util.List;

/**
 * 通配符的另一个方向是超类型的通配符: ? super T，
 * T 是类型参数的下界。使用这种形式的通配符，我们就可以 ”传递对象” 了。
 */
public class SuperTypeWildcards {
    /**
     * writeTo 方法的参数 apples 的类型是 List<? super Apple>，
     * 它表示某种类型的 List，这个类型是 Apple 的基类型。
     * 也就是说，我们不知道实际类型是什么，但是这个类型肯定是 Apple 的父类型。
     * 因此，我们可以知道向这个 List 添加一个 Apple 或者其子类型的对象是安全的，这些对象都可以向上转型为 Apple。
     * 但是我们不知道加入 Fruit 对象是否安全，因为那样会使得这个 List 添加跟 Apple 无关的类型。
     */
    static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
        // apples.add(new Fruit()); // Error
    }
}

/**
 * src 是原始数据的 List，因为要从这里面读取数据，所以用了上边界限定通配符：<? extends T>，取出的元素转型为 T。
 * dest 是要写入的目标 List，所以用了下边界限定通配符：<? super T>，可以写入的元素类型是 T 及其子类型。
 * <p>
 * public class Collections {
 * public static <T> void copy(List<? super T> dest, List<? extends T> src)
 * {
 * for (int i=0; i<src.size(); i++)
 * dest.set(i,src.get(i));
 * }
 * }
 */

/**
 * 总结：
 * 使用 List<? extends C> list 这种形式（上边界限定通配符），
 * 表示 list 可以引用一个 ArrayList ( 或者其它 List 的 子类 ) 的对象，
 * 这个对象包含的元素类型是 C 的子类型 ( 包含 C 本身）的一种。
 *
 * 使用 List<? super C> list 这种形式（下边界限定通配符），
 * 表示 list 可以引用一个 ArrayList ( 或者其它 List 的 子类 ) 的对象，
 * 这个对象包含的元素就类型是 C 的超类型 ( 包含 C 本身 ) 的一种。
 */