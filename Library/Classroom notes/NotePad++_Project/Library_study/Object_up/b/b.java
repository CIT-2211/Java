import java.util.*;

//System.out.println();
public class b{
	public static void main(String[] agrs){
	System.out.println("Learn to compute other recursions");
	System.out.println("Frist issue is Fibonacci");
	Fibonacci(5);
	
	}
	//fibonacci is very interin
	public static int Fibonacci(int n){
		if (n == 0){
			System.out.println("n1=" +n );
			System.out.println("n1 != 0");
			System.out.println("End of system");
			System.out.println();
			return 0;
		}else if (n == 1){
			System.out.println("n2=" +n );
			System.out.println("n2 != 1");
			System.out.println("End of system");
			System.out.println();
				return 1;
			}else{
				int num = Fibonacci(n-1) + Fibonacci(n-2);
				System.out.println("n = "+n);
				System.out.println("num = "+num);
				System.out.println();
				return num;
			}	
	}
	
	
}
