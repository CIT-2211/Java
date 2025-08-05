import java.lang.*;

public class TestPolym{
	public static void main(String[] args){
		anmalCry(new Dog());
		anmalCry(new Cat());
		anmalCry(new Animal());
		
		//
		Animal animal = new Dog("汉斯",(byte)5,'男');
		animal.shout();
		//Dog d = (Dog)animal;//通过强制转型（向上转型），使编译器赋添加Dog编译不报错
		//d.seeDoor();	//无法调用，编译器（Animal）不行，运行(Dog)时候行,转型问题
		//Cat c = (Cat)animal;//编译不报错，运行汇报异常：classCastException的错误
		//c.climbTree();
		//综合判断
	if(animal instanceof Cat){
		Cat c = (Cat)animal;
		c.climbTree();
	}
	//进行类所属判断
	if(animal instanceof Dog){
		Dog d = (Dog)animal;
		d.seeDoor();
	}
	
	}
	
	static void anmalCry(Animal a){
		//Animal a = new Dog() , Animal a = new Cat()
		a.shout();	//可以出现多态
		
	}
}