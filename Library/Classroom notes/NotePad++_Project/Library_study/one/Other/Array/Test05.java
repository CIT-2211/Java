import java.lang.*;
import java.util.*;
//测试Object[][]二维数组存储表格数据
public class Test05 {
	public static void main (String[] args) {
		Object[] a1 = {1001,"小明",18,"教师","2-14"};
		Object[] a2 = {1002,"小李",19,"教授","9-10"};
		Object[] a3 = {1003,"小王",20,"院士","5-5"};
		Object[] [] emps = new Object[3][];
		emps[0] = a1;
		emps[1] = a2;
		emps[2] = a3;
		System.out.println(Arrays.toString(emps[0]));
		System.out.println(Arrays.toString(emps[1]));
		System.out.println(Arrays.toString(emps[2]));
		System.out.println("============分隔符============");
		for(int i=0;i<emps.length;i++){
			for(int j=0;j<emps[i].length;j++){
				System.out.print(emps[i][j] +" ");
			}
			System.out.println("");
		}
	}
}



