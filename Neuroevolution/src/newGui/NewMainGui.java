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

public class NewMainGui extends JPanel
{
	  private JFrame frame;
	  
	  private Simulation simulation;
	  private Graphs graphs;
	  private JTabbedPane tabbedPanel;

	  
	public NewMainGui(JFrame f) 
	{
		frame = f;	        
		
		init();
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
}
