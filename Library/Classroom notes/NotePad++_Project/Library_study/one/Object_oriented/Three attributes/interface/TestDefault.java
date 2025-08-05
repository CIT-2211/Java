import java.lang.*;
// 测试默认方法 和 静态方法
public interface TestDefault{
	
	void printInfo();
	
	default void moren() {
		System.out.println("TESTDEFAULT.moren");
		System.out.println("测试默认方法！");
	}
	
	static void testStatic01(){
		System.out.println("测试静态方法！-------父类");
	}
}

class TestDefaultImpl01 implements TestDefault{
	@Override
	public void printInfo(){
		System.out.println("TESTDEFAULT.moren");
	}
	
	static void testStatic01(){
		System.out.println("测试静态方法！-------子类");
	}
	
}