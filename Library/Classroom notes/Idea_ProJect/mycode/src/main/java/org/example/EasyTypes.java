package org.example;

import java.math.BigDecimal;
import java.util.Scanner;

public class EasyTypes {
    int a = 1;//成员
    static int b = 2;//静态
    public static void main(String[] args) {

        /*变量的分类：局部、成员、静态*/
        int age=10;//局部变量
        int b;
        int x = 2, y =3,z=1;
        System.out.println(age);
        b = 5;
        z = 6;
        System.out.println(z+b);
        System.out.println(x+y);

        /*常量+命名*/
        final double c = 3.14;//局部变量转化为常量，常量被初始化，无法再次赋值
        final int MAX_SPEED = 120;//常量命名要用大写字母，单词之间用下划线隔开。
        int r = 2;
        double area = c*r*r;
        double circle = 2*r*c;
        System.out.println("Area is "+area);
        System.out.println("Circle is "+circle);

        /*进制*/
        int a = 100;//默认为十进制
        int a2 = 015;//0开头的为八进制
        int a3 = 0xff;//0x、0X开头为十六进制
        int a4 = 0b10;//0b、0B开头为二进制
        System.out.println(a);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(a4);
        int salary = 30000;//整型默认为int
        long Yearsalary = 3000000000L;//把整型转化为long类型
        byte a5 = -128;
        short a6 = 30000;
        System.out.println("你的年薪为："+ Yearsalary);


        /*浮点型*/
        double d1 = 3.14;
        double d2 = 3.14e2;//科学计数法、3.14*10^2
        System.out.println("科学计数法："+ d2);
        float f1 = 3.14f;//浮点型默认类型为double,改为float类型需要在后面加上f、F
        /*浮点型是不精确的，尽量不要直接比较*/
        float f2 = 0.1f;
        double d3 = 1.0/10;
        System.out.println(f2 == d3);
        float f3 = 234324332432L;
        float f4 = f3+1;
        System.out.println(f3 == f4);
        /*解决浮点型不精准的方案*/
        //一、采用整型
        //二、采用BigDecimal类

        /*字符型*/
        /*字符发展史*/
        //最初字符集 ASCLL字符集126个、0--48,A--65,a--96
        //西欧字符集 ISO8859-1(latin1)
        //台湾字符集BIG5、 国标GB2312--->GB18030  65536
        //Unicode字符集通用字符集、UTF-8  字母占一个字节、汉字占三个字节、其他文字占两个字节
        /*unicode a--61 A--41*/
        //基本数据类型---字符
        char c1 = 'a';
        char c2 = '中';
        char c3 = '\u0041';
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        //字符串、不是数据类型是类
        String str = "我喜欢你！";
        System.out.println(str);
        //转义字符
        char c4 = '\n';
        System.out.println("a \n b \n c \n d \n e \n f \n g");
        /*基础转义字符
        * \n    换行
        * \b    退格符,光标向左移动一格
        * \r    回车
        * \t    制表符
        * \\    反义斜杠
        * \'    单引号
        * \"    双引号
        * */

        /*布尔型,占一个字节或四个字节、 不能用c语言里面的0、1来表示*/
        boolean b1 = true;
        boolean b2 = false;
        if (b1 == true) {
            System.out.println("b1为true!");
        }else {
            System.out.println("b1是false!");
        }

        /*算数运算符*/
        int a7 = 3;
        int a8 = 7;
        long a9 = a7+a8;
        double d10 = 3+3.14;
        double d4 = 32.0/3;
        System.out.println(d4);//两个整数相除，直接保留整数部分
        int e = 10%3;
        int e2 = -10%3;
        int e3 = 10%-3;
        System.out.println(e);
        System.out.println(e2);
        System.out.println(e3);//取余操作，结果符号和左边操作数相同
        //自增和自减
        int g = 10;
        g++;//相当于：g=g+1
        g--;//相当于:g=g-1
        g = 10;
        int h = g++;
        System.out.println("测试g值1："+g);
        int h2 = g--;
        System.out.println("测试g值2："+g);
        int h3 = ++g;
        int h4 = --g;
        System.out.println("测试g值为："+g);
        System.out.println(h);//先赋值，再自增
        System.out.println(h2);//同上
        System.out.println(h3);//先自增，再赋值
        System.out.println(h4);//同上

        /*扩展运算符*/
        int a10 = 3;
        int a11 = 4;
        a10 += a11;
        System.out.println(a10);
        a11 -= a10;
        System.out.println(a11);

        a10 = 3; a11 = 4;
        a10*=a11+1;
        System.out.println(a10);

        /*关系运算符*/
        //结果为true、false
        //注意：==、!=(基本、引用（地址）类型)数据类型都可以使用
        //>、<、>=、<=仅针对数值类型如int、double、char
        int a12 = 7;
        int a13 = 8;
        boolean b4 = a12 > a13;
        System.out.println(b4);
        char d = 'a';
        //char值为0~65536,可以通过(int)来进行强制类型转换
        System.out.println((int)d);
        boolean b5 = d>100;
        System.out.println(b5);

        /*逻辑运算符*/
        /*
        * &         只要一个为false,则false,会从头运算到位
        * &&        同时,但一旦检测到为false停止运算直接输出false
        * |         只要一个为true,则true
        * ||        同上
        * !         取反
        * ^         相同为false,不同为true
        * */
        boolean b6 = true;
        boolean b7 = false;
        System.out.println("开始测试逻辑运算符\n");
        System.out.println(b6&b7);//F
        System.out.println(b6|b7);//T
        System.out.println(!b6);//F
        System.out.println(b6^b7);//T
        boolean b8 = 1>2 && (4<3/0);//不报错,F
        //boolean b8 = 1>2 & (4<3/0);//报错,0一般不能做除数
        System.out.println(b8);

        /*位运算--二进制的位运算*/
        /*
        * ~        取反
        * &        按位与
        * |        按位或
        * ^        按位亦或
        * <<       左移运算符,左移1位相当于乘2
        * >>       右移运算符,右移一位相当于除2区商
        * */
        //1、0运算,1相当于true,0相当于false
        System.out.println("测试位运算\r");
        int e1 = 7;//0111
        int e11 = 8;//1000
        System.out.println(e1&e11);//0000
        System.out.println(e1|e11);//1111
        System.out.println(e1^e11);//1111
        System.out.println(~e11);//0001

        //移位,运算速度最快的操作
        int c5 = 5<<1;//相当于5*2
        System.out.println(c5);
        int c6 = 5>>1;//相当于5/2
        System.out.println(c6);

        /*字符串链接符*/
        //+两侧有字符串，自动转化位链接符
        System.out.println("字符串连接符:\n");
        String str1 = "3";
        int b9 = 4;
        System.out.println(str1+" "+b9);
        //条件是String或者"xxx",不是char.char的话仍然是加法
        char c7 = 'h';
        char c8 = 'i';
        System.out.println(c7+c8);
        System.out.println(""+c7+c8);

        /*条件运算符*/
        //判断，true左边,false右边
        System.out.println("条件运算符\n");
        int score1 = 50;
        int score2 = 60;
        int score3 = 80;
        int score4 = 100;
        String str2 = score1<60?"不及格":"及格";
        System.out.println(str2);
        System.out.println(score2<60?"不及格":"及格");
        if (score3 <60) {
            System.out.println("不及格");
        }else {
            System.out.println("及格");
        }
        int x1 = -100;
        int flag = x1 > 0 ? 1 : (x1 == 0? 0:-1);
        System.out.println(flag);

        /*优先级问题*/
        System.out.println("优先级问题\n");
        boolean s1 = true, s2 = true, s3 = false;
        System.out.println(s1||s3&&s2);
        boolean s4 = (3>1 || 4>3) && 3>5;
        System.out.println(s4);

        /*数据类型转化*/
        //小容量--->大容量  不会存在精度丢失
        //大容量--->小容量 （存在精度缺失）
        //整数可以转小数,但精度会有所丢失（整数超过53位转double时会发生精度丢失,float--24位）
        //丢失的原因：计算机二进制算法导致的
        int a123 = 2345;//小容量自动转化成大容量的类型
        long b12 = a;
//        int c12 = b12;  大容量不能自动转化为小容量
        double d12 = b12;
        float f12 = b12;
        //整型常量int可以自动转化为：byte/short/char,不超过对应范围

        /*强制类型转换*/
        //强制类型转换可能存在损失精度的问题
        System.out.println("强制类型转化：\n");
        double a1 = 3.94152;
        int b3 = (int)a;//3,小数部分会被直接省略
        System.out.println(b);
        int c9 = 97;
        char d5 = (char)c9;
        System.out.println(d);
        //当强制转换时，超过了表数范围，会转化为一个完全不同的值
        byte e4 = (byte)300;
        System.out.println(e4);

        /*基本数据类型转化常见的问题*/
        System.out.println("基本数据类型转化常见的问题");
        int money = 1000000000;//10亿
        int years = 20;
        int total = money*years;//常见错误：溢出
        System.out.println("total = "+total);
        long total1 = money*years;//默认值为int,转化值仍然是int
        System.out.println("total1 = "+total1);
        long total2 = money*((long)years);
        long total3 = 1l*money*years;
        System.out.println("total2 = "+total2);
        System.out.println("total3 = "+total3);

        /*键盘输入*/
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入用户名子：");
        String Uname1 = sc.next();//只读一个单词

        System.out.print("请输入用户年龄：");
        byte  age1 = sc.nextByte();//读取一段话

        System.out.print("请输入用户月薪（日元）: ");
        double salary1 = sc.nextDouble();

        System.out.println("\n\n\n\n");
        System.out.println("********简易年薪计算器********");
        System.out.println("用户名为:"+Uname1);
        System.out.println("用户年龄为:"+age1);
        System.out.println("用户年薪为:"+salary1*12);

    }
}
