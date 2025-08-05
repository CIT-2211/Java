import java.io.*;

/* ## 测试throws声明式异常处理方式 ##*/
public class Test03 {
	public static void main (String[] args) throws IOException{
		FileReader reader = null;
		try{
		reader = new FileReader("H:\\NotePad++\\library\\Java\\NotePad++_Project\\Library_study\\two\\Exception\\file");
		char c = (char)reader.read();
		char c2 = (char)reader.read();
		char c3 = (char)reader.read();
		System.out.println(""+c+"、"+c2+"、"+c3);
		// } catch	(FileNotFoundException e){
			// e.printStackTrace();
		// } catch (IOException e) {
			// e.printStackTrace();
		}finally {
			System.out.println("调用finally方法，结束使用资源！");
			try{
			if(reader != null){	
			reader.close();
				}
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
}