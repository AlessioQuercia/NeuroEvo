package newGui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import gui.evo_fit;
import gui.evo_in;
import gui.evo_out;
import jNeatCommon.EnvConstant;
import jNeatCommon.EnvRoutine;
import jNeatCommon.IOseq;
import jneat.Genome;
import jneat.NNode;
import jneat.Neat;
import jneat.Network;
import jneat.Organism;
import jneat.Population;
import jneat.Species;
import myGui.myGuiConstants;

public class Simulation extends JPanel implements ActionListener
{
	private JFrame frame;
	
	private MainPanel mainPanel;
	private SimulationLeftPanel leftPanel;
	private ThrowPanel rightPanel;
	
	 String mask6d;
	 DecimalFormat fmt6d;

private boolean start;

private boolean autodraw; 
	
	public Simulation(JFrame frame, MainPanel mainPanel) 
	{
		this.frame = frame;
		this.mainPanel = mainPanel;
		
		start = false;
		autodraw = true;
		
		mask6d = "  0.0000000";
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(','); 
		fmt6d = new DecimalFormat(mask6d, otherSymbols);

		initUI();
	}

    private void initUI() 
    {   
    	setLayout(new GridBagLayout());
    	
    	GridBagConstraints gc = new GridBagConstraints();
    	
//    	OptionsPanel options = new OptionsPanel();
    	
    	leftPanel = new SimulationLeftPanel(frame);
    	leftPanel.getOptionsPanel().getStartBtn().addActionListener(this);
    	leftPanel.getOptionsPanel().getAutodrawBtn().addActionListener(this);
    	
    	rightPanel = new ThrowPanel(frame);
    	
//    	JTextArea info = new JTextArea();

//		gc.weighty = 10;
//		gc.weightx = 10;
//		gc.anchor = GridBagConstraints.FIRST_LINE_START;
//		gc.fill = GridBagConstraints.BOTH;
    	
//		gc.weightx = 0.5;
//		gc.weighty = 0.5;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.fill = GridBagConstraints.VERTICAL;
		
		gc.gridx = 0;
		gc.gridy = 0;
    	add(leftPanel, gc);
    	
		gc.weightx = 0.5;
		gc.weighty = 0.5;
    	gc.fill = GridBagConstraints.BOTH;
    	
		gc.gridx = 1;
		gc.gridy = 0;
    	add(rightPanel, gc);
    	
    	
    	
    	
//    	setLayout(new BorderLayout());	
//    	OptionsPanel options = new OptionsPanel();
//    	add(options, BorderLayout.WEST);
//    	
//    	Lancio parabola = new Lancio();
//    	add(parabola, BorderLayout.EAST);
    	
//    	TextArea a = new TextArea("");
//    	add(a, BorderLayout.EAST);
//		 textArea = new JTextArea("",10,60);
//		 textArea.setFont(getFont());
//		 textArea.setLineWrap(true);
//		 textArea.setEditable(false);
//		 textArea.setOpaque(false);
//		 textArea.setWrapStyleWord(true);
//		 textArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//		 textArea.setVisible(true);
//	  
//		 textArea.setBackground(new Color(255, 242, 232));
    	
//    	setLayout(new GridBagLayout());
    	
    	
//    	GridBagConstraints gc = new GridBagConstraints();
//    	
//    	OptionsLabel options = new OptionsLabel();
//    	
//    	add(options);
//    	
//    	gc.weightx = 0.5;
//    	gc.weighty = 0.5;
//    	
//    	gc.gridx = 1;
//    	gc.gridy = 1;
//    	
//    	add(options, gc);
    	
//    	frame.getContentPane().add(this);
    }

    public SimulationLeftPanel getLeftPanel()
    {
    	return leftPanel;
    }
    
