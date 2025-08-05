import java.lang.*;
class Cylinder {
	private double radius;
	private double height;
	private double pi = Math.PI;
	public void setCylinder(double r, double h){
		if(r>0 && h>0) {
			radius = r;
			height = h;
		}
		else{
			System.out.println("您的数据有错误！！");
		}
	}
}