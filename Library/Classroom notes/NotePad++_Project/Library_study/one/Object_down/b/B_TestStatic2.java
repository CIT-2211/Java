public class B_TestStatic2{
	
	static String university;
	
	static{
		System.out.println("执行类的初始化工作！");
		university = "常州理工大学_计算机与科学研究院";
		printlnUniversity();
	}
	public static void printlnUniversity(){
		System.out.println(university);
	}
	public static void main(String[] args){
		
	}
}