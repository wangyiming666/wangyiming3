package cn.sxt.game;
import java.awt.*;
import java.awt.event.KeyEvent;

public class plane extends GameObject{
    int speed;
    boolean left,up,right,down;
    boolean live;
    public void drawSelf(Graphics g){
        if (live)
        {
            g.drawImage(img,(int)x,(int)y,20,20,null);
        if (left)
        {
            x-=speed;
        }
        if (right)
        {
            x+=speed;
        }
        if (up){
            y-=speed;
        }
        if (down)
        {
            y+=speed;
        }
        }
    }
    public plane(Image img,double x,double y){
        this.img=img;
        this.x=x;
        this.y=y;
        this.speed=2;
        this.height=20;
        this.width=20;
        this.live=true;
    }
    //按下某个键增加相应的方向；
    public void addDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                up=true;
                break;
            case KeyEvent.VK_DOWN:
                down=true;
                break;
            case KeyEvent.VK_LEFT:
                left=true;
                break;
            case KeyEvent.VK_RIGHT:
                right=true;
                break;
                default:
                    break;

        }

    }
    public void minusDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                up=false;
                break;
            case KeyEvent.VK_DOWN:
                down=false;
                break;
            case KeyEvent.VK_LEFT:
                left=false;
                break;
            case KeyEvent.VK_RIGHT:
                right=false;
                break;
            default:
                break;

        }

    }
    public Rectangle getRect(){
        return new Rectangle((int)x,(int)y,width,height);
    }
}
