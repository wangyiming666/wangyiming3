package cn.sxt.game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class MyGame01 extends Frame {
    Image beijing=GameUtil.getImage("images/biejing.jpg");
    Image Feiji=GameUtil.getImage("images/feiji.jpg");
//    Image sun=GameUtil.getImage("images/sungou.jpg");
    plane plane=new plane(Feiji,600,600);
    Shell[] shells=new Shell[50];
    Explode bao;
    Date startTime=new Date();
    Date endTime;
    int period;
 //   plane plane1=new plane(spider,300,200);
  //  plane plane2=new plane(spider,400,200);
   @Override
    public void paint(Graphics g) {//自动调用，g相当于一只画笔
     g.clearRect(0,0,this.getWidth(),this.getHeight());
       g.drawImage(beijing,0,0,Constant.GAME_WIDTH,Constant.GAME_WIDTH,null);
   plane.drawSelf(g);
   for (int i=0;i<shells.length;i++)
   {
       shells[i].draw(g);
     boolean peng= shells[i].getRect().intersects(plane.getRect());//判断
     if (peng)
     {
         plane.live=false;
         if (bao==null)
         { bao=new Explode(plane.x,plane.y);
         endTime=new Date();
         period=(int)((endTime.getTime()-startTime.getTime())/1000);
         }

         bao.draw(g);
     }
     if (!plane.live){
         g.setColor(Color.red);
         Font f=new Font("宋体",Font.BOLD,50);
         g.setFont(f);
         g.drawString("时间："+period+"秒",(int)plane.x,(int)plane.y);
     }
   }


    }

    //这个线程的作用是反复重画窗口
class  PaintThread extends Thread {
    @Override
    public void run() {
        while(true)
        {
            repaint();//重画窗口
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
//定义键盘监听的内部类
class KeyMonitor extends KeyAdapter{
    @Override
    public void keyPressed(KeyEvent e) {
        plane.addDirection(e);

    }

   @Override
    public void keyReleased(KeyEvent e) {
        plane.minusDirection(e);
    }
}
    /**
     * 初始化窗口
     */

    public void launchFrame(){
        setTitle("王天才");
        setVisible(true);//窗口默认不可见，这个方法可使窗口可见。
        setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        setLocation(300,300);
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new PaintThread().start();//启动重画窗口的线程
        addKeyListener(new KeyMonitor());//给窗口监听增加键盘的输入
        for(int i=0;i<shells.length;i++)
            shells[i]=new Shell();
    }

    public static void main(String[] args) {
        MyGame01 f=new MyGame01();
        f.launchFrame();
    }
    private Image offscreenImage=null;
    public  void  update(Graphics g){
        if (offscreenImage==null)
            offscreenImage=this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        Graphics gOff=offscreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offscreenImage,0,0,null);
    }

}
