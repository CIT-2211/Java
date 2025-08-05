import java.lang.*;

public class TestObject {
    public static void main(String[] args) {
		
		/*	1、创建Car的对象c1
			2、调用changeColor()方法来-----改变汽车颜色
			3、调用showColor()方法来-----打印汽车颜色
			4、分别打印Car的轮胎数量、c1的轮胎数量（静态变量）
		*/
        Car c1 = new Car();
        c1.changeColor("红色");
        c1.showColor();
        System.out.println(Car.tyreNum);
        System.out.println(c1.tyreNum);
		/*	1、创建Car的对象c2
			2、创建Engine的对象e
			3、分别给e的成员变量速度、质量赋值1000、10
			4、分别给c2的成员变量颜色、轮胎数量赋值黑色、10
			5、打印c1的轮胎数量
		*/
		
        Car c2 = new Car();
        Engine e = new Engine();
        e.speed = 1000;
        e.weight = 10;
        c2.engine = e;
        c2.color = "黑色";
        c2.tyreNum = 10;
        System.out.println(c1.tyreNum);
    }
}
 
class Car {
    static int tyreNum = 4;	// 静态变量，轮胎数量
    Engine engine;			// 成员变量，发动机引擎型号(Engine类型的对象）
    String color; 			// 成员变量，汽车颜色
 
    void changeColor(String c) {//定义一个changeColor方法来修改汽车颜色
        color = c;
    }
 
    void showColor() {			//通过定义一个showColor方法来打印汽车颜色
        System.out.println("我的颜色是：" + color);
    }
}
 
 
class Engine {
    int speed;				// 成员变量，速度
    int weight;				// 成员变量，质量
}