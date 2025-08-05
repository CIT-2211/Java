import java.lang.*;


public interface Volant{
	
	public static final int Fly_HIGHT = 100;	//常量定义
	public abstract void fly();					//方法定义
	
}

interface Honest{
		void helpOther();
}

class GoodMan implements Honest{
	
	@Override
	public void helpOther() {
		System.out.println("不危害社会！");
	}
}

class BirdMan implements Volant {
	
	@Override
	public void fly() {
		System.out.println("我会飞了！");
	}
}

class Angel implements Volant, Honest {
	
	@Override
	public void fly() {
		System.out.println("自带飞行！");
	}
	@Override
	public void helpOther() {
		System.out.println("我很公正！");
	}
}

class Plan implements Volant {
	
	@Override
	public void fly () {
		System.out.println("飞机本来就会飞！");
	}
}

