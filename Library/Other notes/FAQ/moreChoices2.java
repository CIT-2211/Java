package org.example;

import java.util.*;
import java.io.*;
import java.lang.*;
/* 从键盘上输入一个月份，然后判断该月份的天数，（a、假设不是闰年，b假设是闰年，c、假设是随机年份） */
public class moreChoices2{
	public static void main (String[] args) throws IOException{
		System.out.print("请您输入月份: ");
		BufferedReader buf = new BufferedReader (new InputStreamReader(System.in));
		String str = buf.readLine();
		int month = Integer.parseInt(str);
		int days;
		switch(month){
			case 2:  days = 28;
			break;
			
			case 4:
			case 6:
			case 9:
			case 11:
			days = 30;
			break;
			
			default: days = 31;
			
		}
		System.out.println(month + "月份为: " + days + "天");
	}
}