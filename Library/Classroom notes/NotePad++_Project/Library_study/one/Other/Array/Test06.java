import java.lang.*;
import java.util.*;
//测试JavaBean来存储表格数据
public class Test06 {
	public static void main (String[] args) {
		Emp[] emps = {
					 new Emp (1001,"小明",18,"教师","2-14"),
	                 new Emp (1002,"小李",19,"教授","9-10"),
                     new Emp (1003,"小王",20,"院士","5-5")};
		for(Emp e:emps){
			System.out.println(e);
		}			 
	}
}	

class Emp{
	private int id;
	private String name;
	private int age;
	private String job;
	private String hiredate;
	
	public Emp(){}
	public Emp(int id,String name,int age,String job,String hiredate){
		this.id = id;
		this.name = name;
		this.age = age;
		this.job = job;
		this.hiredate = hiredate;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age){
		this.age = age;
	}
	public String getJob(){
		return job;
	}
	public void setJob(String job){
		this.job = job;
	}
	public String hiredate(){
		return hiredate;
	}
	public void sethiredate(String hiredate){
		this.hiredate = hiredate;
	}
	
	@Override
	public String toString() {
		return "Emp{"+"id = "+id+", name = "+name+'\''+", age = "+age+", job = "+job+'\''+", hiredate = "+hiredate+'\''+'}';
	}
	
}



