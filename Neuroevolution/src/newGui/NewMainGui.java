package newGui;

import javax.swing.JFrame;

public class NewMainGui 
{
	public static void main(String[] args) 
	{
		JFrame frame = new MainFrame();
		MainPanel mainPanel = new MainPanel(frame); 
		frame.setVisible(true);	
	}
}
