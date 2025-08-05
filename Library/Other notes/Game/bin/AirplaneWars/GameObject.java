import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.KeyEvent;
//游戏物体根类
public class GameObject{
	Image img;
	double x;
	double y;
	int speed;
	int width;
	int height;
	
	//我画我自己
	public void drawMySelf(Graphics g){
		g.drawImage(img,(int)x,(int)y,width,height,null);
	}
	//返回该物体对应的矩形
	public Rectangle getRec(){
		return new Rectangle ((int)x,(int)y,width,height);
	}
	
	//重载构造器
	public GameObject(){}
	public GameObject(Image img,double x,double y,int speed,int width,int height){
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}
	public GameObject(Image img, double x, double y, int speed){
		this(img,x,y);
		this.speed = speed;
	}
	
	public GameObject(Image img, double x, double y){
		this(img);
		this.x = x;
		this.y = y;
	}
	
	public GameObject(Image img){
		this.img = img;
		if(this.img != null){
			this.width = img.getWidth(null);
			this.height = img.getHeight(null);
		}
	}
	
	
}