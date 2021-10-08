package map3;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class wrmPane extends JPanel implements MouseListener {
	String []roleImg={"mouse.gif","resource/Iron Man.jpg","resource/Naruto Uzumaki.jpg","resource/One Punch Man.jpg"};
    private boolean changeable_click;
    private int flag;  
    public Image wall = new ImageIcon("resource/wall.gif").getImage();        
    public Image road = new ImageIcon("resource/road.gif").getImage();       
    public static Image mouse 
    = new ImageIcon("resource/Naruto Uzumaki.jpg").getImage();     
    public Image liangc = new ImageIcon("resource/exit.gif").getImage();   
    public Image monster = new ImageIcon("resource/monster.jpg").getImage();  
    private Image treasure = new ImageIcon("resource/key.png").getImage();  
    wrmPane(int f) {
        flag = f;
        changeable_click = false;   
        addMouseListener(this);
    }

    wrmPane() {
        this(0);
    }

   
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (flag == 0)
            g.drawImage(wall, 0, 0, getWidth(), getHeight(), this);
        else if (flag == 1)
            g.drawImage(road, 0, 0, getWidth(), getHeight(), this);
        else if (flag == 2)
            g.drawImage(mouse, 0, 0, getWidth(), getHeight(), this);
        else if(flag==3)
            g.drawImage(liangc, 0, 0, getWidth(), getHeight(), this);
        else if(flag==4)
        	 g.drawImage(monster, 0, 0, getWidth(), getHeight(), this);
        else
        	 g.drawImage(treasure, 0, 0, getWidth(), getHeight(), this);
    }

    
    public int getFlag() {
        return flag;
    }

   
    public boolean isWall() {
        return flag == 0;
    }

   
    public boolean isChangeable() {
        return changeable_click;
    }

   
    public void setChangeable_click(boolean c) {
        changeable_click = c;
    }

   
    public void change(int f) {
        flag = f;
        repaint();
    }

   
    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        if (!changeable_click) return;
        if (flag == 0) {
            flag = 1;
            repaint();
        } else {
            flag = 0;
            repaint();
        }
    }

}
