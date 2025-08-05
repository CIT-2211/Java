import java.lang.*;

public class Test{
	public static void main (String[] args){
		User u  = new User();
		u.setId(100);
		u.setName("张三");
		u.setMan(true);
		
		u.printUserInfo();
	}
}