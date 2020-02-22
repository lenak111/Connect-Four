import java.awt.Graphics;
import javax.swing.JPanel;

public class CRenderer extends JPanel
{
    //private static final long serialVersionUID = 1L;
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Connect4.c4.repaint(g);
    }
}