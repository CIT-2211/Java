package org.example;

import java.util.*;
import java.io.*;
import java.lang.*;
public class UpInput{
	public static void main (String[] args) throws IOException{
		System.out.println("从键盘输入数据的两种方法");
		System.out.println("一、readLine()异常处理法");
		System.out.println("请输入字符串：");
		
		try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))){	//声明buff为BufferedReader的成员变量，BufferedReader类已经在java.io的库中定义了,并创建buff对象
		String str;
		StringBuffer mun = new StringBuffer();
		while(true){
			str = buff.readLine();//StringBuffer类与String区别，StringBuffer每次操作不是产生新的对象，而是数据本身。
			if(str == null || str.isEmpty() || str.equalsIgnoreCase("exit")){//isEmpty和equalsIgnoreCase调用java.lang的方法
				break;
			}
			mun.append(str).append("\n");
		}
		
		System.out.println("您输入的字符串是：" + str);
		System.out.println(mun.toString());
		}
		System.out.println("");
		System.out.println("");
		System.out.println("二、Scanner方法来处理");
		Scanner sr = new Scanner(System.in);
		int a = sr.nextInt();
		//char b = sr.nextChar();
		String c = sr.nextLine();
		System.out.println("数值a的值为：" + a);
		//System.out.println("字符b的值为：" + b);
		System.out.println("字符串c的值为：" + c);
		/*Scanner() 和 readline()方法不可以一起使用，会有被注销的风险*/
		/*next()是读取空白文字结束，nextLine()是读取一整行结束*/
		
	}
}