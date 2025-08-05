import java.lang.*;
import java.util.Arrays;

//遍历和拷贝
public class Test03 {
	public static void main (String[] args) {
		String[] fruit = {"苹果","香蕉","菠萝","榴莲","葡萄","火龙果"};
		String[] basket = new String[7];
		for(int i=0; i<fruit.length;i++){
			System.out.println(fruit[i]);
		}
		System.out.println("======================对比分隔符====================");
		for(String F:fruit){
			System.out.println(F);
		}
		System.out.println("======================拷贝=========================");
		System.arraycopy(fruit,0,basket,0,fruit.length);
		for(String B: basket){
			System.out.println(B);
		}
		
		int [] a = {1,2,10,9,6,5};
		System.out.println(a);
		System.out.println(Arrays.toString(a));
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		System.out.println("该元素的索引为： " + Arrays.binarySearch(a,9));
		Arrays.fill(a,2,4,100);
		System.out.println(Arrays.toString(a));
	}
}



