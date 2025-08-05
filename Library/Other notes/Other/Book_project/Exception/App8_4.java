public class App8_4{
	public static void main (String[] args) {
		int[] a = {1,2,3,4};
		try{
			System.out.println("各个元素和为： " + sumArray(a));
		}
		catch(Exception e){
			System.out.println("异常类的名称是： "+e);
			e.printStackTrace();
			System.out.println("异常对象信息是： "+ e);
			System.out.println(e.toString());
			StackTraceElement[] te = e.getStackTrace();
			for(int i=0;i<te.length;i++){
				System.out.println("被调用的方法: "+te[i].getMethodName());
				System.out.println("被调用的方法所属类: "+te[i].getClassName());
				System.out.println("被调用的方法所在行号: "+te[i].getLineNumber());
			}
		}
		
	}
	private static int sumArray(int[] array){
			int sum = 0;
			for(int i=0;i<=array.length;i++){
				sum+=array[i];
			}
			return sum;
		}
}