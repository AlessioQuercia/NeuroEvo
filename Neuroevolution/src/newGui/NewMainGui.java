package newGui;

import javax.swing.JFrame;

public class NewMainGui 
{
	public static void main(String[] args) 
	{
		JFrame frame = new MainFrame();
		MainPanel mainPanel = new MainPanel(frame); 
		frame.setVisible(true);
//		String filename = MyConstants.DATA_DIR + "genome_prova";
//		MyMethods.generateGenomeToFile(filename, 1, 3, 2, 2, 2, false, 1, false, false);
	}
}
