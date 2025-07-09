public class a{
	String name;
	int age;
	
	public void show(){
		System.out.println(name);
	}
	
	public static void main(String[] args){
		/*创建p1对象*/
		a p1 = new a();
		p1.age = 24;
		p1.name = "张三";
		p1.show();
		
		/*创建p2对象*/
		a p2 = new a();
		p2.age = 35;
		p2.name = "李四";
		p2.show();
		
		a p3 = p1;
		a p4 = p1;
		
		p4.age = 80;
		System.out.println(p1.age);
	}
}
