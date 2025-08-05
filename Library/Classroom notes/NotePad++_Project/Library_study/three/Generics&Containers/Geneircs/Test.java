import java.lang.*;
/* 客户端代码，程序的入口 */
/*泛型具体类、 泛型普通类*/
public class Test {
	public static void main(String[] args){
		IGeneric<String> iGeneric = new IGenericImpl();
		String a = iGeneric.getName("Welcome Master");
		System.out.println(a);
		
		IGeneric<String> iGeneric2 = new IGenericImpl2();
		String a2 = iGeneric2.getName("IGenericImpl 2.0");
		System.out.println(a2);
	}
}