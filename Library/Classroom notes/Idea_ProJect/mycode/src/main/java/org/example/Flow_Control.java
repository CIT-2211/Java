package org.example;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Flow_Control {
    public static void main(String[] args) {
            /*if */
        //骰子项目
        int rand = (int)(Math.random()*6)+1;
        System.out.print(rand);

        Random rand1 = new Random();
        int rand2 = rand1.nextInt(6)+1;
        System.out.print("、 "+ rand2 + "、 ");

        int rand3 = (int)(Math.random()*6)+1;
        System.out.print(rand3);

        int count = rand + rand2 + rand3;
        System.out.println("    今天的得分是：" + count);
        if (count > 15) {
            System.out.println("今天手气不错！再来一次如何？");
        }
        else if (count >= 10) {
            System.out.println("手气一般！再来两把！");
        }
        else   {
            System.out.println("手气不行！回家吧！");
        }

        //圆性质的小项目
        System.out.println("     圆性质的小项目");
        double r = 4 * Math.random();
        double Area = Math.PI * r * r;
        double Circle = Math.PI * 2 * r;
        System.out.println("半径: " + r);
        System.out.println("周长: " + Circle);
        System.out.println("面积: " + Area);
        if (Area >= Circle) {
            System.out.println("面积的数值大于等于周长！");
        }else  {
            System.out.println("面积的数值小于周长!");
        }
        /*数值比大小*/
        int a = 3, b = 4;
        int c = a < b ? b : a;
        System.out.println("a、b最大值为(c):" + c);
        if (a < b) {
            c = b;
        }else {
            c = a;
        }
        System.out.println(c);

        //随机年龄项目
        int Age = (int) (Math.random()*120);
        System.out.println("您挑选的年龄是:" + Age);

        if (Age > 110) {
            System.out.println("申请国家记录吧!太牛啦!");
        } else if (Age<15) {
            System.out.println("儿童,好好玩!");
        } else if (Age >= 15 && Age <= 24) {
            System.out.println("青年,好好学!还来得及,不然会被社会毒打!");
        } else if (Age >= 25 && Age <= 44) {
            System.out.println("中年,好好工作!被社会毒打了吗?");
        }else if (Age >= 45 && Age <= 64) {
            System.out.println("中老年,不服老,继续干,加油!");
        }else if (Age >= 65 && Age <= 84) {
            System.out.println("老年,好好休息！跳跳广场舞!");
        }else if (Age >= 85 && Age <= 99) {
            System.out.println("百岁老人,好好休息!看看广场舞!");
        }else {
            System.out.println("老寿星,不容易!多看看孩子!");
        }
        /*switch*/
        //switch不能对应long类型
        //随机大学年级小项目
        int grade = (int) (Math.random()*4)+1;

        switch (grade) {
            case 1:
                System.out.println("大一!不要迷茫，好好学习，差距是大一开始的!");
                break;
            case 2:
                System.out.println("大二!不要玩游戏了!好好学习吧!");
                break;
            case 3:
                System.out.println("大三了!真快啊!继续努力!");
                break;
            default:
                System.out.println("大四了!时间过得真快!马上要工作了!");
                break;
        }

        if (grade == 1) {
            System.out.println("大一!不要迷茫，好好学习，差距是大一开始的!");
        } else if (grade == 2) {
            System.out.println("大二!不要玩游戏了!好好学习吧!");
        }else if (grade == 3) {
            System.out.println("大三了!真快啊!继续努力!");
        }else if (grade == 4) {
            System.out.println("大四了!时间过得真快!马上要工作了!");
        }
        //  随机月份季节小项目
        int month = (int) (Math.random()*12)+1;
        System.out.println("现在的季节是:" + month + "月");
        if (month < 4) {
            System.out.println(month + "月,春季");
        }else if (month < 7) {
            System.out.println(month + "月,夏季");
        }else if (month < 10) {
            System.out.println(month + "月,秋季");
        }else {
            System.out.println(month + "月,冬季");
        }

        switch (month) {
           case 1:
           case 2:
           case 3:
               System.out.println(month + "月,春季");
               break;
           case 4:
           case 5:
           case 6:
               System.out.println(month + "月,夏季");
               break;
           case 7:
           case 8:
           case 9:
               System.out.println(month + "月,秋季");
               break;
            default:
                System.out.println(month + "月,冬季");
                break;
        }

        String computer = "华为";
        switch (computer) {
            case "联想":
                System.out.println("人类没有联想,会怎样?");
                break;
            case "华为":
                System.out.println("遥遥领先,引领全球!");
                break;
            default:
                System.out.println("中国小众电脑");
                break;
        }
        /*while语句*/
        System.out.println("对while的语句测试");
        int a2 = 0;
        while (a2<3){
            System.out.println("I love you baby!");
            a2++;
        }
        //高斯累加项目：1+2+3+4+5+……+n
        Scanner sc = new Scanner(System.in);
       // int i = sc.nextInt();
        int sum = 0;
       /* while (i<100){
            i++;
            sum = sum + i;
            System.out.println("sum的值为:" + sum + ",i的值为:" + i);
        }*/
        /*for循环*/
         sum = 0;
       // int n = sc.nextInt();
       /* for (int j = n; j > 0; j--) {
            sum = sum + j;
            System.out.println("sum值为:" + sum + ",j值为:" + j);
        }

         sum = 0;
        for (int k = 0; k <= n; k++) {
            sum = sum + k;
            System.out.println("sum2的值为:" + sum + ",k的值为:" + k);
        }*/
        /*do-while循环*/
        //可以被while(true)+break取代，很少使用，但因为比较直观教学中常用。
//        i = 0; sum = 0;
//        do{
//            sum = sum + i;
//            i++;
//        }while(i<=100);
//        System.out.println("sum值为:" + sum + ",i值为:" + i);
        /*课堂项目练习,
        * 1、用while循环计算1-100之间所数字和,偶数和,奇数和。
        * 2、用while、for循环语句打印0~130的数字,每行显示5个
        * */
        int i2 = 0;
        int count0 = 0;
        int count2 = 0, count3 = 0;
        int mun = sc.nextInt();
        while (mun > 0){
            i2++;
            count0 = count0 + mun;//总和
            if(mun%2 == 0){
                count2 = count2 + mun;//偶数和
            }else {
                count3 = count3 + mun;//基数和
            }
            mun--;
        }
        System.out.println("i2循环次数为:" + i2);
        System.out.println( "count0总和值为:" + count0);
        System.out.println( "count2偶数和为:" + count2);
        System.out.println( "count3基数和为:" + count3);

        int sum2 = sc.nextInt();//行数
        int mun2 = sc.nextInt();//个数
        int mun3 = 0;
        for (int i = 0; i < mun2; i++) {
            for (int j = 0; j < sum2; j++) {
                System.out.print(mun3 + " ");
                mun3++;


            }
        }

    }
}
