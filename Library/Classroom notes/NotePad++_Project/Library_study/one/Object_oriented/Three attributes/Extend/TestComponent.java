import java.lang.*;
/*组合式学习*/
public class TestComponent{
	public static void main(String[] args){
		Student2 s = new Student2("张三", (byte)20, 175, 65, "计算机科学");
        s.action(); // 调用学生的行为
        Student2.count_Student(); // 统计学生数量
	}
}

class Person2{
	private String name;
	private byte age;
	private int height;
	private	int	weight;
	private static int count = 0;
	public Person2(String name,byte age,int height,int weight){
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
		count++;
		System.out.println("你好，我是"+name);
	}
	public static void count(){
		System.out.println("当前已经创建"+count+"个对象！");
	}
	public void action(){
		System.out.println(name+"已经复苏！");
	}
	public void studyin(){
		System.out.println(name+"正在学习！");
	}
	public void rest(){
		System.out.println(name+"正在养精蓄锐！");
	}
	public String gitName(){
		return name;
	}
	public byte age(){
		return age;
	}
}

class Student2{
		private Person2 Person2;
		private String major;
		private static int count_Student = 0;
		private boolean action_major;
		
		public Student2(String name,byte age,int height,int weight,String major){
			this.Person2 = new Person2(name, age, height, weight);
			this.major = major;
			this.action_major = false;
			count_Student++;
			System.out.println("加入了学生"+Person2.gitName());
		}
		
		public static void count_Student(){
		System.out.println("游戏开始！玩家已经复苏！");
		System.out.println("当前已经复苏了"+count_Student+"个学生！");
		}
		public void attendLab() {
			this.action_major = true;
		}
		
		public void action(){
		if(this.action_major){
		System.out.println("学生"+Person2.gitName()+"已经开始行动！");	
		System.out.println(Person2.gitName() + "正在" + major + "实验室做实验");
		}else{
		System.out.println("学生"+Person2.gitName()+"正在上课！");
			}
		}
		public void	rest(){
			Person2.rest();
		}
		public void studyin(){
			Person2.studyin();
		}
		
	}