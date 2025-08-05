import java.lang.*;
import java.util.*;

public class TestBinarySearch {
	public static void main (String[] args){
		int [] arr = new int [10];
		for(int i=0;i<arr.length;i++) {
			int count = (int)(Math.random()*10) + 1;
			arr[i] = count;
		}
	    arr[9] = 5;
		Arrays.sort(arr);
		System.out.println("arr源数组为: " + Arrays.toString(arr));
		
		int searchWord = 5;
		System.out.println("二分法查找的结果是： " + binarySearch(arr,searchWord));
		
	}
	
	public static int binarySearch(int[] array,int value) {
		int low = 0;
		int high = array.length - 1;
		while(low <= high){
			int middle = (low + high)/2;
			if(value == array[middle]){
				return middle;
			}
			if(value > array[middle]){
				low = middle + 1;
			}
			if(value < array[middle]){
				low = middle - 1;
			}
		}
		return -1;
	}
}