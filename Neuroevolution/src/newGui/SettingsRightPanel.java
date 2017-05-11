package newGui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.ParamValue;

public class SettingsRightPanel extends JPanel implements ListSelectionListener
{
	private JFrame frame;
	private SessionSettings sessionSettings;
	private ParameterSettings parameterSettings;
	private OtherSettings otherSettings;
	private JTextArea description;

	private GridBagConstraints gc;
	
	public SettingsRightPanel(JFrame frame)
	{
		this.frame = frame;
		
		init();
	}
	
	public void init()
	{
		setLayout(new GridBagLayout());	
		
		sessionSettings = new SessionSettings(frame);
		parameterSettings = new ParameterSettings(frame);
		otherSettings = new OtherSettings(frame);
		description = new JTextArea();
		description.setBorder(BorderFactory.createTitledBorder("Description"));
		Dimension size = description.getSize();
		size.height = 110;
		description.setMinimumSize(size);
		description.setPreferredSize(size);
		description.setBackground(null);
		description.setEditable(false);
		
    	gc = new GridBagConstraints();
    	
		gc.fill = GridBagConstraints.BOTH;
		
		gc.gridx = 0;
		gc.gridy = 1;
    	add(description, gc);
    	
		gc.weightx = 0.5;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
    	add(sessionSettings, gc);
    	add(parameterSettings, gc);
    	add(otherSettings, gc);
		
	}
	
	public void setSessionSection()
	{
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		remove(sessionSettings);
		remove(parameterSettings);
		remove(otherSettings);
    	add(sessionSettings, gc);
	}
	
	public void setParameterSection()
	{
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		remove(sessionSettings);
		remove(parameterSettings);
		remove(otherSettings);
    	add(parameterSettings, gc);
    	repaint();
	}
	
	public void setOtherSection()
	{
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		remove(sessionSettings);
		remove(parameterSettings);
		remove(otherSettings);
    	add(otherSettings, gc);
    	repaint();
	}

	public void updateDescritpion(String text)
	{
		description.setText(text);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		 int irow = 0;
		 Object s_descr = null;
		 Object s2 = null;
		 ParamValue ox = null;
		 String r2 = null;
		 String r3 = null;
		 String tipo = null;
	  
	  
		 if (e.getValueIsAdjusting())
		 {
			return;
		 }
	  
	  
		 ListSelectionModel lsm = (ListSelectionModel) e.getSource();
	  
	  
		 if (!lsm.isSelectionEmpty()) 
		 {
			irow = lsm.getMinSelectionIndex();
			ox = (ParamValue) parameterSettings.getModel().data.elementAt(irow);
			s_descr = parameterSettings.getNetx().getDescription((String) ox.o1);
			s2 = ox.o2;
		 
			if ( s2 instanceof Integer)
			{
			   tipo = new String(" integer ");
			}
			if ( s2 instanceof Double )
			{
			   tipo = new String(" double");
			}
		 
			r2 = "\n Current setting is " + s2;
			r3 = s_descr+r2+tipo;
			updateDescritpion(r3);
		 
//			scrollPanel.revalidate();
//			scrollPanel.validate();
		 }		
	}
}
