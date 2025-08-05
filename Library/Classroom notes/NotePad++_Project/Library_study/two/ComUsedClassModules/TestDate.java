import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;
/** ## 测试Date类及其相关方法 ## **/
public class TestDate {
	public static void main(String[] args) {
		long NowNum = System.currentTimeMillis();
		System.out.println(NowNum);
		Date date1 = new Date();	//当前的值
		System.out.println("当前的时间为： "+date1);
		Date date2 = new Date(-21L*365*24*3600*1000);
		System.out.println("过去的时间为： "+date2);
		System.out.println(date1.getTime());
		System.out.println(date2.equals(date1));
		System.out.println(date2.before(date1));//2是否在1之前
		System.out.println(date2.after(date1));//2是否在1之后
		
		
	}
}