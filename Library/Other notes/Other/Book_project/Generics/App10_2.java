import java.lang.*;

public class App10_2{
	public static void main(String[] args){
		Integer[] num ={1,2,3,4,5};
		String[] str = {"红","橙","蓝","绿","紫"};
		App10_2.display(num);
		App10_2.display(str);
	}
	
	public static <T> void display (T[] list) {
	for(int i = 0; i<list.length;i++) {
		System.out.print(list[i]+"\t");
	}
	System.out.println();
}
}

