/*枚举*/
import java.lang.*;
import java.util.*;

public class TestEnum{
	public static void main(String[] args){
		System.out.println(Jijie.SPRING);
		System.out.println(Season.SPRING);
		for(Season s:Season.values()){
			System.out.print(s+"\t");
		}
		int a = new Random().nextInt(4);//随机生成数字：区间[0,4)
		System.out.println("");
		switch(Season.values()[a]){
			case SPRING:
			System.out.println("春天");
			break;
			case SUMMER:
			System.out.println("夏天");
			break;
			case AUTUMN:
			System.out.println("秋天");
			break;
			case WINDER:
			System.out.println("冬天");
			break;
		}
		
	}
}

enum Season {
	SPRING,SUMMER,AUTUMN,WINDER
}

class Jijie {
	public static final int SPRING = 0;
	public static final int SUMMER = 1;
	public static final int AUTUMN = 2;
	public static final int WINDER = 3;
}