package newGui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jNeatCommon.EnvConstant;
import jneat.Organism;

public class NetLeftPanel extends JPanel
{
	private JFrame frame;
	
	private NetOptionsPanel optionsPanel;
	private NetDetailsPanel detailsPanel;

	private JLabel generationLabel;
	
	public NetLeftPanel(JFrame frame)
	{
		this.frame = frame;
		
		init();
	}

	private void init() 
	{	
		Dimension size = getPreferredSize();
		size.width = MyConstants.OPTIONS_WIDTH;
		setPreferredSize(size);	
		
		setLayout(new GridBagLayout());
		
		optionsPanel = new NetOptionsPanel(frame);
		
		detailsPanel = new NetDetailsPanel(frame);
		
		generationLabel = new JLabel();
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 0.5;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;		
		add(optionsPanel, gc);
		
		gc.weighty = 10;
		gc.gridx = 0;
		gc.gridy = 1;		
		add(detailsPanel, gc);
		
		gc.weighty = 0.01;
		gc.gridx = 0;
		gc.gridy = 2;		
		add(generationLabel, gc);
	}

	public JLabel getGenerationLabel()
	{
		return generationLabel;
	}
	
	public NetOptionsPanel getOptionsPanel() 
	{
		return optionsPanel;
	}
	
	public void updateInfoRete(Organism o)
	{
		int numNodes = o.getGenome().getNodes().size();
		int inputNodes = EnvConstant.NR_UNIT_INPUT;
		int outputNodes = EnvConstant.NR_UNIT_OUTPUT;
		int biasNodes = EnvConstant.NR_UNIT_BIAS;
		int hiddenNodes = numNodes - inputNodes - outputNodes - biasNodes;
		String info_rete =
				"nodi totali:  " + numNodes + "\n" +
				"nodi bias:  " + biasNodes + "\n" +
				"nodi di input:  " + inputNodes + "\n" +
				"nodi hidden:  " + hiddenNodes + "\n" +
				"nodi di output:  " + outputNodes + "\n" +
				"\n" + "Lista input:  " + "\n" + 
				"- id_1 = x_target" + "\n" +
				"- id_2 = y_target" + "\n" +
				"- id_3 = velocità" + "\n" +
				"\n" + "Lista output:  " + "\n" + 
				"- id_9 = angolo" + "\n" +
				"- id_10 = forza" + "\n" +
				"- id_11 = lascia" + "\n";

			
			detailsPanel.getInfoRete().setText(info_rete);
	}
}
