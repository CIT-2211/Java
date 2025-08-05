import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

public class App8_8{
	public static void main(String[] args) throws IOException {
		try(Scanner in = new Scanner(Paths.get("Test\\a.txt")))
		{
			while (in.hasNext())
				System.out.println(in.nextLine());
		}
	}
}