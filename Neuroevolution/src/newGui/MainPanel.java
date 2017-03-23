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

public class MainPanel extends JPanel implements Runnable
{
	private JFrame frame;
	
	private Thread mainThread;
	private boolean isRunning;
	
	private Simulation simulation;
	private Graphs graphs;
	private Net net;
	private JTabbedPane tabbedPanel;

	  
	public MainPanel(JFrame f) 
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

	private void init() 
	{
        // Set layout manager
        
        setLayout(new BorderLayout());
        
        // Create Swing component
        
        simulation = new Simulation(frame);
        
        graphs = new Graphs(frame);
        
        net = new Net(frame);
        
		tabbedPanel = new JTabbedPane();
		tabbedPanel.addTab("Simulation", simulation);
		tabbedPanel.addTab("Graphs", graphs);
		tabbedPanel.addTab("Net", net);
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
		
		while (isRunning)
		{
			long startTime = System.currentTimeMillis();
			
			if (simulation.getStart() && simulation.getLeftPanel().getOptionsPanel().getGenerationList().getSelectedItem() != null)
			{
				simulation.getRightPanel().repaint();
				graphs.getChart().repaint();
				
				String generazione = simulation.getLeftPanel().getOptionsPanel().getGenerationList().getSelectedItem().toString();
				String lancio = simulation.getLeftPanel().getOptionsPanel().getThrowList().getSelectedItem().toString();
				
				ArrayList<Double> info = simulation.readNet(generazione, lancio);
				
				if (x_tgt != info.get(MyConstants.X_TARGET_INDEX) || y_tgt != info.get(MyConstants.Y_TARGET_INDEX))
				{					
					simulation.getLeftPanel().updateInfoRete(info);
					simulation.getLeftPanel().updateInfoLancio(info);
					
					x_tgt = info.get(MyConstants.X_TARGET_INDEX);
					y_tgt = info.get(MyConstants.Y_TARGET_INDEX);
				}
				
	            double X_tgt = simulation.getRightPanel().proportionX(x_tgt);
	            double Y_tgt = simulation.getRightPanel().proportionY(y_tgt);
				
				simulation.getRightPanel().getTarget().setFrame(MyConstants.BORDER_X + X_tgt, 
						(simulation.getRightPanel().getHeight()-MyConstants.BORDER_Y) - Y_tgt, 5, 5);
				
				a = info.get(MyConstants.ANGOLO_INDEX);
				v = info.get(MyConstants.VELOCITA_INDEX);
				
	            double y = Math.tan(a)*x - ((MyConstants.GRAVITY/(2*Math.pow(v, 2)*Math.pow(Math.cos(a), 2)))*Math.pow(x, 2));
	            
	            double X = simulation.getRightPanel().proportionX(x);
	            double Y = simulation.getRightPanel().proportionY(y);
	            
	            if (Y > 0)
	            {
	    			simulation.getRightPanel().getPeso().setFrame(
	    					MyConstants.BORDER_X+X,(simulation.getRightPanel().getHeight()-MyConstants.BORDER_Y)-Y, 3, 3);
	            }
	            
				if (x > MyConstants.ASSE_X || y > MyConstants.ASSE_Y) 
				{
					x=0;
				}
				x++;    
			}
			
			long endTime = System.currentTimeMillis() - startTime;
			long waitTime = (MyConstants.MILLISECOND/MyConstants.FPS) - endTime/MyConstants.MILLISECOND;

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
