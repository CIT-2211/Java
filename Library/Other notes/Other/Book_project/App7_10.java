import java.text.DecimalFormat;
import java.lang.*;

interface IShape {
	public static final double PI = Math.PI;
	public abstract double getArea();
	public abstract double gatLength();
}

class Circle implements IShape {
	private double radius;
	public Circle (double r) {
		radius = r;
	}
	
	@Override
	public double getArea() {
		return PI*radius*radius;
	}
	@Override
	public double gatLength() {
		return 2*PI*radius;
	}
}

class Rectangle implements IShape {
	private double width;
	private double height;
	public Rectangle (double width, double height) {
		this.width = width;
		this.height = height;
	}
	@Override
	public double getArea() {
		return width*height;
	}
	@Override
	public double gatLength() {
		return 2*(width+height);
	}
}

public class App7_10 {
	public static void main (String[] args){
		DecimalFormat df = new DecimalFormat("0.00");
		IShape circle = new Circle(5.0);
		System.out.println("圆面积： "+df.format(circle.getArea()));
		System.out.println("圆周长： "+df.format(circle.gatLength()));
		Rectangle rect = new Rectangle(6.5,10.8);
		System.out.println("矩形面积： "+df.format(rect.getArea()));
		System.out.println("矩形周长： "+df.format(rect.gatLength()));
	}
}