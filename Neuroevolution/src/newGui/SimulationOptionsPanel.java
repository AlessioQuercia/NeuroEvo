package newGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jNeatCommon.EnvConstant;
import myGui.myGuiConstants;

public class SimulationOptionsPanel extends JPanel
{
	private JFrame frame;
	
	private JComboBox generationList;
	private JComboBox throwList;

	private JButton startBtn;
	private JButton loadBtn;
//	private JButton autodrawBtn;
//	private JButton showBestBtn;
//	private JButton settingsBtn;
//	private JButton physicsBtn;

	GridBagConstraints gc;

	public SimulationOptionsPanel(JFrame frame) 
	{
		this.frame = frame;
		
		init();
	}
	
	private void init() 
	{
//		Dimension size = getPreferredSize();
//		
//		size.width = MyConstants.OPTIONS_WIDTH;
//		setPreferredSize(size);
		
		setLayout(new GridBagLayout());
		
		setBorder(BorderFactory.createTitledBorder("Selezione rete"));
		
		JLabel genLabel = new JLabel("Generazione: ");
		JLabel throwLabel = new JLabel("Lancio: ");
		
//		JTextArea info = new JTextArea();
//		info.setFont(getFont());
//		info.setLineWrap(true);
//		info.setEditable(false);
//		info.setOpaque(false);
//		info.setWrapStyleWord(true);
//		info.setVisible(true);
//		
//		//info.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//		info.setBackground(new Color(255, 242, 232));
//
//		JTextArea info2 = new JTextArea("info2");
//		info2.setFont(getFont());
//		info2.setLineWrap(true);
//		info2.setEditable(false);
//		info2.setOpaque(false);
//		info2.setWrapStyleWord(true);
//		info2.setVisible(true);
//		//info.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//		info2.setBackground(new Color(255, 242, 232));
		
//		String[] prova = { "7000", "2", "1", "10", "670" };
		generationList = new JComboBox();
//		generationList.setSelectedIndex(4);
		
//		String[] prova2 = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Best"};
		throwList = new JComboBox();

		//list.setSelectedItem("Best");
//		list.addItem("1.5");
//		list.setSelectedIndex(4);
		
//		autodrawBtn = new JButton("Auto-draw: OFF");
		
//		showBestBtn = new JButton("Show best");
		
		
		startBtn = new JButton("Start");
		Dimension size = startBtn.getSize();
		size.width = 105;
		size.height = 25;
		startBtn.setMinimumSize(size);
		startBtn.setPreferredSize(size);
		startBtn.setMaximumSize(size);
		
		loadBtn = new JButton("Load");
		loadBtn.setMinimumSize(size);
		loadBtn.setPreferredSize(size);
		loadBtn.setMaximumSize(size);
		
		generationList.setMinimumSize(size);
		generationList.setPreferredSize(size);
		generationList.setMaximumSize(size);
		
		throwList.setMinimumSize(size);
		throwList.setPreferredSize(size);
		throwList.setMaximumSize(size);
		
//		settingsBtn = new JButton("Settings");
		
//		physicsBtn = new JButton("Physics");
		
		gc = new GridBagConstraints();
		
		////// First column ////////
		gc.anchor = GridBagConstraints.LINE_START;
//		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weighty = 0.5;
		
		gc.gridx = 0;
		gc.gridy = 0;	
		add(genLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;		
		add(throwLabel, gc);
		
		////// Second column ////////
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 0;	
		add(generationList, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;		
		add(throwList, gc);
		
		// Final row
//		gc.weighty = 10;
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 0;
		gc.gridy = 2;
		add(startBtn, gc);
		
		gc.weightx = 0.5;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 2;
		add(loadBtn, gc);
		
//		gc.anchor = GridBagConstraints.LINE_START;
//		gc.gridx = 0;
//		gc.gridy = 3;
//		add(loadBtn, gc);
//		
//		gc.anchor = GridBagConstraints.LINE_START;
//		gc.gridx = 1;
//		gc.gridy = 3;
//		add(showBestBtn, gc);
//		
//		gc.anchor = GridBagConstraints.LINE_START;
//		gc.gridx = 0;
//		gc.gridy = 4;
//		add(settingsBtn, gc);
//		
//		gc.anchor = GridBagConstraints.LINE_START;
//		gc.gridx = 1;
//		gc.gridy = 4;
//		add(physicsBtn, gc);
		

		
//		gc.weighty = 15;
////		gc.weightx = 10;
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		gc.fill = GridBagConstraints.BOTH;
//		gc.gridx = 0;
//		gc.gridy = 3;
//		add(info, gc);
//		
//		gc.weighty = 15;
////		gc.weightx = 10;
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		gc.fill = GridBagConstraints.BOTH;
//		gc.gridx = 1;
//		gc.gridy = 3;
//		add(info2, gc);
		
	}

	public JComboBox getGenerationList()
	{
		return generationList;
	}

	public JComboBox getThrowList()
	{
		return throwList;
	}
	
	public JButton getStartBtn() 
	{
		return startBtn;
	}
	
//	public JButton getAutodrawBtn()
//	{
//		return autodrawBtn;
//	}
//	
//	public JButton getShowBestBtn()
//	{
//		return showBestBtn;
//	}

	public JButton getLoadBtn() 
	{
		return loadBtn;
	}
	
//	public JButton getSettingsBtn()
//	{
//		return settingsBtn;
//	}
//
//	public void setSettingsBtn(JButton settingsBtn) 
//	{
//		this.settingsBtn = settingsBtn;
//	}
//
//	public JButton getPhysicsBtn()
//	{
//		return physicsBtn;
//	}
//
//	public void setPhysicsBtn(JButton physicsBtn) 
//	{
//		this.physicsBtn = physicsBtn;
//	}

	public GridBagConstraints getGC() 
	{
		return gc;
	}
}
