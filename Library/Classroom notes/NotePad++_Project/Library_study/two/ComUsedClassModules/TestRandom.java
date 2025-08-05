import java.lang.*;
import java.util.*;
// import java.io.*;

/* ## 测试random随机数 ##*/
public class TestRandom {
	public static void main (String[] args) {
			Random random = new Random();
			/*随机生成[0,1)之间的double类型的随机数*/
			System.out.println("随机生成[0,1)之间的double类型的随机数： " + random.nextDouble());
			/*随机生成int类型允许范围之内的整形数据*/
			System.out.println("随机生成int类型允许范围之内的整形数据: " + random.nextInt());
			/*随机生成[0,10)之间的int类型的随机数*/
			System.out.println("随机生成[0,10)之间的int类型的随机数： " + random.nextInt(10));
			/*随机生成[10,20)之间的int类型的随机数*/
			System.out.println("随机生成[10,20)之间的int类型的随机数： " + (20+random.nextInt(10)));
			/*随机生成[0,1)之间的float类型的随机数*/
			System.out.println("随机生成[0,1)之间的float类型的随机数： " + random.nextFloat());
			/*随机生成false或者true*/
			System.out.println("随机生成false或者true： " + random.nextBoolean());
			/*随机生成[0,10)之间的int类型的随机数（计算法获得）*/
			System.out.println("随机生成[0,10)之间的int类型的随机数： " + ((int)(random.nextDouble()*10)));//方法一
			System.out.println("随机生成[0,10)之间的int类型的随机数： " + random.nextInt(10));			  //方法二
			
	}
}