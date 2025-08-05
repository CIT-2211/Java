import java.lang.*;
import java.text.*;
import java.util.*;
//DecimalFormat df = new DecimalFormat(0.00);
//自定义一个简单包装类（练习）
public class MyInteger {
	private final int value;	//必须要初始化	
	private static MyInteger[] cache;	//缓存[-128,127]之间的数
	private static final int LOW = -128;
	private static final int HIGH = 127;
	static {
		cache = new MyInteger[HIGH-LOW+1];//值为256
		for(int i =LOW;i<=HIGH;i++){	//[-128,127]
			cache[i-LOW] = new MyInteger(i);
		}
		// System.out.println(Arrays.toString(cache));
	}
	
	//public MyInteger(){}构造器
	public MyInteger(int value) {
		this.value = value;
		
	}
	
	@Override
	public String toString() {
		return value + "";
	}
	
	public static MyInteger valueOf (int value){
		// return new MyInteger(value);
		if (value >= LOW && value <=HIGH) {
			return cache[value-LOW];
		}else {
			return new MyInteger(value);
		}
	}
	
	public int intValue() {
		return value;
	}
	
	public static void main (String[] args) {
		MyInteger a = new MyInteger(10);
		MyInteger b1 = MyInteger.valueOf(100);
		MyInteger b2 = MyInteger.valueOf(100);
		MyInteger b3 = MyInteger.valueOf(1000);
		MyInteger b4 = MyInteger.valueOf(1000);
		int c = b1.intValue();
		System.out.println(b1 == b2);
		System.out.println(b3 == b4);
		
	}
}
