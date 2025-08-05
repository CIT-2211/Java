//初入泛型：
//本质：数据类型的参数化处理(Java5以后出现)

import java.lang.*;

public class Generic <T>{
	private T flag;
	
	public void setFlag(T flag){
		this.flag = flag;
	} 
	public T getFlag(){
		return this.flag;
	}
	
	public static void main (String[] args){
		Generic<String> generic = new Generic<String>();
		generic.setFlag("Welcom dress!!");
		String flag = generic.getFlag();
		System.out.println(flag);
		
		Generic<Integer> generic1 = new Generic<Integer>();
		generic1.setFlag(123);
		Integer flag1 = generic1.getFlag();
		System.out.println(flag1);
	}
}