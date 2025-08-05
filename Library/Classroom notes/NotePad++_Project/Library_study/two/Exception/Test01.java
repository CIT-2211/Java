import java.util.regex.*;
/*测试运行时异常和处理方式*/
public class Test01 {
	public static void main(String[] agrs){
		/*算数异常*/
		// System.out.println("## 测试算数异常");
		// try{
			// int a = 1/0;
		// }catch (Exception e){
			// e.printStackTrace();//打印错误信息
		// }
		// System.out.println("* 算数异常 *");
		
		/*空指针异常*/
		// // System.out.println("## 空指针异常");
		// String a = null;
		// try{
			// System.out.println(a.charAt(0));
		// }catch (Exception e){
			// e.printStackTrace();
			// System.out.println("* 空指针异常 *");
		// }
		
		
		/*类型转换异常*/
		// Animal cat = new Cat();
		// Dog d = (Dog)a;
		
		/*数组越界异常*/
		// System.out.println("## 数组越界异常");
		// int[] arr = new int[5];
		// try{
		// System.out.println(arr[5]);
		// } catch (Exception e){
			// e.printStackTrace();
			// System.out.println("* 数组越界异常 *");
		// }
		
		
		/*数字格式化异常*/
		System.out.println("## 数字格式化异常");
		String str2 = "123asd";
		try{
			System.out.println(Integer.parseInt(str2));
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("* 数字格式化异常 *");
		}
	}
}