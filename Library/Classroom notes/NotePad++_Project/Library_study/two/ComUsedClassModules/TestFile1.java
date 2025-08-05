import java.io.*;
import java.util.*;
import java.lang.*;

public class TestFile1 {
	public static void main(String[] args) /*throws Exception*/ {
		/*创建文件，绝对路径，获取项目路径*/
		System.out.println(System.getProperty("user.dir"));//当前项目的目录下
		// File f = new File("a.txt");		//相对路径：默认放在user.dir目录下面
		// f.createNewFile();				//创建文件
		// f.delete();
		
		File f = new File("H:\\b.txt");	//绝对路径
		/*f.createNewFile();*/
		try {
            if (f.createNewFile()) {
                System.out.println("文件创建成功！");
			} else {
                System.out.println("文件已存在或无法创建。");
            }
            // 删除文件
			if (f.delete()) {
                System.out.println("文件删除成功！");
            } else {
                System.out.println("文件删除失败。");
            }
        } catch (IOException e) {
            e.printStackTrace();
			}
		
		System.out.println("File是否存在： "+ f.exists());
		System.out.println("File是否是目录： "+f.isDirectory());
		System.out.println("File是否是文件： "+f.isFile());
		System.out.println("File最后修改时间： "+new Date(f.lastModified()));
		System.out.println("File的大小： "+f.length());
		System.out.println("File文件名： "+f.getName());
		System.out.println("File目录路径： "+f.getPath());
		System.out.println("File目录路径： "+f.getAbsolutePath());
		
		/*创建文件夹*/
		 File f3 = new File("H:\\VMword\\China\\inner");
		 boolean flag = f3.mkdir();		//目录中有一个不存在，则不会创建整个目录树
		 System.out.println(flag);		
		 
		 File f4 = new File("H:\\VMword\\China\\inner");
		 boolean flag2 = f4.mkdirs();		//目录结构中有一个不存在也没关系：创建整个目录树
		 System.out.println(flag);
	}
}