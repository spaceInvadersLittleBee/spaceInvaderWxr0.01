import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{
	boolean left, up, right, down;
    boolean live = true;
    
    // 按下上下左右键，则改变方向值。
    // 比如：按下上键，则e.getKeyCode()的值就是VK_UP，那么置：up=true;
    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            left = true;
            break;
        case KeyEvent.VK_UP:
            up = true;
            break;
        case KeyEvent.VK_RIGHT:
            right = true;
            break;
        case KeyEvent.VK_DOWN:
            down = true;
            break;
        default:
            break;
        }
    }
 
    // 松开上下左右键，则改变方向值。
    // 比如：松开上键，则e.getKeyCode()的值就是VK_UP，那么置：up=false;
    public void minusDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            left = false;
            break;
        case KeyEvent.VK_UP:
            up = false;
            break;
        case KeyEvent.VK_RIGHT:
            right = false;
            break;
        case KeyEvent.VK_DOWN:
            down = false;
            break;
        default:
            break;
        }
    }
 
	@Override
    public void drawMySelf(Graphics g) {
        super.drawMySelf(g);
        if (left) {
            x -= speed;
            if(x<0)
            	x=0;
        }
        if (right) {
            x += speed;
            if(x>500)
            	x=462;
        }
        if (up) {
            y -= speed;
            if(y<0)
            	y=33;
        }
        if (down) {
            y += speed;
            if(y>500)
            	y=467;
        }
    }
     
    public Plane(Image img, double x, double y) {
        super(img,x,y);
        this.speed=5;//先这么设
    }
}
