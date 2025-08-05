import java.until.*;
/*测试继承*/
public class a{
	public static void main(String[] args){
		Student s1 = new Student("三叶",165,"三维设计与动画");
		
	}
	class Person{
		int age;
		String name;
		double weight;
		int height;
		public void rest(){
			System.out.println("请休息一下！");
		}
	}
	/*class Student{
		int age；
		String name;
		double weight;
		int height;
		String major;
		public rest(){
			System.out.println("请休息一下！");
		}
		public study(){
			System.out.println("请开始学习！");
		}
	}*/
	public class Student extends Person{
		String major;
		public void study(){
			System.out.println("请开始学习！");
			rest();
			System.out.println(this.name);
		}
		public class Student (String name,int height,String major){
		this.name = name;
		this.height = height;
		this.major = major;
		
		}
	}
	
	
}