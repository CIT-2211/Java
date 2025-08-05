import java.lang.*;

public class TestString2 {
    public static void main(String[] args) {
        // 触发GC减少干扰
        System.gc();
        
        /* 使用String进行字符串拼接 */
        String str = "";
        long startMem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long startTime1 = System.currentTimeMillis();
        
        for (int i = 0; i < 50000; i++) {
            str = str + i;
        }
        long endMem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long endTime1 = System.currentTimeMillis();
        
        System.out.println("String占用内存: " + (endMem1 - startMem1) + " bytes");
        System.out.println("String占用时间: " + (endTime1 - startTime1) + " ms");
        
        // 再次触发GC
        System.gc();
        try { Thread.sleep(500); } catch (InterruptedException e) {} // 给GC时间
        
        /* 使用StringBuilder进行字符串拼接 */
        StringBuilder sb = new StringBuilder();
        long startMem2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long startTime2 = System.currentTimeMillis();
        
        for (int j = 0; j < 50000; j++) {
            sb.append(j);  //	增加
        }
        long endMem2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long endTime2 = System.currentTimeMillis();
        
        System.out.println("StringBuilder占用内存: " + (endMem2 - startMem2) + " bytes");
        System.out.println("StringBuilder占用时间: " + (endTime2 - startTime2) + " ms");
        
        // 验证结果
        System.out.println("String结果长度: " + str.length());
        System.out.println("StringBuilder结果长度: " + sb.length());
    }
}

//获得系统测试后内存空间	long num1 = Runtime.getRuntime().freeMemory();
//获得系统当前时间			long time1 = System.currentTimeMillis();

//获得对象测试所占内存		long startMem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//获得系统当前时间     	long startTime1 = System.currentTimeMillis();