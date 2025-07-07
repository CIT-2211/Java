import java.util.*;

public class c{
	int id;
	int age;
	String sname;
	
	double x,y;
    c(double _x,double _y){
		x = _x;
		y = _y;
	}
	public double getDistance(c p){
		double d = Math.sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y));
			return d;
	}
	public static void main(String[] args){
		//c teacher = new c();
		// c student = new c();
		
		//teacher.id = 1001;
		//teacher.sname = "„Ù—ÙÕº Èπ›";
		//teacher.age = 100;
		//System.out.println(teacher.id);
		//System.out.println(teacher.sname);
		//System.out.println(teacher.age);
		
		//teacher.study();
		//teacher.kickball();
		
		c c1 = new c(3.0,4.0);
		c origin = new c(0.0,0.0);
		System.out.println(c1.getDistance(origin));
		
		
		
	
	}
	void study(){
		System.out.println("I am learning now!");
	}
	void kickball(){
		System.out.println("I am playing football now!Cheering for me please!");
	}
}