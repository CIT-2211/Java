import java.lang.*;

public class TestString1 {
	public static void main (String[] args){
		
		String s1 = "core Java";
		String s2 = "Core Java";
		String s3 = "I love Computer and I very love AI !";
		
		System.out.println(s1.charAt(3));	//提取下表为3的字符(位置为3的字符)
		System.out.println(s2.length());	//测算字符长度
		System.out.println(s1.equals(s2));	//比较s1和s2的字符内容是否相等
		System.out.println(s1.equalsIgnoreCase(s2));	//比较两个字符串(忽略大小)
		
		System.out.println(s1.indexOf("Java"));		//字符串s1中是否包含Java，包含，返回第一个字符的位置
		System.out.println(s1.indexOf("student"));	//字符串中不包含，返回-1	
		System.out.println(s3.lastIndexOf("love"));	//从后往前找，返回第一个字符的位置
		System.out.println(s3.indexOf("love"));		//对照试验组
		
		String s = s1.replace(' ','&');		//将s1中的空格替换成&,值返回新字符串s
		System.out.println("result s: "+ s);
		

		
	}
}