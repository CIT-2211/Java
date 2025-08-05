import java.lang.*;

public class TestString {
	public static void main (String[] args){
		String s0 ;//null
		String s1 = "";//空
		String s2 = "java";
		String s3 = new String("java");
		
		System.out.println(s1.length());
		System.out.println(s2.length());
		System.out.println(s3.length());
		System.out.println(s0.length());
		
	}
}