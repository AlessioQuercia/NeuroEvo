package newGui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class OtherSettings extends JPanel
{
	private JFrame frame;
	
	private OtherUpperPanel upperPanel;
	private JTextArea description;
	
	public OtherSettings(JFrame frame) 
	{
		this.frame = frame;
		
		init();
	}
	
	public void init()
	{
//		setBorder(BorderFactory.createTitledBorder("Other settings"));
		
		setLayout(new GridBagLayout());	
		
		GridBagConstraints gc = new GridBagConstraints();
		
		upperPanel = new OtherUpperPanel(frame);
		
		description = new JTextArea();
		description.setBorder(BorderFactory.createTitledBorder("Description"));
		Dimension size = description.getSize();
		size.height = 110;
		description.setMinimumSize(size);
		description.setPreferredSize(size);
		description.setBackground(null);
		description.setEditable(false);
		
		gc.fill = GridBagConstraints.BOTH;
		
		gc.gridx = 0;
		gc.gridy = 1;
    	add(description, gc);
    	
		gc.weightx = 0.5;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
    	add(upperPanel, gc);
	}
	
	public OtherUpperPanel getUpperPanel() {
		return upperPanel;
	}

	public JTextArea getDescription() {
		return description;
	}

	public void updateDescription(String text) 
	{
		description.setText(text);
	}
}
