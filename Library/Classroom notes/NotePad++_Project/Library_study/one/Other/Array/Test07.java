import java.util.*;
import java.lang.*;
//测试Comparable接口（初始泛类）
public class Test07 {
	public static void main (String[] args) {
		Person[ ] msMans = {new Person(3,"a"),new Person(60,"b"),new Person(2,"c")};
		System.out.println(Arrays.toString(msMans));
		Arrays.sort(msMans);
		System.out.println(Arrays.toString(msMans));
	}
}

class Person implements Comparable {
	private int id;
	private int age;
	private String name;
	
	public Person(){
		
	}
	public Person(int id,int age,String name){
		super();
		this.id = id;
		this.age = age;
		this.name = name;
	}
	public Person(int age,String name){
		super();
		this.age = age;
		this.name = name;
	}
	@Override
	public String toString(){
		return this.name;
	}
	public int compareTo(Object obj){
		Person man = (Person)obj;
		if(this.age < man.age){
			return -1;
		}else if (this.age>man.age){
			return 1;
		}else{
			return 0;
		}
	}
}
