import java.lang.*;
/*非静态成员内部类-------测试*/
public class Outer {
	public static void main (String[] args) {
		outClass p = new outClass();
		outClass.innerClass Inner =  p.new innerClass();
		Inner.show();
		System.out.println(Inner.coum);
		System.out.println("Inner测试结束！下一个inner");
		
		outClass.innerClass inner = new outClass().new innerClass();
		inner.show();
	}
}

class outClass {
	private int coum = 100;
	public void show(){
		System.out.println("调用外部类的方法!");
		System.out.println(coum);
	}
	
	
	public class innerClass {
		int coum = 50;
		public void show (){
			System.out.println("调用内部类的方法!");
			System.out.println(coum);
			System.out.println(outClass.this.coum);
			outClass.this.show();
		} 
	}
}