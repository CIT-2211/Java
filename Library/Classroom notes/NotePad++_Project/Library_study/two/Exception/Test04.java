import java.io.*;

/*## try-with-resource  "自动关闭" ##*/
public class Test04 {
	public static void main (String[] args) {
		
		try(FileReader reader = new FileReader("H:\\NotePad++\\library\\Java\\NotePad++_Project\\Library_study\\two\\Exception\\file");) 
		{
		char c = (char)reader.read();
		char c2 = (char)reader.read();
		char c3 = (char)reader.read();
		System.out.println(""+c+"、"+c2+"、"+c3);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}