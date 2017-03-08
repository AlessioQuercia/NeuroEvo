package newGui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import jNeatCommon.EnvConstant;
import myGui.myGuiConstants;

public class SimulationLeftPanel extends JPanel
{		
	JFrame frame;
	
	OptionsPanel optionsPanel;
	NetDetailsPanel netPanel;
	ThrowDetailsPanel throwPanel;
	JLabel generationLabel;

	//	JTextPane infoRete;
//	JTextPane infoLancio;
	Document doc1;
	Document doc2;
    SimpleAttributeSet attributes;
    SimpleAttributeSet attr;
	String mask6d;
	DecimalFormat fmt6d;
    
	public SimulationLeftPanel(JFrame frame) 
	{
		this.frame = frame;
		
		init();
		
	}
	
	public OptionsPanel getOptionsPanel()
	{
		return optionsPanel;
	}
	
	public void getInfoRete()//ArrayList<Double> array)
	{
		try 
		{
			doc1.insertString(doc1.getLength(),  "errore totale:  " + "\n", attr);
			doc1.insertString(doc1.getLength(),  "fitness totale:  " + "\n", attr);
			doc1.insertString(doc1.getLength(),  "fitness vecchia:  ", attr);
		} catch (BadLocationException e) 
		{
			e.printStackTrace();
		}
//		update(getGraphics());
//		infoRete.validate();
//		infoRete.repaint();
//		infoRete.update(infoRete.getGraphics());
//		String[] info_rete =
//			{
//				"errore totale:  " + "\n",
//				"fitness totale:  " + "\n",
//				"fitness vecchia:  ",
//			};
//		return info_rete;
	}
	
	public void getInfoLancio()//ArrayList<Double> array)
	{
//		infoLancio.setText("");
//		infoLancio.revalidate();
//		infoLancio.repaint();
		try 
		{
			doc2.insertString(doc2.getLength(),  "errore totale:  " + "\n", attributes);
			doc2.insertString(doc2.getLength(),  "fitness totale:  " + "\n", attributes);
			doc2.insertString(doc2.getLength(),  "errore totale:  " + "\n", attributes);
			doc2.insertString(doc2.getLength(),  "fitness totale:  " + "\n", attributes);
			doc2.insertString(doc2.getLength(),  "errore totale:  " + "\n", attributes);
			doc2.insertString(doc2.getLength(),  "fitness totale:  " + "\n", attributes);
			doc2.insertString(doc2.getLength(),  "errore totale:  " + "\n", attributes);
			doc2.insertString(doc2.getLength(),  "fitness totale:  " + "\n", attributes);
			doc2.insertString(doc2.getLength(),  "errore totale:  " + "\n", attributes);
			doc2.insertString(doc2.getLength(),  "fitness vecchia:  ", attributes);
			
		} 
		catch (BadLocationException e) 
		{
			e.printStackTrace();
		}
//		infoLancio.validate();
//		infoLancio.repaint();
//		infoLancio.update(infoRete.getGraphics());
//		String[] info_lancio = 
//			{
//				"x_target:  " + "\n",
//				"y_target:  " + "\n",
//				"y_lancio:  " + "\n",
//				"angolo:  " + "\n",
//				"velocità:  " + "\n",
//				"forza:  " + "\n",
//				"tempo:  " + "\n",	
//				"accelerazione:  " + "\n",
//				"massa:  " + "\n",
//				"errore:  ",
//			};
//		return info_lancio;
	}
	
