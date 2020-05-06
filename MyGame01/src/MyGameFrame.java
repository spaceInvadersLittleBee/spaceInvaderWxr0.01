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
	//������ͼƬ��ɻ�ͼƬ����Ϊ��Ա����
    Image bgImg = GameUtil.getImage("image/bg.jpg");
    Image planeImg = GameUtil.getImage("image/plane.png");
    Plane plane = new Plane(planeImg,300,300);
	
    //static int count = 0;
    
    //paint�������������ڼ��ڲ�,��ϵͳ�Զ����á�
    @Override
    public void paint(Graphics g) {  
        g.drawImage(bgImg, 0, 0, null);
        plane.drawMySelf(g);    //�����ɻ�����
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
        //����Ϸ���ڴ�ӡ����:
        setTitle("game");
        //����Ĭ�ϲ��ɼ�����Ϊ�ɼ�
        setVisible(true);
        //���ڴ�С����500����500
        setSize(500, 500);
        //�������ϽǶ��������λ��
        setLocation(300, 300);     
        
        //���ӹرմ��ڼ����������û�������Ͻǹر�ͼ�꣬���Թر���Ϸ����     
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new PaintThread().start();
        addKeyListener(new KeyMonitor());//���Ӽ��̵ļ���
    }
	
	//���̼���
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
    
    //˫��������˸
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(500,500);//������Ϸ���ڵĿ�Ⱥ͸߶�
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  

	
    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }
    

 

    
 
}
