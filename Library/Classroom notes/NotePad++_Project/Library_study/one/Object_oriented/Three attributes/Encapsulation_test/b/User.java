import java.lang.*;

public class User{
	private int id;
	private String name;
	private boolean man;
	
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
	public boolean getMan(){
		return true;
	}
	public void setMan(boolean man){
		this.man = man;
	}
	
	public void printUserInfo(){
		System.out.println(id);
		System.out.println(name);
		System.out.println(man);
	}
}