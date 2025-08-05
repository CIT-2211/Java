import java.lang.*;

public class TestString2 {
	public static void main (String[] args){
		String s = "";
		String s1 = "How are you ?";
		
		System.out.println(s1.startsWith("How"));	//是否以How开头
		System.out.println(s1.endsWith("you"));		//是否以you结尾
		s = s1.substring(4);	//提取字符串，从下表为4的字符开始直至字符串结尾为止
		System.out.println(s);
		s = s1.substring(4,7);	//提取字符串，区间为[4,7)之间
		System.out.println(s);
		s = s1.toLowerCase();	//转小写
		System.out.println(s);
		s = s1.toUpperCase();	//转大写
		System.out.println(s);
		
		String s2 = "   How old are you??  ";
		s = s2.trim();	//去除字符串首尾空格，中间空格不去除
		System.out.println(s);
		System.out.println(s2);	//	因为字符串为不可变字符序列，故s2不会发生改变
		
	}
}