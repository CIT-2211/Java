import java.lang.*;
import java.util.*;
//多维数组的静态初始化和内存模型
public class Test04 {
	public static void main (String[] args) {
		int [] []a = new int [3] [];
		a[0] = new int[2];
		a[1] = new int[4];
		a[2] = new int[3];
		a[0][0] = 100;
		a[0][1] = 200;
		
		int[] []b = {{1,2,3},{3,4,5,6},{8,9}};
		System.out.println(b[1][3]);
		
		int [][] c = new int[3][];
		c[0] = new int[] {1,2};
		c[1] = new int[] {1,2,3};
		c[2] = new int[] {2,2,3,3};
		System.out.println(c[2][2]);
		System.out.println(Arrays.toString(c[0]));
		System.out.println(Arrays.toString(c[1]));
		System.out.println(Arrays.toString(c[2]));
	}
}



