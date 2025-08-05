public class B_TestStatic{
	int id;
	String name;
	
	static String company = "字节跳跃";
	public B_TestStatic(int id,String name){
		this.id = id;
		this.name = name;
	}
	public void longin() {
		System.out.println("name");
	}
	public static void printcompany(){
		System.out.println(company);
	}
	
	public static void main(String[] args){
		B_TestStatic a = new B_TestStatic(1,"三叶");
		B_TestStatic.printcompany();
		B_TestStatic.company = "字节踊跃";
		B_TestStatic.printcompany();
	}
}