package org.example;//给定一个分数，按照不同的分段将其评定为A、B、C、D和E五个等级。	多重条件选择语句
import java.util.*;
import java.io.*;
import java.lang.*;

public class moreChoices{
	public static void main (String[] args) throws IOException{
		System.out.print("请您输入一个数：");
		BufferedReader buf  = new BufferedReader(new InputStreamReader(System.in));
		String str = buf.readLine();
		int num = Integer.parseInt(str);
		char grade;
		if(num >= 90){
			grade = 'A';
		}else if(num >= 80){
			grade = 'B';
		}else if(num >= 70){
			grade = 'C';
		}else{
			grade = 'E';
		}
		System.out.println("成绩评定为：" + grade);
	}
}