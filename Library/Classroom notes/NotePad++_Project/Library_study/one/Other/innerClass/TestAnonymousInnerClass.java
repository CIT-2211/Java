import java.lang.*;
/*匿名内部类------------测试*/
public class TestAnonymousInnerClass {
	public void test1(A a) {
		a.run();
	}
	
	
	
	public static void main(String[] args) {
		TestAnonymousInnerClass t = new TestAnonymousInnerClass();
		t.test1 (new AImpl());
		System.out.println("改用匿名类来实现test1的run方法");
		
		t.test1(new A() {
			@Override
			public void run() {
				System.out.println("调用第一个匿名内部类");
			}
		});
		
		t.test1(new A() {
			@Override
			public void run () {
				System.out.println("调用第二个匿名内部类");
			}
		});
	}
}

class AImpl implements A {
	
	@Override
	public void run() {
		System.out.println("测试接口文件");
	}
}


interface A {
	void run();
}