    public ThrowPanel getRightPanel()
    {
    	return rightPanel;
    }
    

	   
	   public void updateSimulationPanel(Organism o)
	   { 
		   int bestThrow = o.getMap().get(EnvConstant.NUMBER_OF_SAMPLES).get(14).intValue();
//		    System.out.println(bestThrow);
		    
			///GENERAZIONE
			leftPanel.getOptionsPanel().getGenerationList().addItem(o.getGeneration());
			if (autodraw) 
				leftPanel.getOptionsPanel().getGenerationList().setSelectedItem(o.getGeneration());;
			
////			System.out.println(leftPanel.getOptionsPanel().getGenerationList().getSelectedItem());
//		   String generazione = leftPanel.getOptionsPanel().getGenerationList().getSelectedItem().toString();
//		   String lancio = leftPanel.getOptionsPanel().getThrowList().getSelectedItem().toString();
//		   ArrayList<Double> info = readNet(generazione, lancio);
//			
//			///RETE
////			leftPanel.updateInfoRete(o.getMap().get(EnvConstant.NUMBER_OF_SAMPLES));
//			leftPanel.updateInfoRete(info);
//			
//			///LANCIO
////			leftPanel.updateInfoLancio(o.getMap().get(bestThrow));
//			leftPanel.updateInfoLancio(info);
//			
//			///PARABOLA
////			rightPanel.clearPanel();
//			rightPanel.drawAxis();
//			rightPanel.drawTarget(o.getMap().get(bestThrow).get(MyConstants.X_TARGET_INDEX), o.getMap().get(bestThrow).get(MyConstants.Y_TARGET_INDEX));
//			//o.getMap().get(bestThrow).get(MyConstants.X_TARGET_INDEX), o.getMap().get(bestThrow).get(MyConstants.Y_TARGET_INDEX)
//			rightPanel.drawParabola(o.getMap().get(bestThrow).get(MyConstants.ANGOLO_INDEX), o.getMap().get(bestThrow).get(MyConstants.VELOCITA_INDEX));
			
//			System.out.println("ciao");
//			mainPanel.revalidate();
//			mainPanel.repaint();
	
			////////////////////////////////
			
//			 Map<Integer,ArrayList<Double>> mappa = o.getMap();
//			 for (int i=0; i<EnvConstant.NUMBER_OF_SAMPLES; i++)
//			 {
//				 System.out.println("------ LANCIO: "+i +" ------");
//				 ArrayList<Double> array = mappa.get(i);
//				 System.out.println("X_OBJ: "+fmt6d.format(array.get(0)));
//				 System.out.println("Y_OBJ: "+fmt6d.format(array.get(1)));
//				 System.out.println("Y_TIRO: "+fmt6d.format(array.get(2)));
//				 System.out.println("ANGOLO: "+fmt6d.format(array.get(3)));
//				 System.out.println("VELOCITA': "+fmt6d.format(array.get(4)));
//				 System.out.println("FORZA: "+fmt6d.format(array.get(7)));
//				 System.out.println("TEMPO: "+fmt6d.format(array.get(8)));
//				 System.out.println("ACCELERAZIONE: "+fmt6d.format(array.get(9)));
//				 System.out.println("MASSA: "+fmt6d.format(array.get(10)));
//				 System.out.println("ERRORE: "+fmt6d.format(array.get(5)));
//				 //System.out.println("FITNESS: "+fmt6d.format(array.get(6)));
//			 }
//			 System.out.println("GENERAZIONE: "+o.getGeneration());
//			 System.out.println("ERRORE TOTALE: "+fmt6d.format(mappa.get(EnvConstant.NUMBER_OF_SAMPLES).get(8)));
//			 System.out.println("FITNESS TOTALE: "+fmt6d.format(mappa.get(EnvConstant.NUMBER_OF_SAMPLES).get(6)));
//			 System.out.println("FITNESS VECCHIA: "+fmt6d.format(mappa.get(EnvConstant.NUMBER_OF_SAMPLES).get(9)));
//			 System.out.println();
	   }
	   
