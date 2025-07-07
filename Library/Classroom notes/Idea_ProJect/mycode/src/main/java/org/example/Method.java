package org.example;

import java.util.Scanner;

public class Method {

    public static void main(String[] args) {
        /*黑盒方法*/
        System.out.println("I love you.");
        System.out.println("About you?");
        sayHello();
        add(10,10,12);
        Scanner sc = new Scanner(System.in);
        /*
        *方法测试项目：薪水扣除
        * 定义一个方法处理公司迟到问题
        * （1）、输入：迟到时间，月薪
        * （2）、处理逻辑：
        * <1>、迟到1~10分钟，警告
        * <2>、迟到11~20分钟，罚款100￥
        * <3>、迟到21~30分钟,罚款200￥
        * <4>、迟到30分钟以上,扣除半日工资
        * <5>、迟到半小时以上,扣除半天工资
        * <6>、迟到1小时以上,按矿工计算，扣除3日薪资
        * */
        System.out.println("The program starts running…………Please wait…………");
        System.out.println("successfully running now!");

        while(true){
            System.out.print("Name:");
            String str = sc.nextLine().trim();
            if (str.equalsIgnoreCase("Esc") || str.equalsIgnoreCase("exit")) {
                System.out.println("Welcome to use and see you next time.");
                break;
            }

            if (str.equals("Daniel")){
                System.out.println("Hello "+ str +",Hope you're in a good mood today.");
                int Daniel = DeSalary(60,8000);
                System.out.println("If you want to exit, type \"exit\".");
            } else if (str.equals("David")) {
                System.out.println("Hello "+ str +",It's pretty impressive.");
                int David = DeSalary(0,8000);
                System.out.println("If you want to exit, type \"exit\".");
            }else {
                System.out.println("No such person was found!");
                System.out.println("Re-enter now.");
            }
        }
            sc.close();


    }
    public static void sayHello() {
        System.out.println("I love you too.");
    }
    public static int add(int a, int b, int c) {
        int sum = a + b + c;
        System.out.println(sum);
        return sum;//返回值，结束方法运行
    }
    public static int  DeSalary (int LateTime, int salary) {
        System.out.println("Number of days late: " + LateTime +" minutes");
        System.out.println("Your Salary: " + salary +"$");
        int penalty = 0;
        int DaySalary = (int)(salary/22.5);
        if (LateTime < 10) {
            System.out.println(" Unbelievably cute little guys!Warning!");
        } else if (LateTime > 10 && LateTime <= 20) {
            System.out.println("Penalty for 100$!");
            penalty = 100;
        }else if (LateTime > 20 && LateTime <= 30) {
            System.out.println("Penalty for200!");
            penalty = 200;
        }else if (LateTime > 30 && LateTime <= 60) {
            System.out.println("Penalty for half a day's pay!");
            penalty = DaySalary/2;
        }else {
            System.out.println("Penalty for three days' pay!");
            penalty = DaySalary*3;
        }
        return salary-penalty;
    }

}
