import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class MyGameFrame extends Frame{
	//将背景图片与飞机图片定义为成员变量
    Image bgImg = GameUtil.getImage("image/bg.jpg");
    Image planeImg = GameUtil.getImage("image/plane.png");
    Plane plane = new Plane(planeImg,300,300);
	
    //static int count = 0;
    
    //paint：画出整个窗口及内部,被系统自动调用。
    @Override
    public void paint(Graphics g) {  
        g.drawImage(bgImg, 0, 0, null);
        plane.drawMySelf(g);    //画出飞机本身
    }
    
    class PaintThread extends Thread {
        public void run(){
            while(true){
                repaint();
                try {
                    Thread.sleep(40); //1s = 1000ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }   
            }
        }
    } 
    
	public void launchFrame(){
        //在游戏窗口打印标题:
        setTitle("game");
        //窗口默认不可见，设为可见
        setVisible(true);
        //窗口大小：宽500，高500
        setSize(500, 500);
        //窗口左上角顶点的坐标位置
        setLocation(300, 300);     
        
        //增加关闭窗口监听，这样用户点击右上角关闭图标，可以关闭游戏程序     
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new PaintThread().start();
        addKeyListener(new KeyMonitor());//增加键盘的监听
    }
	
	//键盘监听
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }
 
        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }
    }
    
    //双缓冲无闪烁
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(500,500);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  

	
    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }
    

 

    
 
}
