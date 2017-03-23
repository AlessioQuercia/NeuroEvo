package newGui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Chart extends JPanel
{
	private JFrame frame;
	
//	private JLabel descX;
//	private JLabel descY;
	
	private int x_axis;
	private int y_axis;

	private String descX;
	private String descY;
	
	private double maxX;
	private double maxY;
	
	private int prevX;
	private int prevY;
	private int currX;
	private int currY;
	
	private int hyphens_x;
	private int hyphens_y;
	
	public Chart(JFrame frame) 
	{
		this.frame = frame;
		
		prevX = 0;
		prevY = 0;
		currX = 0;
		currY = 0;
    	
		setMaxX(1000);
		setMaxY(100000);
		
		setHyphens_x(ChartConstants.NUMBERS_HYPHENS_X);
		setHyphens_y(ChartConstants.NUMBERS_HYPHENS_Y);
		
//		descX = new JLabel("Prova");
//		descY = new JLabel("Prova");
		descX = "Generation";
		descY = "Fitness";
		
		init();
	}
	
	public Chart(JFrame frame, int maxX, int maxY, String descX, String descY, int hyphensX, int hyphensY) 
	{
		this.frame = frame;
		
		prevX = 0;
		prevY = 0;
		currX = 0;
		currY = 0;
    	
		setMaxX(maxX);
		setMaxY(maxY);
		
		setHyphens_x(hyphensX);
		setHyphens_y(hyphensY);
		
//		descX = new JLabel("Prova");
//		descY = new JLabel("Prova");
		this.descX = descX;
		this.descY = descY;
		
		init();
	}

	public void init()
	{
		setLayout(new GridBagLayout());
		
		setX_axis(55);
		setY_axis(30);
		
    	GridBagConstraints gc = new GridBagConstraints();
    	
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
////		gc.fill = GridBagConstraints.BOTH;
//		
//		gc.weightx = 0.5;
//		gc.weighty = 0.5;
//		
//		gc.gridx = 0;
//		gc.gridy = 0;
//    	
//    	add(descX, gc);
//    	
//		gc.anchor = GridBagConstraints.LAST_LINE_END;
//    	
//		gc.gridx = 1;
//		gc.gridy = 1;
//    	add(descY, gc);
	}

	public void drawAxis(Graphics2D g2d)
	{
		g2d.drawLine(x_axis + ChartConstants.BORDER_X, getHeight()-ChartConstants.BORDER_Y - y_axis, getWidth()-ChartConstants.BORDER_X, getHeight()-ChartConstants.BORDER_Y - y_axis); // x-axis
        g2d.drawLine(x_axis + ChartConstants.BORDER_X, getHeight()-ChartConstants.BORDER_Y - y_axis, x_axis + ChartConstants.BORDER_X, ChartConstants.BORDER_Y); // y-axis
	}
	
	public void drawNumbers(Graphics2D g2d)
	{
		//ASSE X
		double larghezza = (getWidth()-ChartConstants.BORDER_X) - (x_axis + ChartConstants.BORDER_X);
		double stepX = larghezza/hyphens_x;
		double numeroX = getMaxX()/hyphens_x;
		
		// DISEGNA LUNGO L'ASSE X
		int pos = 0;
		for (double i = x_axis, j = 0; i<larghezza+stepX; i+=stepX, j+=numeroX)
		{
			if (i == x_axis) pos = 3;
			else if (i > larghezza) pos = 12;
			else pos = 9;
			Line2D line = new Line2D.Double(ChartConstants.BORDER_X + i, getHeight() - y_axis - ChartConstants.BORDER_Y,
					ChartConstants.BORDER_X + i, getHeight() - y_axis - ChartConstants.BORDER_Y + ChartConstants.HYPHEN_WIDTH);
			g2d.draw(line);	// DISEGNA I TRATTINI
			g2d.drawString("" + (int)j, ChartConstants.BORDER_X + (int)i - pos, getHeight() - y_axis);	// DISEGNA I NUMERI
		}
		
		//ASSE Y
		double altezza = (getHeight()-ChartConstants.BORDER_Y - y_axis) - (ChartConstants.BORDER_Y);
		double stepY = altezza/hyphens_y;
		double numeroY = getMaxY()/hyphens_y;
		
		// DISEGNA LUNGO L'ASSE Y
		int x = 0;
		for (double i = y_axis, j = 0; i<altezza+stepY; i+=stepY, j+=numeroY)
		{
			if (i == y_axis) x = x_axis;
			else if (i > altezza) x = x_axis - 35;
			else x = x_axis - 30;
			Line2D line = new Line2D.Double(x_axis + ChartConstants.BORDER_X, getHeight() - ChartConstants.BORDER_Y - i,
					 x_axis + ChartConstants.BORDER_X - ChartConstants.HYPHEN_WIDTH, getHeight() - ChartConstants.BORDER_Y - i);
			g2d.draw(line);	// DISEGNA I TRATTINI
			g2d.drawString("" + (int)j, x + 3, getHeight() - ChartConstants.BORDER_Y - (int)i + 5);	// DISEGNA I NUMERI
		}
	}
	
	public void drawDesc(Graphics2D g2d)
	{
        g2d.drawString(descX, getWidth() - 70, getHeight() - y_axis + 15);
        
        AffineTransform orig = g2d.getTransform();
        g2d.rotate(Math.toRadians(270));
        
        g2d.drawString(descY, -70, 15);
        
        g2d.setTransform(orig);
	}
	
	public void drawCurve(Graphics2D g2d, int x, int y)
	{
		prevX = currX;
		prevY = currY;
		currX = x;
		currY = y;
		
//		g2d.fillOval(x_axis + ChartConstants.BORDER_X + currX, getHeight() - y_axis - ChartConstants.BORDER_Y - currY, 3, 3);
//		g2d.drawLine(x_axis + ChartConstants.BORDER_X + prevX, getHeight() - y_axis - ChartConstants.BORDER_Y - prevY
//				, x_axis + ChartConstants.BORDER_X + currX, getHeight() - y_axis - ChartConstants.BORDER_Y - currY);
		Line2D line = new Line2D.Double(x_axis + ChartConstants.BORDER_X + prevX + 1, getHeight() - y_axis - ChartConstants.BORDER_Y - prevY - 1
				, x_axis + ChartConstants.BORDER_X + currX + 1, getHeight() - y_axis - ChartConstants.BORDER_Y - currY - 1);
		Stroke s = g2d.getStroke();
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(line);
		g2d.setStroke(s);
	}
	
	public void addDescription()
	{
		//TODO
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		drawAxis(g2d);
		drawNumbers(g2d);
		drawDesc(g2d);
		
//		for (int i = 0, j = 0; i<400; i++, j+=100)
//		{
//			drawCurve(g2d, (int)proportionX(i), (int)proportionY(maxY));
//		}
	}
	
	public double proportionX(double x)
	{
		int larghezza = (getWidth()-ChartConstants.BORDER_X) - (x_axis + ChartConstants.BORDER_X);
        double X = x*larghezza/maxX;	///PROPORZIONI X
        return X;
	}
	
	public double proportionY(double y)
	{
		int altezza = (getHeight()-ChartConstants.BORDER_Y - y_axis) - (ChartConstants.BORDER_Y);
        double Y = y*altezza/maxY;	///PROPORZIONI Y
        return Y;
	}
	
	public double getMaxX() 
	{
		return maxX;
	}

	public void setMaxX(double maxX) 
	{
		this.maxX = maxX;
	}

	public double getMaxY()
	{
		return maxY;
	}

	public void setMaxY(double maxY) 
	{
		this.maxY = maxY;
	}
	
	public int getX_axis() 
	{
		return x_axis;
	}

	public void setX_axis(int x_axis) 
	{
		this.x_axis = x_axis;
	}

	public int getY_axis() 
	{
		return y_axis;
	}

	public void setY_axis(int y_axis) 
	{
		this.y_axis = y_axis;
	}
	
	public int getHyphens_x() 
	{
		return hyphens_x;
	}

	public void setHyphens_x(int hyphens_x) 
	{
		this.hyphens_x = hyphens_x;
	}

	public int getHyphens_y() 
	{
		return hyphens_y;
	}

	public void setHyphens_y(int hyphens_y)
	{
		this.hyphens_y = hyphens_y;
	}
}
