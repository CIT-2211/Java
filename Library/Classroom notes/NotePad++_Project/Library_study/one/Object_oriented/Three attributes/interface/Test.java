import java.lang.*;

public class Test {
	
	public static void main (String[] args){
		Angel a = new Angel();
		a.fly();
		a.helpOther();
		System.out.println(Volant.Fly_HIGHT);
		
		Volant a2 = new Angel();
		a2.fly();
		//a2.helpOther();	//用不了，helpOther()方法是HONEST接口里面的，是Angel类来实现的，如果想要调用，要转型
		
		
		System.out.println("===========================测试默认方法============================");
		TestDefault td = new TestDefaultImpl01();
		td.printInfo();
		td.moren();
		System.out.println("===========================测试静态方法============================");
		TestDefault.testStatic01();
		TestDefaultImpl01.testStatic01();
	}
}