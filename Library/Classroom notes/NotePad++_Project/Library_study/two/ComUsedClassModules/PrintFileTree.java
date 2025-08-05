// import java.util.*;
// import java.lang.*;
// import java.io.*;
// 递归打印目录结构
// public class PrintFileTree {
	// public static void main(String[] args){
		// File f = new File("H:\\VmWare\\Java");
		// printFile(f,0);
	// }
	
	// static void printFile(File file,int level){
		// for(int i=0;i<level;i++){
			// System.out.print("--");
		// }
		// 输出文件名
		// System.out.println(file.getName());
		// if(file.isDirectory()){
			// File[] files = file.listFiles(); //列出他的所有文件、子目录
			// for(File temp:files){
				// printFile(temp,level+1);
			// }
		// }
	// }
// }


import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class PrintFileTree {
    public static void main(String[] args) {
        File root = new File("H:\\NotePad++\\library\\a.txt");
        
        // 检查目录是否存在
        if (!root.exists()) {
            System.err.println("error：Path is Lost - " + root.getAbsolutePath());
            return;
        }
        
        // 检查是否为目录
        if (!root.isDirectory()) {
            System.err.println("error：Paths are not Directories  - " + root.getAbsolutePath());
            return;
        }
        
        System.out.println("Directories structure : " + root.getAbsolutePath());
        printFile(root, 0);
    }
    
    static void printFile(File file, int level) {
        // 打印当前层级缩进
        for (int i = 0; i < level; i++) {
            System.out.print("|--");
        }
        
        // 打印文件/目录名（区分类型）
        System.out.print(file.getName());
        if (file.isDirectory()) {
            System.out.print(" [DIR]");
        }
        System.out.println();
        
        // 如果是目录，递归处理子项
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            
            // 处理空目录或访问权限问题
            if (files == null) {
                System.out.println(getIndent(level + 1) + "无法访问或为空");
                return;
            }
            
            // 排序：目录优先，然后按字母顺序
            Arrays.sort(files, Comparator
                .comparing(File::isDirectory).reversed()  // 目录在前
                .thenComparing(File::getName));          // 按名称排序
            
            for (File temp : files) {
                printFile(temp, level + 1);
            }
        }
    }
    
    // 辅助方法：获取缩进字符串
    private static String getIndent(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("|  ");
        }
        return indent.toString();
    }
}