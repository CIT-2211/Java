import java.until.*

public class c1{
	int id;
	String name;
	String pwd;
	
	public c1(){
		
	}
	public c1(int _id){
	  this.id = _id;
	}
	public c1(int id,String pwd){
	  this.id = id;
	  this.pwd = pwd;
	}
	public c1 (int id,String name,String pwd){
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}
	public static void main(String[] args){
		c1 u = new c1();
		c1 u2 = new c1();
	}
} 