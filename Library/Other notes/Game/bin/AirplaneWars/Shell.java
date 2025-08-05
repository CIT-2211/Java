import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
//子弹类
public class Shell extends GameObject {
	double degree;
	@Override
	public void drawMySelf(Graphics g){
		//Color c = g.getColor();
		//g.setColor(Color.yellow);
		//g.fillOval((int)x,(int)y,width,height);
		//g.setColor(c);
		
		super.drawMySelf(g);
		//g.drawImage(img,(int)x,(int)y,width,height,null);
		//炮弹沿着任意角度飞行
		x += speed*Math.cos(degree);
		y += speed*Math.signum(degree);
	}
	
	public Shell(){
		degree = Math.random()*Math.PI*2; //计算弧度
		x = 200;
		y = 200;
		width = 10;
		height = 10;
		speed = 5;
	}
	public Shell (Image img, int x, int y, int speed) {
		super(img,x,y,speed);
	}
}
