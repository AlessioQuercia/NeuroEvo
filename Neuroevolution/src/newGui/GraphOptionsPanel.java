package newGui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphOptionsPanel extends JPanel
{

	private JFrame frame;
	
	private JLabel chartLabel;
	private JComboBox chartList;
	private JButton gridButton;

	public GraphOptionsPanel(JFrame frame)
	{
		this.frame = frame;
		
		init();
	}
	
	private void init() 
	{
		setLayout(new GridBagLayout());
		
		setBorder(BorderFactory.createTitledBorder("Chart selection"));
		
		chartLabel = new JLabel("Grafico: ");
		chartList = new JComboBox();
		gridButton = new JButton("Grid: OFF");
		
		GridBagConstraints gc = new GridBagConstraints();
		
		////// First column ////////
		gc.anchor = GridBagConstraints.LINE_END;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx = 0.1;
		gc.weighty = 0.5;
		
		gc.gridx = 0;
		gc.gridy = 0;	
		add(chartLabel, gc);
		
		
		////// Second column ////////
		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx = 0.5;
		gc.gridx = 1;
		gc.gridy = 0;	
		add(chartList, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;		
		add(gridButton, gc);
	}
	
	public JLabel getChartLabel() 
	{
		return chartLabel;
	}

	public JComboBox getChartList() 
	{
		return chartList;
	}

	public JButton getGridButton() 
	{
		return gridButton;
	}
}