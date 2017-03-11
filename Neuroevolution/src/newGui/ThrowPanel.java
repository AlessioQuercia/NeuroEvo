package newGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

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
	
	private Graphics2D g;
	
	private Line2D line;
	
//	private Peso peso = new Peso(60, 60, 0);
	
	private Ellipse2D target;
	private Ellipse2D peso;
	
	boolean draw;

	public ThrowPanel(JFrame frame) 
	{
		this.frame = frame;
		
		g = (Graphics2D) getGraphics();
		
		init();
	}
	
	
	private void init() 
	{
		setBorder(BorderFactory.createTitledBorder("Lancio"));
		
		setLayout(new GridBagLayout());	
		
		target = new Ellipse2D.Double(0, 0, 5, 5);
		
		peso = new Ellipse2D.Double(0, 0, 3, 3);
		
		draw = false;
	}
	
	public void clearPanel()
	{
        repaint();
	}
	
	public void drawAxis()
	{
		g = (Graphics2D) getGraphics();
        g.drawLine(MyConstants.BORDER_X, getHeight()-MyConstants.BORDER_Y, getWidth()-MyConstants.BORDER_X, getHeight()-MyConstants.BORDER_Y); // x-axis
        g.drawLine(MyConstants.BORDER_X, getHeight()-MyConstants.BORDER_Y, MyConstants.BORDER_X, MyConstants.BORDER_Y); // y-axis
	}
	
	public void drawTarget(double x_tgt, double y_tgt)
	{
		double X = proportionX(x_tgt);
		double Y = proportionY(y_tgt);
        g.setColor(Color.RED);
        Ellipse2D circle = new Ellipse2D.Double(MyConstants.BORDER_X + (int)X, (getHeight()-MyConstants.BORDER_Y) - (int)Y, 5, 5);
        g.fill(circle);
//        g.fillOval(MyConstants.BORDER_X + (int)X, getHeight()-MyConstants.BORDER_Y-(int)Y, 3, 3);
	}
	
	public void drawTarget(Graphics2D g2d, double x_tgt, double y_tgt)
	{
		double X = proportionX(x_tgt);
		double Y = proportionY(y_tgt);
        g2d.setColor(Color.RED);
        Ellipse2D circle = new Ellipse2D.Double(MyConstants.BORDER_X + (int)X, (getHeight()-MyConstants.BORDER_Y) - (int)Y, 5, 5);
        g2d.fill(circle);
//        g.fillOval(MyConstants.BORDER_X + (int)X, getHeight()-MyConstants.BORDER_Y-(int)Y, 3, 3);
	}
	
	public void clearTarget(double x_tgt, double y_tgt)
	{
		double X = proportionX(x_tgt);
		double Y = proportionY(y_tgt);
		
		g.clearRect(MyConstants.BORDER_X + (int)X, getHeight()-MyConstants.BORDER_Y-(int)Y, 5, 5);
	}
	
	public void drawParabola(double a, double v)
	{
        g = (Graphics2D) getGraphics();
//        g.drawLine(MyConstants.BORDER_X, getHeight()-MyConstants.BORDER_Y, getWidth()-MyConstants.BORDER_X, getHeight()-MyConstants.BORDER_Y); // x-axis
//        g.drawLine(MyConstants.BORDER_X, getHeight()-MyConstants.BORDER_Y, MyConstants.BORDER_X, MyConstants.BORDER_Y); // y-axis
//        getGraphics().drawLine((int)x_tgt, getHeight()-MyConstants.BORDER_Y-(int)y_tgt, (int)x_tgt, getHeight()-MyConstants.BORDER_Y-(int)y_tgt);
//        g.setColor(Color.RED);
//        g.fillOval(MyConstants.BORDER_X + (int)x_tgt, getHeight()-MyConstants.BORDER_Y-(int)y_tgt, 3, 3);
        g.setColor(Color.BLACK);
        
        for(int x=0; x<=getWidth()-MyConstants.BORDER_X; x++)
        {
            double y = Math.tan(a)*x - ((MyConstants.GRAVITY/(2*Math.pow(v, 2)*Math.pow(Math.cos(a), 2)))*Math.pow(x, 2));
            double Y = proportionY(y);	///PROPORZIONI Y
            double X = proportionX(x);		///PROPORZIONI X
            if (y < 0 || x > getWidth()-MyConstants.BORDER_X) break;
            line = new Line2D.Double(MyConstants.BORDER_X+X,(getHeight()-MyConstants.BORDER_Y)-Y,MyConstants.BORDER_X+X,(getHeight()-MyConstants.BORDER_Y)-Y);
            g.draw(line);
//            g.drawLine(MyConstants.BORDER_X+x,(getHeight()-MyConstants.BORDER_Y)-Y,MyConstants.BORDER_X+x,(getHeight()-MyConstants.BORDER_Y)-Y);
        }
	}
	
	public double proportionX(double x)
	{
        double X = x*(getWidth()-MyConstants.BORDER_X)/100;	///PROPORZIONI X
        return X;
	}
	
	public double proportionY(double y)
	{
        double Y = y*(getHeight()-MyConstants.BORDER_Y)/100;	///PROPORZIONI Y
        return Y;
	}
	
	public Graphics2D getGraphics2D()
	{
		return g;
	}
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		if (draw)
		{
			drawAxis(g2d);
			
			drawTarget(g2d);
			
			drawPeso(g2d);
		}
	}

	public void drawAxis(Graphics2D g2d)
	{
        g2d.drawLine(MyConstants.BORDER_X, getHeight()-MyConstants.BORDER_Y, getWidth()-MyConstants.BORDER_X, getHeight()-MyConstants.BORDER_Y); // x-axis
        g2d.drawLine(MyConstants.BORDER_X, getHeight()-MyConstants.BORDER_Y, MyConstants.BORDER_X, MyConstants.BORDER_Y); // y-axis
	}
	
	private void drawTarget(Graphics2D g2d) 
	{
		g2d.setColor(Color.RED);
		g2d.fill(target);	
	}
	
	private void drawPeso(Graphics2D g2d) 
	{
		g2d.setColor(Color.BLACK);
		g2d.fill(peso);
	}

	public Ellipse2D getPeso()
	{
		return peso;
	}
	
	public Ellipse2D getTarget()
	{
		return target;
	}
	
	public boolean getDraw()
	{
		return draw;
	}
	
	public void setDraw(boolean bool)
	{
		draw = bool;
	}
}
