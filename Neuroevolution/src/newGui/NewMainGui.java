package newGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.text.Document;

import gui.evo_fit;
import gui.evo_in;
import gui.evo_out;
import jNeatCommon.EnvConstant;
import jNeatCommon.EnvRoutine;
import jNeatCommon.IOseq;
import jneat.Genome;
import jneat.NNode;
import jneat.Neat;
import jneat.Network;
import jneat.Organism;
import jneat.Population;
import jneat.Species;
import log.HistoryLog;
import myGui.myGuiConstants;

public class NewMainGui extends JPanel implements Runnable
{
	private JFrame frame;
	
	private Thread mainThread;
	private boolean isRunning;
	
	private Line2D line;
	private Ellipse2D circle;
	private ArrayList<Line2D> lines = new ArrayList<> ();
	private ArrayList<Ellipse2D> circles = new ArrayList<> ();
	
	private Simulation simulation;
	private Graphs graphs;
	private JTabbedPane tabbedPanel;

	  
	public NewMainGui(JFrame f) 
	{
		frame = f;	        
		
		init();
		
		start();	// LANCIA IL THREAD CHE GESITSCE LA GRAFICA
	}

	public Simulation getSimulationPanel()
	{
		return simulation;
	}
	
	public Graphs getGraphsPanel()
	{
		return graphs;
	}

	public static void main(String[] args) 
	{
		JFrame frame = new MainFrame();
		NewMainGui mainPanel = new NewMainGui(frame); 
		frame.setVisible(true);	
	}

	private void init() 
	{
        // Set layout manager
        
        setLayout(new BorderLayout());
        
        // Create Swing component
        
        simulation = new Simulation(frame);
        
        graphs = new Graphs(frame);
        
		tabbedPanel = new JTabbedPane();
		tabbedPanel.addTab("Simulation", simulation);
		tabbedPanel.addTab("Graphs", graphs);
		tabbedPanel.setSelectedIndex(0);
		
        // Add Swing components to content pane
        
		Container contentPane = frame.getContentPane(); 
		contentPane.setLayout(new BorderLayout());

		tabbedPanel.setMinimumSize(new Dimension(400, 50));

		contentPane.add(tabbedPanel,BorderLayout.CENTER);
        
		// Add behaviour
		//TODO
	}
	
	private void start()
	{
		isRunning = true;
		if(mainThread == null)
		{
			mainThread = new Thread(this, "mainThread");
			mainThread.start();
		}
	}

