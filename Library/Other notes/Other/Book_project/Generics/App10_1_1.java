import java.lang.*;

public class App10_1_1 <T1,T2,T3>{
	private T1 name;
	private T2 age;
	private T3 sec;
	
	public void setName(T1 name){
		this.name = name;
	}
	public void setAge(T2 age){
		this.age = age;
	}
	public void setSec(T3 sec){
		this.sec = sec;
	}
	
	public T1 getName(){
		return name;
	}
	public T2 getAge(){
		return age;
	}
	public T3 getSec(){
		return sec;
	}
	
	public static void main (String[] args){
		App10_1_1<String, Integer, Character> player = new App10_1_1<>();
		
		player.setName("大卫");
        player.setAge(20);
        player.setSec('男');
		
		String PlayerName = player.getName();
        int PlayerAge = player.getAge();
        char PlayerSec = player.getSec();
		
		System.out.println("玩家姓名为： " + PlayerName);
		System.out.println("玩家年龄为： " + PlayerAge);
		System.out.println("玩家性别为: " + PlayerSec);
		
	}
}