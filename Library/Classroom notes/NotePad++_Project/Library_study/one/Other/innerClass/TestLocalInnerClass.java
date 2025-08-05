import java.lang.*;
/*方法内部类----------测试*/
public class TestLocalInnerClass {
	public void show() {
		class Inner3 {
			public void fun() {
				System.out.println("测试方法内部类");
			}
		}
		new Inner3().fun();
	}
	
	public static void main(String[] args) {
		new TestLocalInnerClass().show();
	}
}