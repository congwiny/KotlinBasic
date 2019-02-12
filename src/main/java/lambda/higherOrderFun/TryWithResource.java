package lambda.higherOrderFun;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lambda 可以去除重复代码的一个常见模式是资源管理：
 * <p>
 * 先获取一个资源，完成一个操作，然后释放资源。
 * 这里的资源可以表示很多不同的东西： 一个文件、一个锁、一个数据库事务等。
 * 实现这个模式的标准做法是使用try/finally 语句。
 * 资源在try 代码块之前被获取，在finally 代码块中被释放。
 */
public class TryWithResource {

    /**
     * 文件是另一种可以使用这种模式的常见资源类型。
     * Java 7 甚至为这种模式引入了特殊的语法：try-with-resource 语句。
     *
     * 下面的代码清单展示了一个使用这个语句来读取文件第一行
     */
    static String readFirstLineFromFile(String path) throws IOException {
        //try-with-resources语法。最后br会自动释放资源
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    public static void main(String[] args) {
        try {
            String txt = readFirstLineFromFile("src/hello.txt");
            System.out.println(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
