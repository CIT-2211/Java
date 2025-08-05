import java.lang.*;
import java.util.*;
import java.io.*;
/* ## 测试Math算数 ##*/
public class TestMath {
	public static void main (String[] args) {
		
		/* #索取整数相关操作 */
		System.out.println("取整数相关操作");
		System.out.println(Math.ceil(3.2));	//向上取整
		System.out.println(Math.floor(3.2));//向下取整
		System.out.println(Math.round(3.2));//四舍五入
		System.out.println(Math.round(3.8));//四舍五入
		
		/* ##绝对值、开方、a的b次幂等操作 */
		System.out.println("绝对值、开方、a的b次幂等操作");
		System.out.println(Math.abs(-95));	//绝对值
		System.out.println(Math.sqrt(64));	//开方
		System.out.println(Math.pow(3,2));	//3^2
		System.out.println(Math.pow(2,3));	//2^3
		
		/*	##Math中常用的常量##	*/
		System.out.println("Math中常用的常量");
		System.out.println(Math.PI);		//π, 3.1415926
		System.out.println(Math.E);			//自然对数, 2.718
		/*	##随机数##	*/
		System.out.println(Math.random());	//随机数，区间[0,1)
		
		
	}
}