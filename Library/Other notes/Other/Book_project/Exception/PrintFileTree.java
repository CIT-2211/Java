import java.util.*;
import java.lang.*;
import java.io.*;
//递归打印目录结构
public class PrintFileTree {
	public static void main(String[] args){
		File f = new File("H:\\VmWare\\Java");
	}
	
	static void printFile(File file,int level){
		for(int i=0;i<level;i++){
			System.out.println("-");
		}
		//输出文件名
		System.out.println(file.getName());
		if(file.isDirectory()){
			File[] files = file.listFiles(); //列出他的所有文件、子目录
			for(File temp:files){
				printFile(temp,level+1);
			}
		}
	}
}