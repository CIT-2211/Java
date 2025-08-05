import java.lang.*;
import java.util.*;
import java.io.*;
// import java.Math.*;
/*测试字符串相关类*/
public class TestString {
	public static void main (String[] args) {
		// String s1 = "abcdef";
		/*字符串截取*/
		// System.out.println("字符串截取测试");
		// String s2 = s1.substring(2,5);
		// System.out.println(s1);
		// System.out.println(s2);
		// System.out.println("");
		/*字符串拼接优化测试*/
		// System.out.println("字符串拼接优化测试");
		// String s3 = s1+s2;
		// String s4 = "abcdefcde";
		// String s5 = "abcdef"+"cde";
		// System.out.println(s3 == s4);
		// System.out.println(s5 == s4);
		// System.out.println(s3.equals(s4));
		// System.out.println(s5.equals(s4));
		
		/*字符转化*/
		// System.out.println(Integer.toHexString(s1.hashCode()));	//String => hash整数值 => 16进制
		
		/*测试StringBuffer和StringBuilder*/
		// StringBuilder sb = new StringBuilder("abc");
		// StringBuffer sb2 = new StringBuffer("abc");
		
		/*StringBuffer*/
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<7;i++){
			sb.append((char)('a'+i));	//追加单个字符串
		}
		System.out.println(sb.toString());	//转化成String输出
		sb.append(", I can sing my abc!");	//追加字符串
		System.out.println(sb.toString());	//转化成String输出
		
		/*StringBuffer,下面的方法同样适用于StringBuilder*/
		StringBuffer sb2 = new StringBuffer("## 常州大学 ##");
		sb2.insert(2,"❥Love").insert(2,"I ");	//插入字符串
		System.out.println(sb2);
		sb2.delete(0,2);//删除字符串	[0,2)
		System.out.println(sb2);
		sb2.deleteCharAt(0).deleteCharAt(0);//删除某个字符
		System.out.println(sb2);
		System.out.println(sb2.charAt(0));	//获取某个字符
		System.out.println(sb2.reverse());	//字符串逆序	
	}
}