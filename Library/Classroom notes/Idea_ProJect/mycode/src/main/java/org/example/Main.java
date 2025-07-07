package org.example;
import java.util.*;
//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);

        int W = sc.nextInt();
        int count = 0;
        int m = 0;

            for (int j = 0; j <= W; j++) {
                System.out.print(m+" ");
                if (m == 0) {}
                else if ((m+1) % 5 == 0) {
                    System.out.println("");
                }
                m++;
                count++;
            }
        System.out.println("");
        System.out.println("for循环的次数: "+count);
        System.out.println("");

        count = 0;
        int sum = 0;
        int a = sc.nextInt();
            while(true){
                System.out.print(sum+" ");
                if (sum == 0) {}
                else if ((sum+1) % 5 == 0) {
                    System.out.println("");
                }
                sum++;
                count++;
                if (sum-1 == a){
                    break;
                }
            }
        System.out.println("");
        System.out.println("while循环的次数: "+count);


    }
}