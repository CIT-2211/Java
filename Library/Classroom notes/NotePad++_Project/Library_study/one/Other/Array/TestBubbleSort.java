import java.util.*;
import java.lang.*;

public class TestBubbleSort {
	public static void main (String[] args) {
		
		int[] values = new int [10];
		for(int i=0;i<values.length;i++){
			int a = (int)(Math.random() * 10) + 1;
			values[i] = a;
		}
		//Arrays.sort(values);
		System.out.println("values源数组为： " + Arrays.toString(values));
		
		System.out.println();
		// bubbleSort(values);
		bubbleSort2(values);
	}
	
	public static void bubbleSort(int[] values) {
	int temp;
	System.out.println("## 使用未被优化的原始冒泡排序");
	for (int i=0;i<values.length-1;i++){
		for (int j=0;j<values.length-1-i;j++){
			if(values[j] > values [j+1]){
				temp = values[j];
				values[j] = values[j+1];
				values[j+1] = temp;
				}
			}
		System.out.println("第" + (i+1) + "趟顺序为: " + Arrays.toString(values));
		}
	}
	
	public static void bubbleSort2(int[] values) {
	int temp;
	System.out.println("## 使用被优化的原始冒泡排序");
	for (int i=0;i<values.length-1;i++){
		boolean flag = false;
		for (int j=0;j<values.length-1-i;j++){
			if(values[j] > values [j+1]){
				temp = values[j];
				values[j] = values[j+1];
				values[j+1] = temp;
				flag = true;
				}
			}
			if(!flag){
				break;
				}
			System.out.println("第" + (i+1) + "趟顺序为: " + Arrays.toString(values));
		}
	}
}