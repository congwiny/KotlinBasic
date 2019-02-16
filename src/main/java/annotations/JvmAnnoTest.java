package annotations;

public class JvmAnnoTest {
    public static void main(String[] args){
        //bar方法使用@JvmStatic，直接像静态方法一样使用。在Java中不用写A.Companion.bar()
        A.bar();
    }
}
