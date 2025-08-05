import java.lang.*;

public class TestSuper01{
	public static void main(String[] args){
		new ChildClass(1,2).f();//匿名对象
	}
}


class FatherClass{
	protected int value;
	public FatherClass(){
		
	}
	public void f(){
		value = 100;
		System.out.println("FatherClass.value:" + value);
	}
}
class ChildClass extends FatherClass{
	private int value;
	private int age;
	
	public ChildClass(int value,int age){
		this.value = value;
		this.age = age;
	}
		
	public void f(){
		super.f();
		value = 200;
		System.out.println("ChildClass.value: " + value);
		System.out.println(value);
		System.out.println(super.value);
}
	public void f2(){
		System.out.println(age);
	}
}