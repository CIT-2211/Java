// package two.ComUsedClassModules;
import java.lang.*;
public class TestIntegar {
	public static void main (String[] args) {
		Integer i = new Integer(9);			//java9已经废弃使用
		Integer j = Integer.valueOf(10);	//官方推荐
		int a = j.intValue();				//把包装类对象转成基本数据类型
		double b = j.doubleValue();			//
		/*把字符串转成数字*/
		Integer m = Integer.valueOf("456");
		//Integer n = Integer.valueOf("456abc");//包含非数字的东西，报错NumberFormatException
		
		
		System.out.println("int的最大值为： " + Integer.MAX_VALUE);
		System.out.println("int的最大小值为： " + Integer.MIN_VALUE);
		/*测试自动装箱、拆箱操作*/
		Integer x = 100;	//编译器： Integer x = Integer.valueOf(100);
		int y = x;			//编译器： int y = x.intValue();
		// 常见问题---空指针
		//Integer z = null;
		// int z2 = z;			//编译器： int z2 = z.intValue();	(报空指针错误	)
		
		/*测试缓存问题*/
		//自动装箱时，[-128,127]之间的数有缓存！
		System.out.println("测试缓存问题");
		Integer x1 = 100;
		Integer x11 = valueOf(100);
		Integer x2 = 100;
		Integer x3 = 1000;
		Integer x4 = 1000;
		System.out.println(x1 == x2);		//比较的是地址对象
		System.out.println(x3 == x4);
		System.out.println(x1.equals(x2));
		System.out.println(x3.equals(x4));	//比较的是逻辑值
	}
}