	public void updateInfoRete(ArrayList<Double> array)
	{
//		infoRete.setText("");
//		infoRete.revalidate();
//		infoRete.repaint();
////		infoRete.update(infoRete.getGraphics());
////		infoRete.validate();
////		infoRete.repaint();
//		
//		try {
//			doc1.insertString(doc1.getLength(),  "errore totale:  " + fmt6d.format(array.get(MyConstants.ERRORE_TOTALE_INDEX)) + "\n", attr);
//			doc1.insertString(doc1.getLength(),  "fitness totale:  " + fmt6d.format(array.get(MyConstants.FITNESS_TOTALE_INDEX)) + "\n", attr);
//			doc1.insertString(doc1.getLength(),  "fitness vecchia:  " + fmt6d.format(array.get(MyConstants.FITNESS_VECCHIA_INDEX)), attr);
//		} catch (BadLocationException e) 
//		{
//			e.printStackTrace();
//		}
////		update(getGraphics());
//		infoRete.validate();
//		infoRete.repaint();
//		infoRete.update(infoRete.getGraphics());
		
//		String[] info_rete =
//			{
//				"errore totale:  " + array.get(MyConstants.ERRORE_TOTALE_INDEX) + "\n",
//				"fitness totale:  " + array.get(MyConstants.FITNESS_TOTALE_INDEX) + "\n",
//				"fitness vecchia:  " + array.get(MyConstants.FITNESS_VECCHIA_INDEX),
//			};
		String info_rete =
			"errore totale:  " + array.get(MyConstants.ERRORE_TOTALE_INDEX) + "\n" +
			"fitness totale:  " + array.get(MyConstants.FITNESS_TOTALE_INDEX) + "\n" +
			"fitness vecchia:  " + array.get(MyConstants.FITNESS_VECCHIA_INDEX);
		
		netPanel.getInfoRete().setText(info_rete);
		
		
//		};
//		infoRete.setText(info_rete);
////		for (String s : info_rete)
////			infoRete.append(s);
//		infoRete.revalidate();
//		infoRete.repaint();
	}
	
	public void updateInfoLancio(ArrayList<Double> array)
	{
//		System.out.println("pappetta");
//		infoLancio.setText("");
////		infoLancio.validate();
////		infoLancio.repaint();
//		try 
//		{
//			doc2.insertString(doc2.getLength(),  "errore totale:  " + fmt6d.format(array.get(MyConstants.X_TARGET_INDEX)) + "\n", attributes);
//			doc2.insertString(doc2.getLength(),  "fitness totale:  " + fmt6d.format(array.get(MyConstants.Y_TARGET_INDEX)) + "\n", attributes);
//			doc2.insertString(doc2.getLength(),  "errore totale:  " + fmt6d.format(array.get(MyConstants.Y_LANCIO_INDEX)) + "\n", attributes);
//			doc2.insertString(doc2.getLength(),  "fitness totale:  " + fmt6d.format(array.get(MyConstants.ANGOLO_INDEX)) + "\n", attributes);
//			doc2.insertString(doc2.getLength(),  "errore totale:  " + fmt6d.format(array.get(MyConstants.VELOCITA_INDEX)) + "\n", attributes);
//			doc2.insertString(doc2.getLength(),  "fitness totale:  " + fmt6d.format(array.get(MyConstants.FORZA_INDEX)) + "\n", attributes);
//			doc2.insertString(doc2.getLength(),  "errore totale:  " + fmt6d.format(array.get(MyConstants.TEMPO_INDEX)) + "\n", attributes);
//			doc2.insertString(doc2.getLength(),  "fitness totale:  " + fmt6d.format(array.get(MyConstants.ACCELERAZIONE_INDEX)) + "\n", attributes);
//			doc2.insertString(doc2.getLength(),  "errore totale:  " + fmt6d.format(array.get(MyConstants.MASSA_INDEX)) + "\n", attributes);
//			doc2.insertString(doc2.getLength(),  "fitness vecchia:  " + fmt6d.format(array.get(MyConstants.ERRORE_INDEX)), attributes);
//			
//		} 
//		catch (BadLocationException e) 
//		{
//			e.printStackTrace();
//		}
//		infoLancio.validate();
//		infoLancio.repaint();
//		infoLancio.update(infoLancio.getGraphics());
		
		
		String info_lancio = 
				"x_target:  " + array.get(MyConstants.X_TARGET_INDEX) + "\n" +
				"y_target:  " + array.get(MyConstants.Y_TARGET_INDEX) + "\n" +
				"y_lancio:  " + array.get(MyConstants.Y_LANCIO_INDEX) + "\n" +
				"angolo:  " + array.get(MyConstants.ANGOLO_INDEX) + "\n" +
				"velocità:  " + array.get(MyConstants.VELOCITA_INDEX) + "\n" +
				"forza:  " + array.get(MyConstants.FORZA_INDEX) + "\n" +
				"tempo:  " + array.get(MyConstants.TEMPO_INDEX) + "\n" +	
				"accelerazione:  " + array.get(MyConstants.ACCELERAZIONE_INDEX) + "\n" +
				"massa:  " + array.get(MyConstants.MASSA_INDEX) + "\n" +
				"errore:  " + array.get(MyConstants.ERRORE_INDEX);
		
		throwPanel.getInfoLancio().setText(info_lancio);
		
		
//		infoLancio.setText(info_lancio);
//		infoLancio.revalidate();
//		infoLancio.repaint();
	}
	
//	public JTextPane getInfoList1()
//	{
//		return infoRete;
//	}
//	public JTextPane getInfoList2()
//	{
//		return infoRete;
//	}
	
