package newGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.joml.Vector2d;

import jGraph.chartXY;
import jGraph.code;
import jNeatCommon.CodeConstant;
import jNeatCommon.EnvConstant;
import jneat.Population;
import log.HistoryLog;

public class Graphs extends JPanel implements ActionListener
{
	private JFrame f;
	
	private GraphLeftPanel leftPanel;
	private Chart fitnessChart;
	private Chart errorChart;
	
	private GridBagConstraints gc;
	private boolean fitness, error;

	public Graphs(JFrame f) 
	{
		this.f = f;
		
		fitness = true;
		error = false;
		
		init();
	}
	
	public void init()
	{
		setLayout(new GridBagLayout());
    	
//		setBorder(BorderFactory.createTitledBorder("Grafico fitness"));
		
    	gc = new GridBagConstraints();
    	
    	leftPanel = new GraphLeftPanel(f);
    	leftPanel.getOptionsPanel().getGridButton().addActionListener(this);;
    	
		fitnessChart = new Chart(f, 200, 100000, "Generation", "Fitness", 10, 5);
		fitnessChart.addLine("Mean fitness", Color.BLUE);	// AGGIUNTA LINEA PER RAPPRESENTARE FITNESS MEDIA
		fitnessChart.addLine("Mean cloned fitness", Color.GREEN);	// AGGIUNTA LINEA PER RAPPRESENTARE FITNESS MEDIA DEI CLONATI
		fitnessChart.addLine("Highest fitness", Color.RED);	// AGGIUNTA LINEA PER RAPPRESENTARE FITNESS PIU' ALTA
		fitnessChart.setGrid(true);
		fitnessChart.setBorder(BorderFactory.createTitledBorder("Fitness chart"));
		
		errorChart = new Chart(f, 200, 300, "Generation", "Error", 10, 10);
//		errorChart.addLine("Mean error", Color.BLUE);	// AGGIUNTA LINEA PER RAPPRESENTARE ERRORE MEDIO
		errorChart.addLine("Lowest error", Color.RED);	// AGGIUNTA LINEA PER RAPPRESENTARE ERRORE PIU' BASSO
		errorChart.setGrid(true);
		errorChart.setBorder(BorderFactory.createTitledBorder("Error chart"));
		errorChart.startFromMax();
		
		
		leftPanel.getLegendPanel().setLegend(fitnessChart.getNames(), fitnessChart.getColors());
		leftPanel.getOptionsPanel().getChartList().addItem("Fitness");
		leftPanel.getOptionsPanel().getChartList().addItem("Error");
		
		
    	
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.VERTICAL;
		
		gc.gridx = 0;
		gc.gridy = 0;
    	add(leftPanel, gc);
    	
		gc.fill = GridBagConstraints.BOTH;
		
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		gc.gridx = 1;
		gc.gridy = 0;
    	add(fitnessChart, gc);
    	add(errorChart, gc);
//    	setLayout(new GridBagLayout());
//    	
//    	GridBagConstraints gc = new GridBagConstraints();
//    	
////    	OptionsPanel options = new OptionsPanel();
//    	
//    	leftPanel = new SimulationLeftPanel(frame);
//    	leftPanel.getOptionsPanel().getStartBtn().addActionListener(this);
//    	leftPanel.getOptionsPanel().getAutodrawBtn().addActionListener(this);
//    	
//    	rightPanel = new ThrowPanel(frame);
//    	
////    	JTextArea info = new JTextArea();
//
////		gc.weighty = 10;
////		gc.weightx = 10;
////		gc.anchor = GridBagConstraints.FIRST_LINE_START;
////		gc.fill = GridBagConstraints.BOTH;
//    	
////		gc.weightx = 0.5;
////		gc.weighty = 0.5;
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		gc.fill = GridBagConstraints.VERTICAL;
//		
//		gc.gridx = 0;
//		gc.gridy = 0;
//    	add(leftPanel, gc);
//    	
//		gc.weightx = 0.5;
//		gc.weighty = 0.5;
//    	gc.fill = GridBagConstraints.BOTH;
//    	
//		gc.gridx = 1;
//		gc.gridy = 0;
//    	add(rightPanel, gc);
    	
	}
	
	
	public Chart getFitnessChart() 
	{
		return fitnessChart;
	}
	
	public Chart getErrorChart() 
	{
		return errorChart;
	}

	public void updateGraphPanel(Population pop)
	{
		double highest_fitness = pop.getHighest_fitness();
		double mean_fitness = pop.getMean_fitness();
		double mean_cloned_fitness = pop.getMean_cloned_fitness();
		double lowest_error = pop.getLowest_error();
		double mean_error = pop.getMean_error();
		double generation = pop.getFinal_gen();

		fitnessChart.addVector(0, new Vector2d(generation, mean_fitness));
		fitnessChart.addVector(1, new Vector2d(generation, mean_cloned_fitness));
		fitnessChart.addVector(2, new Vector2d(generation, highest_fitness));
		
//		errorChart.addVector(0, new Vector2d(generation, mean_error));	//TROPPO ALTO
		errorChart.addVector(0, new Vector2d(generation, lowest_error));
	}

	public GraphLeftPanel getLeftPanel() 
	{
		return leftPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		 JButton p = (JButton) e.getSource();
		 
		 if (p.getActionCommand().equals("Grid: OFF")) 
		 {
			 fitnessChart.setGrid(false);
			 errorChart.setGrid(false);
			 
			 leftPanel.getOptionsPanel().getGridButton().setText("Grid: ON");
		 } 
		 
		 else if (p.getActionCommand().equals("Grid: ON")) 
		 {
			 fitnessChart.setGrid(true);
			 errorChart.setGrid(true);
			 
			 leftPanel.getOptionsPanel().getGridButton().setText("Grid: OFF");
		 } 
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		 if (leftPanel.getOptionsPanel().getChartList().getSelectedItem().equals("Error") && !error)
		 {
			 leftPanel.getLegendPanel().setLegend(errorChart.getNames(), errorChart.getColors());
			 gc.fill = GridBagConstraints.BOTH;
			 gc.weightx = 0.5;
			 gc.weighty = 0.5;
			 gc.gridx = 1;
			 gc.gridy = 0;
//			 remove(fitnessChart);
			 add(fitnessChart, gc);
			 error = true;
			 fitness = false;
		 } 
		 else if (leftPanel.getOptionsPanel().getChartList().getSelectedItem().equals("Fitness") && !fitness)
		 {
			 leftPanel.getLegendPanel().setLegend(fitnessChart.getNames(), fitnessChart.getColors());
			 gc.fill = GridBagConstraints.BOTH;
			 gc.weightx = 0.5;
			 gc.weighty = 0.5;
			 gc.gridx = 1;
			 gc.gridy = 0;
//			 remove(errorChart);
			 add(errorChart, gc);
			 fitness = true;
			 error = false;
		 }
	}
	
}
