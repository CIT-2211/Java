import java.lang.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
// import static GameUtil.*;
//import java.awt.event.WindowAdapter;

public class MyGameFrame extends Frame {
	Image Background = GameUtil.getImage("images/background.png");
	Image Airplane = GameUtil.getImage("images/me1.png");
	Image Shell = GameUtil.getImage("images/bullet_supply.png");
	
	/*尘封2.0*/
	//创建飞机对象,如何改大小？(原大小：102*126)
	 Plane plane = new Plane(Airplane,375,874,10);
	 // g.drawImage(Airplane,x,y,30,30,null);
	 Shell shell = new Shell(Shell,200,200,4);
	 
	 @Override
	 public void paint (Graphics g) {
		 g.drawImage(Background,0,0,930,1200,null);
		  // g.drawImage(Background,0,0,480,700,null);
		 plane.drawMySelf(g);	 
		 shell.drawMySelf(g);
	 }
	//int x = 210;
	//int y = 635;
	
	//键盘监听的内部类
	class KeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			//System.out.println("按下键： " + e.getKeyCode());
			// super.keyPressed(e);
			plane.addDirection(e);
		}
		@Override
		public void keyReleased(KeyEvent e) {
			//System.out.println("松开键： " + e.getKeyCode());
			// super.keyReleased(e);
			plane.minusDirection(e);
		}
	}
	
	
	
	//初始化窗口
	public void launchFrame() {
		this.setTitle("## Airplane--Wars ##");
		this.setVisible(true);	//窗口默认不可见，需要让它可见
		this.setSize(930,1200);
		this.setLocation(500,0);
		//新增窗口关闭功能
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//启动窗口绘制线程
		new paintThread().start();
		//启动键盘监听
		this.addKeyListener(new KeyMonitor());
	}
	
	//@Override
	// public void paint(Graphics g){
		// //设置画笔颜色
		// g.setColor(Color.blue);
		// //g相当于一支画笔
		// g.drawLine(100,50,400,400);
		// g.drawRect(100,50,400,400);
		// g.drawOval(100,50,400,400);
		// g.drawString("## Airplane--Wars ##",100,100);
	// }
	
	/*暂时封存*/
	 // public void paint(Graphics g) {
		 // System.out.println("窗口绘制……");
		// // g.drawImage(Background,650,300,480,700,null);
		 // g.drawImage(Background,0,0,480,700,null);
		 // g.drawImage(Airplane,x,y,30,30,null);
		  // x--;
		  // y--;
	 // }
	
	//重画线程
	class paintThread extends Thread{
		@Override
		public void run (){
			while (true) {
				repaint();
				try{
				Thread.sleep(30); //40ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args){
		MyGameFrame frame = new MyGameFrame();
		frame.launchFrame();
		System.out.println();
	}
	private Image offScreenImage = null;
	public void update(Graphics g){
		if(offScreenImage == null)
			offScreenImage = this.createImage(1920,1800);//这是游戏
		
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage,0,0,null);
	}
}