	public void init()
	{
		Dimension size = getPreferredSize();
		mask6d = "  0.0000000";
		fmt6d = new DecimalFormat(mask6d);
		
		size.width = MyConstants.OPTIONS_WIDTH;
		setPreferredSize(size);	
		
    	setLayout(new GridBagLayout());
    	
    	GridBagConstraints gc = new GridBagConstraints();
		
		optionsPanel = new OptionsPanel(frame);
		netPanel = new NetDetailsPanel(frame);
		throwPanel = new ThrowDetailsPanel(frame);
		generationLabel = new JLabel();
		
		///  STILE SCRITTURA
		attributes = new SimpleAttributeSet();
	    StyleConstants.setBold(attributes, true);
	    StyleConstants.setItalic(attributes, true);
	    
		attr = new SimpleAttributeSet();
	    StyleConstants.setBold(attr, true);
	    StyleConstants.setItalic(attr, true);
		
//		infoRete = new JTextPane();
////		infoRete.setBorder(BorderFactory.createTitledBorder("Dettagli rete"));
//		infoRete.setFont(getFont());
////		infoRete.setLineWrap(true);
//		infoRete.setEditable(false);
//		infoRete.setOpaque(false);
////		infoRete.setWrapStyleWord(true);
//		infoRete.setVisible(true);
//		doc1 = infoRete.getDocument();
////		getInfoRete();
////		for (String s : getInfoRete())
////			infoRete.append(s);
//		
//		infoLancio = new JTextPane();
////		infoLancio.setBorder(BorderFactory.createTitledBorder("Dettagli lancio"));
//		infoLancio.setFont(getFont());
////		infoLancio.setLineWrap(true);
//		infoLancio.setEditable(false);
//		infoLancio.setOpaque(false);
////		infoLancio.setWrapStyleWord(true);
//		infoLancio.setVisible(true);
//		doc2 = infoLancio.getDocument();
//		getInfoLancio();
//		for (String s : getInfoLancio())
//			infoLancio.append(s);
		
		
//		gc.anchor = GridBagConstraints.LINE_START;
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 0.5;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;	
		add(optionsPanel, gc);	
		
		gc.weightx = 0.5;
		gc.weighty = 0.01;
		
		gc.gridx = 0;
		gc.gridy = 1;	
		add(netPanel, gc);
		
		gc.weighty = 10;
		
		gc.gridx = 0;
		gc.gridy = 2;		
		add(throwPanel, gc);
		
		gc.weighty = 0.01;
		
		gc.gridx = 0;
		gc.gridy = 3;		
		add(generationLabel, gc);
		
		
	}
	
	public JLabel getGenerationLabel() 
	{
		return generationLabel;
	}
//	public JTextPane getRete()
//	{
//		return infoRete;
//	}
}
