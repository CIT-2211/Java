import java.lang.*;
import a.*;

public class Boy extends Person{
	public void play(){
		System.out.println(super.testProtected);
		System.out.println(super.testPublic);
		
		Person p = new Person();
		//System.out.println(p.testProtected);//错误，无法访问protected的对象
		
	}
	
	public static void main (String[] args){
		
	}
}