	@Override
	public void run() 
	{	
		double x = 0;
		double a = 0;
		double v = 0;
		double x_tgt = 0;
		double y_tgt = 0;
		
		double prov = 50;
		Ellipse2D prova = new Ellipse2D.Double(MyConstants.BORDER_X+prov,(simulation.getRightPanel().getHeight()-MyConstants.BORDER_Y)-prov, 5, 5);
//		Peso peso = new Peso(50, 50, 0);
		
		while (isRunning)
		{
			long startTime = System.currentTimeMillis();
			
//			simulation.getRightPanel().repaint();
			
//			simulation.getRightPanel().getTarget().setFrame(100, 100, 3, 3);
//			
//			simulation.getRightPanel().getPeso().setFrame(x, 50, 3, 3);
//			
//			x++;
			
			
//			simulation.getRightPanel().paint(simulation.getRightPanel().getGraphics());
			
			
			
			if (simulation.getStart() && simulation.getLeftPanel().getOptionsPanel().getGenerationList().getSelectedItem() != null)
			{
				simulation.getRightPanel().repaint();
//				simulation.getRightPanel().repaint();
//				simulation.getRightPanel().drawAxis();
//				simulation.getRightPanel().getGraphics2D().setColor(Color.BLACK);
//						
//				peso.setFrame(MyConstants.BORDER_X+prov, (simulation.getRightPanel().getHeight()-MyConstants.BORDER_Y)-prov, 5, 5);
//				simulation.getRightPanel().getGraphics2D().draw(peso);
//				
//				prov++;
				
				String generazione = simulation.getLeftPanel().getOptionsPanel().getGenerationList().getSelectedItem().toString();
				String lancio = simulation.getLeftPanel().getOptionsPanel().getThrowList().getSelectedItem().toString();
				
				ArrayList<Double> info = simulation.readNet(generazione, lancio);
				
//				simulation.getRightPanel().drawAxis();
				
				if (x_tgt != info.get(MyConstants.X_TARGET_INDEX) || y_tgt != info.get(MyConstants.Y_TARGET_INDEX))
				{
					//simulation.getRightPanel().clearTarget(x_tgt, y_tgt);
					//simulation.getRightPanel().drawTarget(info.get(MyConstants.X_TARGET_INDEX), info.get(MyConstants.Y_TARGET_INDEX));
					
//		            double X_tgt = simulation.getRightPanel().proportionX(info.get(MyConstants.X_TARGET_INDEX));
//		            double Y_tgt = simulation.getRightPanel().proportionY(info.get(MyConstants.Y_TARGET_INDEX));
//					
//					simulation.getRightPanel().getTarget().setFrame(MyConstants.BORDER_X + X_tgt, Y_tgt, 5, 5);
					
					simulation.getLeftPanel().updateInfoRete(info);
					simulation.getLeftPanel().updateInfoLancio(info);
					
					x_tgt = info.get(MyConstants.X_TARGET_INDEX);
					y_tgt = info.get(MyConstants.Y_TARGET_INDEX);
				}
				
	            double X_tgt = simulation.getRightPanel().proportionX(info.get(MyConstants.X_TARGET_INDEX));
	            double Y_tgt = simulation.getRightPanel().proportionY(info.get(MyConstants.Y_TARGET_INDEX));
				
				simulation.getRightPanel().getTarget().setFrame(MyConstants.BORDER_X + X_tgt, (simulation.getRightPanel().getHeight()-MyConstants.BORDER_Y) - Y_tgt, 5, 5);
				
//				else
//				{
//					simulation.getRightPanel().drawTarget(info.get(MyConstants.X_TARGET_INDEX), info.get(MyConstants.Y_TARGET_INDEX));
//				}
				
//				if (circle!=null) simulation.getRightPanel().getGraphics2D().clearRect((int)circle.getX(), (int)circle.getY(), 5, 5);
//				circles.clear();
				
				a = info.get(MyConstants.ANGOLO_INDEX);
				v = info.get(MyConstants.VELOCITA_INDEX);
				
	            double y = Math.tan(a)*x - ((MyConstants.GRAVITY/(2*Math.pow(v, 2)*Math.pow(Math.cos(a), 2)))*Math.pow(x, 2));
	            
	            double X = simulation.getRightPanel().proportionX(x);
	            double Y = simulation.getRightPanel().proportionY(y);
	            
//	            circle = new Ellipse2D.Double(MyConstants.BORDER_X+X,(simulation.getRightPanel().getHeight()-MyConstants.BORDER_Y)-Y, 3, 3);
	            
	            
	            
	            if (Y > 0)
	            {
	    			simulation.getRightPanel().getPeso().setFrame(
	    					MyConstants.BORDER_X+X,(simulation.getRightPanel().getHeight()-MyConstants.BORDER_Y)-Y, 3, 3);
//		            circles.add(circle);
//					for (Ellipse2D l : circles)
//					{
//						simulation.getRightPanel().getGraphics2D().setColor(Color.BLACK);
//						simulation.getRightPanel().getGraphics2D().fill(l);
//					}
	            }
	            
				if (x > 100 || y > 100) 
				{
					x=0;
				}
				x++;    
			}
			
			long endTime = System.currentTimeMillis() - startTime;
			long waitTime = (MyConstants.MILLISECOND/MyConstants.FPS) - endTime/MyConstants.MILLISECOND;
			
//			updatePanel();
//			renderPanel();

//			simulation.getRightPanel().drawParabola(0.5, 60);
//			if (line!=null) simulation.getRightPanel().getGraphics2D().clearRect((int)line.getX1(), (int)line.getY1(), 1, 1);
//			lines.clear();

//			line = new Line2D.Double(MyConstants.BORDER_X+x,(simulation.getRightPanel().getHeight()-MyConstants.BORDER_Y)-Y,MyConstants.BORDER_X+x,(simulation.getRightPanel().getHeight()-MyConstants.BORDER_Y)-Y);
//			lines.add(line);
//            double Y = y*(simulation.getRightPanel().getHeight()-MyConstants.BORDER_Y)/100;	///PROPORZIONI Y
//            double X = x*(simulation.getRightPanel().getWidth()-MyConstants.BORDER_X)/100;	///PROPORZIONI X
//            System.out.println(simulation.getRightPanel().getHeight() + " height");
//            System.out.println(simulation.getRightPanel().getWidth() + " width");

            

			try 
			{
				mainThread.sleep(waitTime);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

		}
	}
}
