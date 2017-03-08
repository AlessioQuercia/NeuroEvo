package newGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ThrowPanel extends JPanel
{
	private JFrame frame;

	public ThrowPanel(JFrame frame) 
	{
		this.frame = frame;
		
		init();
	}
	
	
	private void init() 
	{
		setBorder(BorderFactory.createTitledBorder("Lancio"));
		
		setLayout(new GridBagLayout());
	
	}
	
	public void clearPanel()
	{
		repaint();
	}
	
	public void drawParabola(double a, double v, double x_tgt, double y_tgt)
	{
//		repaint();
        getGraphics().drawLine(MyConstants.BORDER_X, getHeight()-MyConstants.BORDER_Y, getWidth()-MyConstants.BORDER_X, getHeight()-MyConstants.BORDER_Y); // x-axis
        getGraphics().drawLine(MyConstants.BORDER_X, getHeight()-MyConstants.BORDER_Y, MyConstants.BORDER_X, MyConstants.BORDER_Y); // y-axis
        getGraphics().setColor(Color.RED);
//        getGraphics().drawLine((int)x_tgt, getHeight()-MyConstants.BORDER_Y-(int)y_tgt, (int)x_tgt, getHeight()-MyConstants.BORDER_Y-(int)y_tgt);
        getGraphics().fillOval(MyConstants.BORDER_X + (int)x_tgt, getHeight()-MyConstants.BORDER_Y-(int)y_tgt, 3, 3);
        getGraphics().setColor(Color.BLACK);
        
        for(int x=0; x<=getWidth()-MyConstants.BORDER_X; x++)
        {
            double y = Math.tan(a)*x - ((MyConstants.GRAVITY/(2*Math.pow(v, 2)*Math.pow(Math.cos(a), 2)))*Math.pow(x, 2));
            int Y = (int)y;
            if (y < 0 || x > getWidth()-MyConstants.BORDER_X) break;
            getGraphics().drawLine(MyConstants.BORDER_X+x,(getHeight()-MyConstants.BORDER_Y)-Y,MyConstants.BORDER_X+x,(getHeight()-MyConstants.BORDER_Y)-Y);
        }
	}

}
