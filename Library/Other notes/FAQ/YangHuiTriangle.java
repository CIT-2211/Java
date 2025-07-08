package org.example;

import java.io.*;

public class YangHuiTriangle {
	public static void main(String[] args) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("请输入杨辉三角的行数: ");
		String str = buf.readLine();
		int n = Integer.parseInt(str);

		// 创建二维数组
		int[][] m = new int[n][];

		// 初始化每一行
		for (int i = 0; i < n; i++) {
			m[i] = new int[i + 1];  // 第i行有i+1个元素

			// 每行的第一个和最后一个元素总是1
			m[i][0] = 1;
			if (i > 0) {  // 第二行开始才有最后一个元素
				m[i][i] = 1;
			}
		}

		// 填充中间元素（从第三行开始）
		for (int i = 2; i < n; i++) {
			for (int j = 1; j < i; j++) {  // j从1到i-1
				m[i][j] = m[i - 1][j - 1] + m[i - 1][j];
			}
		}

		// ============= 打印等腰三角形杨辉三角 =============
		System.out.println("\n等腰三角形格式:");
		for (int i = 0; i < n; i++) {
			// 添加前导空格形成三角形效果
			for (int k = 0; k < n - i - 1; k++) {
				System.out.print("  ");
			}

			for (int j = 0; j <= i; j++) {
				System.out.printf("%4d", m[i][j]);
			}
			System.out.println();
		}

		// ============= 打印直角三角形杨辉三角 =============
		System.out.println("\n直角三角形格式:");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.printf("%-4d", m[i][j]); // 左对齐格式
			}
			System.out.println(); // 每行结束后换行
		}

		// ============= 可选：组合打印两种格式 =============
		System.out.println("\n组合格式（左侧等腰，右侧直角）:");
		for (int i = 0; i < n; i++) {
			// 等腰部分
			for (int k = 0; k < n - i - 1; k++) {
				System.out.print("  ");
			}
			for (int j = 0; j <= i; j++) {
				System.out.printf("%4d", m[i][j]);
			}

			// 添加分隔符
			System.out.print("    | ");

			// 直角部分
			for (int j = 0; j <= i; j++) {
				System.out.printf("%-4d", m[i][j]);
			}
			System.out.println();
		}
	}
}