//FileName: App10_1.java 	泛型类的应用
import java.lang.*;

public class App10_1 <T>{
	private T obj;
	public T getObj(){
		return obj;
	}
	public void setObj(T obj){
		this.obj = obj;
	}
	public static void main(String[] args){
		App10_1<Integer> age = new App10_1<Integer>();
		App10_1<String> name = new App10_1<String>();
		name.setObj("陈 磊");
		String newName = name.getObj();
		System.out.println("姓名： " + newName);
		
		age.setObj(25);
		int newAge = age.getObj();
		System.out.println("年龄： " + newAge);
	}
}