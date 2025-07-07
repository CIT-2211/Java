package org.example;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Ball_Game extends JFrame{

    Image ball = Toolkit.getDefaultToolkit().getImage("images/other/ball.png");//64*64
    Image table = Toolkit.getDefaultToolkit().getImage("images/billiards/Ball_Table.png");//839*409

    double x = 200;
    double y = 200;

    double degree = Math.PI/3;//控制小球移动方向,弧度. Π*180°  60°

    //绘制窗口
    public void paint(Graphics g){
        int centerX = (1920 - 839) / 2;  // (1920 - 839) / 2 = 540.5 → 540
        int centerY = (1080 - 409) / 2;   // (1080 - 409) / 2 = 335.5 → 335
        System.out.println("沸羊羊冲锋！");
        g.drawImage(table, 0, 35, null);//对于窗口的坐标
        g.drawImage(ball, (int)x ,(int)y, null);

        x = x + 10*Math.cos(degree);
        y = y + 10*Math.sin(degree);

        /*碰到左右的边界*/
        if (x>839-38 || x<0+25){
            degree = Math.PI - degree;
        }
        /*碰到上下的边界*/
        if (y>409-27 || y<0+62){
            degree = -degree;
        }

    }
    /*创建窗口*/
    void windos(){
        int scaledWidth = (1920 - 839) / 2;  // (1920 - 839) / 2 = 540.5 → 540
        int scaledHeight = (1080 - 409) / 2;   // (1080 - 409) / 2 = 335.5 → 335

        setSize(850,450);//窗口大小
        setLocation(
                (1600 - scaledWidth) / 2,  // 296
                    (900 - scaledHeight) / 2   // 204
        );//窗口位置
        setMinimumSize(new Dimension(839, 409));//控制最小的窗口
        setVisible(true);

        //实现动画,每秒绘制窗口25次
        int mun = 0;
        while(true){
            repaint();
            try {
                Thread.sleep(40);//1s == 10000ms;大约一秒绘制1000/40=25次
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mun++;
            if (mun == 500)
                break;//时停
        }

    }

    public static void main(String[] args) {
        System.out.println("Ball_Game正在启动中……正在请求沸羊羊……请稍后……");

        Ball_Game game = new Ball_Game();
        game.windos();
        System.out.println("Ball_Game已经超功启动！沸羊羊已经出发！");
    }
}
