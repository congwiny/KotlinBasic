package generics;

public class Holder<T> {
    private T value;
    public Holder() {}
    public Holder(T val) { value = val; }
    public void set(T val) { value = val; }
    public T get() { return value; }
    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    /**
     * 在 Holer 类中，set() 方法接受类型参数 T 的对象作为参数，get() 返回一个 T 类型，
     * 而 equals() 接受一个 Object 作为参数。
     * fruit 的类型是 Holder<? extends Fruit>，所以set()方法不会接受任何对象的添加，但是 equals() 可以正常工作。
     *
     */
    public static void main(String[] args) {
        Holder<Apple> apple = new Holder<Apple>(new Apple());
        Apple d = apple.get();
        apple.set(d);
        // Holder<Fruit> Fruit = Apple; // Cannot upcast
        Holder<? extends Fruit> fruit = apple; // OK
        Fruit p = fruit.get();
        d = (Apple)fruit.get(); // Returns ‘Object’
        try {
            Orange c = (Orange)fruit.get(); // No warning
        } catch(Exception e) { System.out.println(e); }
        // fruit.set(new Apple()); // Cannot call set()
        // fruit.set(new Fruit()); // Cannot call set()
        System.out.println(fruit.equals(d)); // OK
    }
}
