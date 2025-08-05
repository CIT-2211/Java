import java.lang.*;
/*测试抽象类*/
public abstract class Creature{
	public static void main (String[] args){
		
	}
	int age;
	public abstract void rest();
	public abstract void run();
	public void shout(){
		System.out.println("Animals are shouting,now! Take careful!");
	}
}

class Dog extends Creature{
	
	@Override
	public void rest(){
		System.out.println("Dog are having rest,now！Let is go!");
	}
	@Override
	public void run(){
		System.out.println("Dog are Running now!Looking out!");
	}
}

class cat extends Creature{
	
	@Override
	public void rest(){
		System.out.println("Cat are having rest,now！Let is go!");
	}
	@Override
	public void run(){
		System.out.println("Cat are Running now!Looking out!");
	}
}