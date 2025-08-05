import java.lang.*;
import java.util.*;

public class GenericVarargs {
    
    // 泛型可变参数方法
    public static <T> void printItems(T... items) {
        for (T item : items) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
    
    // 带返回值的泛型可变参数方法
    public static <T> List<T> toList(T... elements) {
        return Arrays.asList(elements);
    }
    
    // 多类型参数的泛型可变参数
    public static <T, U> void printPairs(T[] first, U... second) {
        for (T t : first) {
            for (U u : second) {
                System.out.println(t + " - " + u);
            }
        }
    }

    public static void main(String[] args) {
        // 打印不同类型的数据
        printItems("Apple", "Banana", "Cherry");
        printItems(1, 2, 3, 4, 5);
        printItems(3.14, 2.718, 1.618);
        
        // 创建不同类型的列表
        List<String> fruits = toList("Apple", "Banana", "Cherry");
        List<Integer> numbers = toList(1, 2, 3, 4, 5);
        
        // 多类型组合
        String[] names = {"Alice", "Bob"};
        printPairs(names, 25, 30, 35);
    }
}