		@Override
		public void actionPerformed(ActionEvent e)
		{
			 EnvConstant.TYPE_OF_SIMULATION = EnvConstant.SIMULATION_FROM_CLASS;
			 EnvConstant.FORCE_RESTART = false;
			 EnvConstant.STOP_EPOCH = false;
			 JButton p = (JButton) e.getSource();
			 
			 if (p.getActionCommand().equals("Start")) 
			 {
//				 System.out.println("start");
				 start = true;
				 getRightPanel().setDraw(true);
//				 autodraw = true;
				 EnvConstant.FORCE_RESTART = false;
				 mainPanel.startProcessAsync();
				 
				 getLeftPanel().getOptionsPanel().getStartBtn().setText("Stop");
			 }
			 
			 else if (p.getActionCommand().equals("Stop")) 
			 {
//				 System.out.println("stop");
				 start = false;
//				 autodraw = true;
//				 EnvConstant.FORCE_RESTART = false;
				 mainPanel.stopProcessAsync();
				 
				 getLeftPanel().getOptionsPanel().getStartBtn().setText("Start");
			 }
			 
			 else if (p.getActionCommand().equals("Auto-draw: OFF")) 
			 {
				 autodraw = false;
				 
				 getLeftPanel().getOptionsPanel().getAutodrawBtn().setText("Auto-draw: ON");
			 } 
			 
			 else if (p.getActionCommand().equals("Auto-draw: ON")) 
			 {
				 autodraw = true;
				 
				 getLeftPanel().getOptionsPanel().getAutodrawBtn().setText("Auto-draw: OFF");
			 } 
		}
		
		public boolean storeBestNet(Organism o)
		{
			boolean success = false;
			String nomefile = "generation_" + o.getGeneration();
			try 
			{
				File file = new File(MyConstants.RESULTS_DIR + nomefile);

				success = true;	//FILE CREATO O ESISTENTE
		        
		        FileWriter fw = new FileWriter(file);
		        BufferedWriter bw = new BufferedWriter(fw);
		        
		        String infoFile = getInfoToWrite(o);
		        
		        bw.write(infoFile);
		        
		        bw.flush();
		        bw.close();	
			} 
			catch (IOException e) 
	        {
				e.printStackTrace();
			}
			return success;
		}
		
		public ArrayList<Double> readNet(int generazione, int lancio)
		{
			ArrayList<Double> array = new ArrayList<Double> ();
			for (int i=0; i<14; i++)
				array.add(0.0);
			
			String nomefile = "generation_" + generazione;
			try 
			{
				File file = new File(MyConstants.RESULTS_DIR + nomefile);

		        FileReader fr = new FileReader(file);
		        BufferedReader br = new BufferedReader(fr);
		        
		        String currentLine;
		        while ((currentLine = br.readLine()) != null)
		        {
					if (currentLine.equals("------ GENERAZIONE: "+ generazione +" ------"))
					{
						String errore [] = br.readLine().split(" ");
						String fitnesst [] = br.readLine().split(" ");
						String fitnessv [] = br.readLine().split(" ");
						String bestThrow [] = br.readLine().split(" ");
							
						double err = Double.parseDouble(errore[errore.length-1]);
						double fitt = Double.parseDouble(fitnesst[fitnesst.length-1]);
						double fitv = Double.parseDouble(fitnessv[fitnessv.length-1]);
						double best = Double.parseDouble(bestThrow[bestThrow.length-1]);
						array.set(MyConstants.ERRORE_TOTALE_INDEX, err);
						array.set(MyConstants.FITNESS_TOTALE_INDEX, fitt);
						array.set(MyConstants.FITNESS_VECCHIA_INDEX, fitv);			
						array.set(MyConstants.LANCIO_MIGLIORE_INDEX, best);		
					}
					else if (currentLine.equals("------ LANCIO: "+ lancio +" ------"))
					{
						String x_target [] = br.readLine().split(" ");
						String y_target [] = br.readLine().split(" ");
						String y_tiro [] = br.readLine().split(" ");
						String angolo [] = br.readLine().split(" ");
						String velocita [] = br.readLine().split(" ");
						String forza [] = br.readLine().split(" ");
						String tempo [] = br.readLine().split(" ");
						String accelerazione [] = br.readLine().split(" ");
						String massa [] = br.readLine().split(" ");
						String errore [] = br.readLine().split(" ");
						
						double x_tgt = Double.parseDouble(x_target[x_target.length-1]);
						double y_tgt = Double.parseDouble(y_target[y_target.length-1]);
						double y_lancio = Double.parseDouble(y_tiro[y_tiro.length-1]);
						double ang = Double.parseDouble(angolo[angolo.length-1]);
						double v = Double.parseDouble(velocita[velocita.length-1]);
						double f = Double.parseDouble(forza[forza.length-1]);
						double t = Double.parseDouble(tempo[tempo.length-1]);
						double acc = Double.parseDouble(accelerazione[accelerazione.length-1]);
						double m = Double.parseDouble(massa[massa.length-1]);
						double err = Double.parseDouble(errore[errore.length-1]);
						array.set(MyConstants.X_TARGET_INDEX, x_tgt);
						array.set(MyConstants.Y_TARGET_INDEX, y_tgt);
						array.set(MyConstants.Y_LANCIO_INDEX, y_lancio);		
						array.set(MyConstants.ANGOLO_INDEX, ang);
						array.set(MyConstants.VELOCITA_INDEX, v);
						array.set(MyConstants.FORZA_INDEX, f);		
						array.set(MyConstants.TEMPO_INDEX, t);
						array.set(MyConstants.ACCELERAZIONE_INDEX, acc);
						array.set(MyConstants.MASSA_INDEX, m);	
						array.set(MyConstants.ERRORE_INDEX, err);	
					}
					
		        }
		        br.close();	
			} 
			catch (IOException e) 
	        {
				e.printStackTrace();
			}
			
			return array;
		}
		
