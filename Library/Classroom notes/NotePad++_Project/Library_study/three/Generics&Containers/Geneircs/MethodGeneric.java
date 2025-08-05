import java.lang.*;

public class MethodGeneric {
	
	public <T> void method(T ... args){
		for(T t: args){
			System.out.print(t+"\t");
		}
		System.out.println();
	}
	
	public <T> void setName(T name){
		System.out.println(name);
	}
	
	public <T> T getAge (T age){
		System.out.println(age);
		return age;	
	}
}