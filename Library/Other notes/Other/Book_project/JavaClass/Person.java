
class Person {
	private String name;
	private int age;
	public Person () {
		System.out.println("调用了个人类的构造方法Person()");
	}
	public void setNameAge(String name, int age){
		this.name = name;
		this.age = age;
	}
	public void show() {
		System.out.println("姓名： "+name+" 年龄： "+age);
	}
}

class Student extends Person {
	private String department;
	public Student() {
		System.out.println("调用了学生类的构造方法Student()");
	}
}