package newGui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class GraphLeftPanel extends JPanel
{
	private JFrame frame;

	private GraphOptionsPanel optionsPanel;
	private LegendPanel legendPanel;
	private JLabel generationLabel;
	
    SimpleAttributeSet attributes;
    SimpleAttributeSet attr;

	public GraphLeftPanel(JFrame frame)
	{
		this.frame = frame;
		
		init();
	}
	
	public void init()
	{
		Dimension size = getPreferredSize();
		size.width = MyConstants.OPTIONS_WIDTH;
		setPreferredSize(size);	
		
    	setLayout(new GridBagLayout());
//		setBorder(BorderFactory.createTitledBorder("LEFT PANEL"));
		
    	GridBagConstraints gc = new GridBagConstraints();
		
		optionsPanel = new GraphOptionsPanel(frame);
		legendPanel = new LegendPanel(frame);
		generationLabel = new JLabel();
		
		///  STILE SCRITTURA
		attributes = new SimpleAttributeSet();
	    StyleConstants.setBold(attributes, true);
	    StyleConstants.setItalic(attributes, true);
	    
		attr = new SimpleAttributeSet();
	    StyleConstants.setBold(attr, true);
	    StyleConstants.setItalic(attr, true);

		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 0.5;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;	
		add(optionsPanel, gc);	
		
		gc.weighty = 20;
		
		gc.gridx = 0;
		gc.gridy = 1;	
		add(legendPanel, gc);	
		
		gc.weighty = 0.01;
		
		gc.gridx = 0;
		gc.gridy = 2;		
		add(generationLabel, gc);
	}
	
	public GraphOptionsPanel getOptionsPanel() 
	{
		return optionsPanel;
	}

	public LegendPanel getLegendPanel() 
	{
		return legendPanel;
	}
	
	public JLabel getGenerationLabel() 
	{
		return generationLabel;
	}
	
	
}
