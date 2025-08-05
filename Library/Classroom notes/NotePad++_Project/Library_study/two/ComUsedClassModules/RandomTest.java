import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random();
        int[] count1 = new int[10]; // 方法1计数
        int[] count2 = new int[10]; // 方法2计数
        final int TOTAL = 1_000_000; // 100万次测试
        
        // 测试方法1
        for (int i = 0; i < TOTAL; i++) {
            int num = (int)(random.nextDouble() * 10);
            count1[num]++;
        }
        
        // 测试方法2
        for (int i = 0; i < TOTAL; i++) {
            int num = random.nextInt(10);
            count2[num]++;
        }
        
        // 打印结果
        System.out.println("方法1结果：");
        for (int i = 0; i < 10; i++) {
            double percent = (double)count1[i] / TOTAL * 100;
            System.out.printf("%d: %.4f%%\n", i, percent);
        }
        
        System.out.println("\n方法2结果：");
        for (int i = 0; i < 10; i++) {
            double percent = (double)count2[i] / TOTAL * 100;
            System.out.printf("%d: %.4f%%\n", i, percent);
        }
    }
}