		public ArrayList<Double> readNet(String generazione, String lancio)
		{
			ArrayList<Double> array = new ArrayList<Double> ();
			for (int i=0; i<MyConstants.INFO_RETE_SIZE; i++)
				array.add(0.0);
			
			String nomefile = "generation_" + generazione;
			String lancioA = lancio;
			try 
			{
//				File file = new File(MyConstants.RESULTS_DIR + nomefile);
			
		        FileReader fr = new FileReader(MyConstants.RESULTS_DIR + nomefile);
	        	BufferedReader br = new BufferedReader(fr);
		        
		        String currentLine;
		        currentLine = br.readLine();
				if (currentLine.equals("------ GENERAZIONE: "+ generazione +" ------"))
				{
					String errore [] = br.readLine().split(" ");
					String fitnesst [] = br.readLine().split(" ");
					String fitnessv [] = br.readLine().split(" ");
					String bestThrow [] = br.readLine().split(" ");
						
					double err = Double.parseDouble(errore[errore.length-1]);
					double fitt = Double.parseDouble(fitnesst[fitnesst.length-1]);
					double fitv = Double.parseDouble(fitnessv[fitnessv.length-1]);
					double best = Double.parseDouble(bestThrow[bestThrow.length-1]);
					array.set(MyConstants.ERRORE_TOTALE_INDEX, err);
					array.set(MyConstants.FITNESS_TOTALE_INDEX, fitt);
					array.set(MyConstants.FITNESS_VECCHIA_INDEX, fitv);			
					array.set(MyConstants.LANCIO_MIGLIORE_INDEX, best);		
					if (lancio.equals("Best")) lancioA = (int)(best+1) +"";
				}
		        while ((currentLine = br.readLine()) != null)
		        {
					if (currentLine.equals("------ LANCIO: "+ lancioA +" ------"))
					{
						String x_target [] = br.readLine().split(" ");
						String y_target [] = br.readLine().split(" ");
						String y_tiro [] = br.readLine().split(" ");
						String angolo [] = br.readLine().split(" ");
						String velocita [] = br.readLine().split(" ");
						String errore [] = br.readLine().split(" ");
						String forza [] = br.readLine().split(" ");
						String tempo [] = br.readLine().split(" ");
						String accelerazione [] = br.readLine().split(" ");
						String massa [] = br.readLine().split(" ");
	
						
						double x_tgt = Double.parseDouble(x_target[x_target.length-1]);
						double y_tgt = Double.parseDouble(y_target[y_target.length-1]);
						double y_lancio = Double.parseDouble(y_tiro[y_tiro.length-1]);
						double ang = Double.parseDouble(angolo[angolo.length-1]);
						double v = Double.parseDouble(velocita[velocita.length-1]);
						double err = Double.parseDouble(errore[errore.length-1]);
						double f = Double.parseDouble(forza[forza.length-1]);
						double t = Double.parseDouble(tempo[tempo.length-1]);
						double acc = Double.parseDouble(accelerazione[accelerazione.length-1]);
						double m = Double.parseDouble(massa[massa.length-1]);
						
						array.set(MyConstants.X_TARGET_INDEX, x_tgt);
						array.set(MyConstants.Y_TARGET_INDEX, y_tgt);
						array.set(MyConstants.Y_LANCIO_INDEX, y_lancio);		
						array.set(MyConstants.ANGOLO_INDEX, ang);
						array.set(MyConstants.VELOCITA_INDEX, v);
						array.set(MyConstants.FORZA_INDEX, f);		
						array.set(MyConstants.TEMPO_INDEX, t);
						array.set(MyConstants.ACCELERAZIONE_INDEX, acc);
						array.set(MyConstants.MASSA_INDEX, m);	
						array.set(MyConstants.ERRORE_INDEX, err);	
					}
					
		        }
		        br.close();
			} 
			catch (IOException e) 
		    {
				e.printStackTrace();
		    }
			
			return array;
		}
		
