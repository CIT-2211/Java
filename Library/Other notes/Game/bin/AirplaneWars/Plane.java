import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;

//飞机类
public class Plane extends GameObject{
	public static final int FRAME_WIDTH = 480;
	public static final int FRAME_HIDTH = 700;
	boolean left;
	boolean right;
	boolean up;
	boolean	down;
	boolean life = true;
	@Override
	public void drawMySelf(Graphics g){
		super.drawMySelf(g);
		//x += speed;
		// 添加边界检查，防止飞机飞出窗口
		/*静止版*/
        // if (x < 0) x = 0;
        // if (x > GameUtil.FRAME_WIDTH - width) x = GameUtil.FRAME_WIDTH - width;
	    // }
		/*弹力版*/
		// if(left){
			// x-=speed;
		// }else{
			// x+=speed;
		// }
		// if(x > 434){
			// left = true;
		// }
		// if(x <= 0){
			// left = false;
		// }	
		
		if(left){
			x-=speed;
		}
		if(right){
			x+=speed;
		}
		if(up){
			y-=speed;
		}
		if(down){
			y+=speed;
		}
}	

	public void addDirection(KeyEvent e){
		switch (e.getKeyCode()){
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
			left = true;
			break;
			
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
			right = true;
			break;
			
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
			up = true;
			break;
			
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
			down = true;
			break;
		}
	}
	
		public void minusDirection(KeyEvent e){
		switch (e.getKeyCode()){
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
			left = false;
			break;
			
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
			right = false;
			break;
			
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
			up = false;
			break;
			
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
			down = false;
			break;
		}
	}


	public Plane (Image img, int x, int y, int speed) {
		super(img,x,y,speed);
	}
}