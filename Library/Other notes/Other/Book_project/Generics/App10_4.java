//FileName: App10_4.java
class GeneralType<T extends Number> implements AutoCloseable {
	T obj;
	public GeneralType(T obj) {
		this.obj = obj;
	}
	public T getObj(){
		return obj;
	}
	public void setObj(T obj){
		this.obj = obj;
	}
	@Override
    public void close() {
        System.out.println("关闭资源: " + obj);
        // 实际项目中这里会释放资源（如关闭文件、网络连接等）
    }
}

public class App10_4 {
	public static void main (String[] args){
		try(GeneralType<Integer> num = new GeneralType<>(7);GeneralType<Double> dbl = new GeneralType<>(3.14)) {
            System.out.println("整数参数: " + num.getObj());
			System.out.println("浮点参数: " + dbl.getObj());
        } // 自动调用close()
		
		// try(GeneralType<Double> dbl = new GeneralType<>(3.14)) {
            // System.out.println("浮点参数: " + dbl.getObj());
        // }
		
		// GeneralType <Integer> num = new GeneralType <Integer>(7);
		// System.out.println("给出的参数是： " + num.getObj());
		
		/*测试使用限制之外的实际类型传导*/
		// try(GeneralType <String> str = new GeneralType <String>("Error！");)
		// {
			// System.out.println("给出的参数是： " + str.getObj());
		// }
		
		
	}
}