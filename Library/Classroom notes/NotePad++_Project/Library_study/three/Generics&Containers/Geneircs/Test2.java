import java.lang.*;

public class Test2 {
	public static void main(String[] args){
		// MethodGeneric methodGeneric = new MethodGeneric();
		// methodGeneric.setName("JVM自动推断类型");
		// Integer age = methodGeneric.getAge(1024);
		
		MethodGeneric methodGeneric = new MethodGeneric();
		String[] arr = new String []{"a","b","c"};
		Integer[] arr2 = new Integer[] {1,2,3,4,5};
		methodGeneric.method(arr);
		methodGeneric.method(arr2);
	}
}