import java.util.*;

public class a1{
	String name;
	a1 friend;
	
	public static void main (String[] agrs){
		a1 s1 = new a1();
		a1 s2 = new a1();
		
		s1.friend = s2;
		s2.friend = s1;
		s1 = null;
		s2 = null;
		
	}
	
}