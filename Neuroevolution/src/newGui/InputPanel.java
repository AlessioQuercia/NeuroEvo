package newGui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.Format;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class InputPanel extends JPanel
{
	private JFrame frame;
	
	private JLabel xLabel;
	private JLabel yLabel;
	
	private JFormattedTextField xArea;
	private JFormattedTextField yArea;
	
	private JButton loadInputBtn;
	
	public InputPanel(JFrame frame)
	{
		this.frame = frame;
		
		init();
	}

	private void init()
	{
		setLayout(new GridBagLayout());
		
		setBorder(BorderFactory.createTitledBorder("Input rete"));
    	
    	GridBagConstraints gc = new GridBagConstraints();
    	
    	xLabel = new JLabel("x_target:");
    	yLabel = new JLabel("y_target:");
    	
    	Format format = NumberFormat.getInstance();
    	
    	xArea = new JFormattedTextField(format);
    	xArea.setEditable(true);
    	
    	yArea = new JFormattedTextField(format);
    	yArea.setEditable(true);
    	
    	loadInputBtn = new JButton("Load inputs");
		
		////// First column ////////
		gc.anchor = GridBagConstraints.LINE_START;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		gc.gridx = 0;
		gc.gridy = 0;	
		add(xLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;		
		add(yLabel, gc);
		
		////// Second column ////////
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;	
		add(xArea, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;		
		add(yArea, gc);
		
		// Final row
//		gc.weighty = 10;
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 2;
		add(loadInputBtn, gc);
	}
}
