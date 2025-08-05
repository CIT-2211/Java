import java.lang.*;
class Cylinder {
	double radius;
	int height;
	double pi = Math.PI;
	void area(){
		System.out.println("圆柱体底面积：" + pi*radius*radius);
	}
	void volume(){
		System.out.println("圆柱体的体积："+(pi*radius*radius)*height);
	}
}
