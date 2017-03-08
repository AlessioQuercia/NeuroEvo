package newGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import log.HistoryLog;

public class Graphs extends JPanel implements ActionListener
{
	private JFrame f;
	private JPanel mainPanel;
	
	public Graphs(JFrame f) 
	{
		this.f = f;
	}

	public JPanel getMainJPanel() 
	{
		return mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
