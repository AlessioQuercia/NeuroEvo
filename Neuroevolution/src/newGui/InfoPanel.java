package newGui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class InfoPanel extends JPanel
{
	public InfoPanel() 
	{
		Dimension size = getPreferredSize();
		
		size.width = MyConstants.OPTIONS_WIDTH;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("Options"));
	}
}
