import java.lang.*;
import java.text.DecimalFormat;

public class App_Circle {
	public static void main (String[] args) {
		DecimalFormat df = new DecimalFormat("0.000");
		
		Circle_class c1 = new Circle_class(5.0);
		double area = c1.Area();
		double lang = c1.lang();
		
		
		
		System.out.println("圆半径为： " + c1.getRadius());
		System.out.println("圆面积为： " + df.format(area));
		System.out.println("圆周长为： " + df.format(lang));
		
	}
}

class Circle_class {
	private double radius;
	private final static double PI = Math.PI;
	
	public Circle_class(double radius){
		if (radius <= 0){
			throw new IllegalArgumentException("圆半径为正整数！,当前值为： " + radius);
		}
		this.radius = radius;
	}
	
	public double getRadius(){		//获取半径值
		return radius;
	}				
	public void setRadius(double NewRadius){
		if (NewRadius <= 0){
			throw new IllegalArgumentException("圆半径为正整数！,当前修改值为： " + NewRadius);
		}
		this.radius = NewRadius;
	}
	
	public double Area(){
		return PI * radius * radius;
	}
	public double lang() {
        return 2 * PI * radius;
    }
}