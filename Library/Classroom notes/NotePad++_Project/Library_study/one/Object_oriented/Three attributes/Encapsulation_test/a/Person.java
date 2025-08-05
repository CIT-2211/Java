import java.lang.*;

public class Person{
	private int testPrivate;
	default int testDefault;
	protected int testProtected;
	public int testPublic;
	
	public void test(){
		System.out.println(this.testPrivate);	
	}
	
	public static void main (String[] args){
		
	}
}