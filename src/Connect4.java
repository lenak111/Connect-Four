import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.Font;



import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Connect4 implements ActionListener, KeyListener
{
    public static Connect4 c4;

    public static final int WIDTH = 660, HEIGHT = 600, SCALE = 60;

    public CRenderer renderer;

    public Rectangle piece;

    public int x, y, pr, pc, p1, p2, velX;

    public boolean gameOver, player1, player2, draw;

    public int[][] course;

    public Connect4()
    {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(20, this);

        renderer = new CRenderer();

        jframe.add(renderer);
        jframe.setTitle("Connect Four");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH + 90, HEIGHT + 10);
        jframe.addKeyListener(this);
        //jframe.setResizable(false);
        jframe.setVisible(true);

        pr = 1;
        pc = 4;
        x = 20 * (pc + 1) + pc * SCALE;
        y = 20;

        course = new int[7][9];
        course();
        timer.start();
    }

    public void course()
    {
        course[0][0] = 1;
        course[0][course[0].length - 1] = 1;
    }

    public void drop1()
    {
        int place = -1;
        for(int r = 1; r < course.length; r++)
        {
            if(course[r][pc] == 0)
            {
                place = r;
            }
        }
        if(place > 0)
        {
            course[place][pc] = 2;
        }
        else
        {
            p1--;
        }
    }

    public void drop2()
    {
        int place = -1;
        for(int r = 1; r < course.length; r++)
        {
            if(course[r][pc] == 0)
            {
                place = r;
            }
        }
        if(place > 0)
        {
            course[place][pc] = 3;
        }
        else
        {
            p2--;
        }
    }

    public void ghost(Graphics g)
    {
        g.setColor(Color.red);
        g.fillOval(x, y, SCALE, SCALE);
    }

    public void ghost2(Graphics g)
    {
        g.setColor(Color.yellow);
        g.fillOval(x, y, SCALE, SCALE);
    }

    public void checkHorizontal()
    {
        for(int r = 1; r < course.length; r++)
        {
            for(int c = 1; c < course[0].length - 4; c++)
            {
                if(course[r][c] == 2 && course[r][c + 1] == 2 && 
                course[r][c + 2] == 2 && course[r][c + 3] == 2)
                {
                    player1 = true;
                }
                if(course[r][c] == 3 && course[r][c + 1] == 3 && 
                course[r][c + 2] == 3 && course[r][c + 3] == 3)
                {
                    player2 = true;
                }
            }
        }
    }

    public void checkVertical()
    {
        for(int c = 1; c < course[0].length; c++)
        {
            for(int r = course.length - 1; r > 3; r--)
            {
                if(course[r][c] == 2 && course[r - 1][c] == 2 && 
                course[r - 2][c] == 2 && course[r - 3][c] == 2)
                {
                    player1 = true;
                }
                if(course[r][c] == 3 && course[r - 1][c] == 3 && 
                course[r - 2][c] == 3 && course[r - 3][c] == 3)
                {
                    player2 = true;
                }
            }
        }
    }

    public void checkDiagonalLL()
    {
        for(int r = course.length - 1; r > 3; r--)
        {
            for(int c = 1; c < course[0].length - 4; c++)
            {
                if(course[r][c] == 2 && course[r - 1][c + 1] == 2 &&
                course[r - 2][c + 2] == 2 && course[r - 3][c + 3] == 2)
                {
                    player1 = true;
                }
                if(course[r][c] == 3 && course[r - 1][c + 1] == 3 &&
                course[r - 2][c + 2] == 3 && course[r - 3][c + 3] == 3)
                {
                    player2 = true;
                }
            }
        }
    }

    public void checkDiagonalLR()
    {
        for(int r = course.length - 1; r > 3; r--)
        {
            for(int c = course[0].length - 2; c > 3; c--)
            {
                if(course[r][c] == 2 && course[r - 1][c - 1] == 2 &&
                course[r - 2][c - 2] == 2 && course[r - 3][c - 3] == 2)
                {
                    player1 = true;
                }
                if(course[r][c] == 3 && course[r - 1][c - 1] == 3 &&
                course[r - 2][c - 2] == 3 && course[r - 3][c - 3] == 3)
                {
                    player2 = true;
                }
            }
        }
    }

    public void checkDraw()
    {
        int count = 0;
        for(int r = 1; r < course.length; r++)
        {
            for(int c = 1; c < course[0].length - 1; c++)
            {
                if(course[r][c] != 0)
                {
                    count++;
                }
            }
        }
        if(count == 42)
        {
            draw = true;
        }
    }

    public void repaint(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH + 80, HEIGHT);

        g.setColor(Color.blue);
        g.fillRect(SCALE + 20, SCALE + 20, WIDTH - 80, HEIGHT - 80);

        for(int r = 0; r < course.length; r++)
        {
            for(int c = 1; c < course[0].length; c++)
            {
                g.setColor(Color.white);
                g.fillOval(20 * (c + 1) + c * SCALE, 20 * (r + 1) + r * SCALE, SCALE, SCALE); 
            }
        }

        if(p1 == p2)
        {
            ghost(g);
        }

        if(p1 != p2)
        {
            ghost2(g);
        }

        for(int r = 1; r < course.length; r++)
        {
            for(int c = 0; c < course[0].length; c++)
            {
                if(course[r][c] == 2)
                {
                    g.setColor(Color.red);
                    g.fillOval(20 * (c + 1) + c * SCALE, 20 * (r + 1) + r * SCALE, SCALE, SCALE); 
                }
                if(course[r][c] == 3)
                {
                    g.setColor(Color.yellow);
                    g.fillOval(20 * (c + 1) + c * SCALE, 20 * (r + 1) + r * SCALE, SCALE, SCALE); 
                }
            }
        }
        if(player1)
        {
            g.setColor(Color.cyan);
            g.fillRect(WIDTH / 2 - 80, HEIGHT / 2 - 60, 230, 100);

            g.setColor(Color.black);
            g.setFont(new Font("Courier New", 1, 35));
            g.drawString("RED WINS!!", WIDTH / 2 - 70, HEIGHT / 2);
        }
        else if(player2)
        {
            g.setColor(Color.cyan);
            g.fillRect(WIDTH / 2 - 80, HEIGHT / 2 - 60, 300, 100);

            g.setColor(Color.black);
            g.setFont(new Font("Courier New", 1, 35));
            g.drawString("YELLOW WINS!!", WIDTH / 2 - 70, HEIGHT / 2);
        }
        if(draw)
        {
            g.setColor(Color.cyan);
            g.fillRect(WIDTH / 2 - 80, HEIGHT / 2 - 60, 200, 100);

            g.setColor(Color.black);
            g.setFont(new Font("Courier New", 1, 35));
            g.drawString("DRAW", WIDTH / 2 - 30, HEIGHT / 2);
        }
    }

    public void actionPerformed(ActionEvent e)
    { 
        x = 20 * (pc + 1) + pc * SCALE;
        checkHorizontal();
        checkVertical();
        checkDiagonalLL();
        checkDiagonalLR();
        checkDraw();
        renderer.repaint();
    }

    public void keyPressed(KeyEvent e)
    {
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_LEFT)
        {
            if(course[0][pc - 1] < 1)
            {
                pc--;
                velX = -1;
            }
        }
        if(c == KeyEvent.VK_RIGHT)
        {
            if(course[0][pc + 1] < 1)
            {
                pc++;
                velX = 1;    
            }
        }
        if(c == KeyEvent.VK_SPACE)
        {
            if(p1 == p2)
            {
                drop1();
                p1++;
            }
            else if(p1 != p2)
            {
                drop2();
                p2++;
            }
        }
    }

    public void keyTyped(KeyEvent e){}

    public void keyReleased(KeyEvent e)
    {
        velX = 0;
    }

    public static void main(String[] args)
    {
        c4 = new Connect4();
    }
}