		public String getInfoToWrite(Organism o)
		{
			Map<Integer,ArrayList<Double>> mappa = o.getMap();
	        
			String infoLanci = "";
			for (int i=0; i<EnvConstant.NUMBER_OF_SAMPLES; i++)
			{
				ArrayList<Double> array = mappa.get(i);
				infoLanci += "------ LANCIO: "+(i+1) +" ------" + "\n" + 
						"X_OBJ: "+fmt6d.format(array.get(MyConstants.X_TARGET_INDEX)) + "\n" + 
						"Y_OBJ: "+fmt6d.format(array.get(MyConstants.Y_TARGET_INDEX)) + "\n" + 
						"Y_TIRO: "+fmt6d.format(array.get(MyConstants.Y_LANCIO_INDEX)) + "\n" + 
						"ANGOLO: "+fmt6d.format(array.get(MyConstants.ANGOLO_INDEX)) + "\n" + 
						"VELOCITA': "+fmt6d.format(array.get(MyConstants.VELOCITA_INDEX)) + "\n" + 
						"ERRORE: "+fmt6d.format(array.get(MyConstants.ERRORE_INDEX)) + "\n" +
						"FORZA: "+fmt6d.format(array.get(MyConstants.FORZA_INDEX)) + "\n" + 
						"TEMPO: "+fmt6d.format(array.get(MyConstants.TEMPO_INDEX)) + "\n" + 
						"ACCELERAZIONE: "+fmt6d.format(array.get(MyConstants.ACCELERAZIONE_INDEX)) + "\n" + 
						"MASSA: "+fmt6d.format(array.get(MyConstants.MASSA_INDEX)) + "\n";
	
			}				
			
	        String infoRete = "------ GENERAZIONE: "+o.getGeneration() +" ------" + "\n" +
	        		"ERRORE TOTALE: "+fmt6d.format(mappa.get(EnvConstant.NUMBER_OF_SAMPLES).get(MyConstants.ERRORE_TOTALE_INDEX)) + "\n" + 
	        		"FITNESS TOTALE: "+fmt6d.format(mappa.get(EnvConstant.NUMBER_OF_SAMPLES).get(MyConstants.FITNESS_TOTALE_INDEX)) + "\n" + 
	        		"FITNESS VECCHIA: "+fmt6d.format(mappa.get(EnvConstant.NUMBER_OF_SAMPLES).get(MyConstants.FITNESS_VECCHIA_INDEX)) + "\n" +
	        		"LANCIO MIGLIORE: "+ (mappa.get(EnvConstant.NUMBER_OF_SAMPLES).get(MyConstants.LANCIO_MIGLIORE_INDEX).intValue()) + "\n";
	        
	        String infoFile = infoRete + infoLanci;
	        
	        return infoFile;
		}
		
		public boolean getStart()
		{
			return start;
		}
		
		public boolean getAutodraw()
		{
			return autodraw;
		}
}
