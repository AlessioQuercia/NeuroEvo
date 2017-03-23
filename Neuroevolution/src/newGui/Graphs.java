package newGui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.joml.Vector2d;

import jGraph.chartXY;
import jGraph.code;
import jNeatCommon.CodeConstant;
import jNeatCommon.EnvConstant;
import jneat.Population;
import log.HistoryLog;

public class Graphs extends JPanel
{
	private JFrame f;
	
	private Chart chart;

	public Graphs(JFrame f) 
	{
		this.f = f;
		
		init();
	}
	
	public void init()
	{
		setLayout(new GridBagLayout());
    	
		setBorder(BorderFactory.createTitledBorder("Grafico fitness"));
		
    	GridBagConstraints gc = new GridBagConstraints();
    	
		chart = new Chart(f, 100, 100000, "Generation", "Fitness", 10, 5);
		chart.addLine();	// AGGIUNTA LINEA PER RAPPRESENTARE FITNESS PIU' ALTA
		chart.addLine();	// AGGIUNTA LINEA PER RAPPRESENTARE FITNESS MEDIA
	 
//		chart.setGrid(true);
    	
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.BOTH;
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
    	add(chart, gc);
	}
	
	
	public Chart getChart() 
	{
		return chart;
	}

	public void updateGraphPanel(Population pop)
	{
		double highest_fitness = pop.getHighest_fitness();
		double mean_fitness = pop.getMean_fitness();
		double generation = pop.getFinal_gen();
		
		chart.addVector(0, new Vector2d(generation, highest_fitness));
		chart.addVector(1, new Vector2d(generation, mean_fitness));
	}
	
}
