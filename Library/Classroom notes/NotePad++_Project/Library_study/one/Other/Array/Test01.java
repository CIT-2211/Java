import java.lang.*;

public class Test01 {
	public static void main (String[] args) {
		int [] s;
		s = new int [10];
		s[0] = 1;
		s[1] = 2;
		System.out.println(s[0]);
		System.out.println(s[1]);
		
		for(int i=0; i < s.length; i++) {
			s[i] = 2*i + 1;
			System.out.println(s[i]);
		}
		System.out.println("");
		System.out.println("");
		
		count [] Count = new count [10];
		count c1 = new count(1,11);
		count c2 = new count(2,12);
		Count[0] = c1;
		Count[1] = c2;
		
		System.out.println(Count[0].toString());
		System.out.println(Count[0].getAge());
		System.out.println(Count[0].getId());
		
	}
}

class count {
	private int age;
	private int id;
	public count () {
		
	}
	public count (int age, int id) {
		this.age = age;
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString (){
		return "Count [age = " + age + ", id = " + id + "] ";
	}
}

