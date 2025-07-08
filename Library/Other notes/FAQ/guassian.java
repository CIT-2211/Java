package org.example;

import java.util.*;
import java.io.*;
import java.lang.*;
/*高斯运算----a、累加和，b、奇、偶数和*/
public class guassian{
	public static void main (String[] args) throws IOException {
		System.out.println("请输入一个整数： ");
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		String str = buf.readLine();
		int mun = Integer.parseInt(str);
		int n=0,n2=0,n3=0;
		for(int i=1;i<=mun;i++){
			n += i;
		}
		for(int j=1;j<=mun;j++){
			if(j % 2 == 0){
				n2 += j;
			}else{
				n3 += j;
			}
		}
		
		/*数学函数快速推导：
		n = mun*(mun+1)/2;
		
		if (num % 2 == 0) n2 = (num / 2) * (num / 2 + 1);
		else n2 = ((num - 1) / 2) * ((num - 1) / 2 + 1);
		
		n3 = n - n2;
		*/
		System.out.println(mun + "累加和为： " + n);
		System.out.println(mun + "累加的偶数和为： " + n2);
		System.out.println(mun + "累加的奇数和为： " + n3);
	}
}