   package gui;




   import java.awt.*;
   import java.awt.event.*;
   import java.awt.geom.*;
   import java.awt.image.*;
   import javax.swing.*;
   import javax.swing.event.*;
   import javax.swing.border.*;
   import java.util.*;
   import java.lang.*;







   import javax.swing.text.*;
   import jneat.*;
   import jNeatCommon.*;


   import java.text.*;
   import jGraph.*;
   import java.lang.reflect.*;
   import log.*;
import newGui.MainFrame;
import newGui.MyConstants;
import newGui.MainPanel;



public class Generation extends JPanel implements ActionListener , ItemListener
   {
   
	  private JFrame f1;

	  
	  Container contentPane;
	  protected HistoryLog   logger;
   
   
	  JPanel pmain;
   
   
	  JPanel p2;       // pannello comandi
	  JPanel p3;       // pannello grafico
   
   
   
   
	  JPanel p3_text;
	  JPanel p3_graph;
	  JPanel p3_curve;
   
   
   
	  Vector    v1_fitness; 
	  Vector    v1_fitness_win; 
	  Vector    v1_species; 
   
   
   
   
	  JButton b1;
	  JButton b2;
	  JButton b3;
   
   //	  JButton b5;
	  
   
	  JButton b9;
   
	  ButtonGroup ck_group;
   
	  JRadioButton ck1;
	  JRadioButton ck2;
	  JRadioButton ck3;
   
	  JTextPane textPane2;
	  JScrollPane paneScroll2;
   
   
	  chartXY mappa_graph;
	  chartXY mappa_graph_curr;
   
	  chartXY mappa_curve;
   
   
   
   
	  JSplitPane paneSplit2;
   
   // dynamic definition for fitness
	  Class  Class_fit;
	  Object ObjClass_fit;
	  Method Method_fit;
	  Object ObjRet_fit;
   
   
   // dynamic definition for input class
	  Class  Class_inp;
	  Object ObjClass_inp;
	  Method Method_inp;
	  Object ObjRet_inp;
   
   // dynamic definition for target class
	  Class  Class_tgt;
	  Object ObjClass_tgt;
	  Method Method_tgt;
	  Object ObjRet_tgt;
   
   
	  private   volatile Thread  lookupThread; 
   
   
	  final static String[] My_styles = 
	  {"normal", "italic", "bold", "bold-italic"}; 
   
   
   
   
   /**
   * pan1 constructor comment.
   */
	   public Generation(JFrame _f) 
	  {

		   
		 GridBagLayout gbl;
		 GridBagConstraints limiti;
	  
		 logger = new HistoryLog();
	  
		 f1 = _f;
	  
	  
	  
		 mappa_graph = new chartXY();
		 mappa_graph_curr = new chartXY();
		 mappa_curve = new chartXY();
	  
	  
		 p2 = new JPanel();
	  
	  
	  
		 p3 = new JPanel();
	  
	  
		 p3_curve = new JPanel();
		 p3_graph = new JPanel();
		 p3_text = new JPanel();
	  
		 p3.setLayout(new BorderLayout());
	  
	  
	  
	  
	  
	  
	  
	  
		 b1 = new JButton(" start ");
		 b1.addActionListener(this);
	  
		 b2 = new JButton(" start from last");
		 b2.addActionListener(this);
	  
		 b3 = new JButton(" stop ");
		 b3.addActionListener(this);
	  
	  //		 b5 = new JButton(" clear ");
	  //		 b5.addActionListener(this);
	  
	  
		 b9 = new JButton(" E X I T ");
		 b9.addActionListener(this);
	  
	  
		 Font fc = new Font("Dialog", Font.BOLD,12);
		 b1.setFont(fc);
		 b2.setFont(fc);
		 b3.setFont(fc);

	  //		 b5.setFont(fc);
		 b9.setFont(fc);
	  
	  
	  
	  
		 ck_group = new ButtonGroup();
		 ck1 = new JRadioButton("text output", true);
		 ck1.setActionCommand("text output");
	  	 ck1.addItemListener(this);

		 ck2 = new JRadioButton("graph champion", false);
		 ck2.setActionCommand("graph champion");
	 	 ck2.addItemListener(this);
	  
		 ck3 = new JRadioButton("plot Error", false);
		 ck3.setActionCommand("plot Error");
	  	 ck3.addItemListener(this);

		 ck_group.add(ck1);
		 ck_group.add(ck2);
		 ck_group.add(ck3);
	  
		 p2.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder("Command options"), 
			BorderFactory.createEmptyBorder(10, 2, 2, 2))); 
	  
		 gbl = new GridBagLayout();
		 limiti = new GridBagConstraints();
		 p2.setLayout(gbl);
	  
		 buildConstraints(limiti, 0, 1, 1, 2, 100, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b1, limiti);
		 p2.add(b1);
	  
		 buildConstraints(limiti, 0, 3, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b2, limiti);
		 p2.add(b2);
	  
		 buildConstraints(limiti, 0, 5, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(b3, limiti);
		 p2.add(b3);
	  
	  
		 buildConstraints(limiti, 0, 7, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(ck1, limiti);
		 p2.add(ck1);
	  
		 buildConstraints(limiti, 0, 9, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(ck2, limiti);
		 p2.add(ck2);
	  
		 buildConstraints(limiti, 0, 11, 1, 2, 0, 5);
		 limiti.anchor = GridBagConstraints.NORTH;
		 limiti.fill = GridBagConstraints.BOTH;
		 gbl.setConstraints(ck3, limiti);
		 p2.add(ck3);
	  
		 buildConstraints(limiti, 0, 13, 1, 2, 0, 70);
		 limiti.anchor = GridBagConstraints.SOUTH;
		 limiti.fill = GridBagConstraints.HORIZONTAL;
		 limiti.ipady = 20;
		 gbl.setConstraints(b9, limiti);
		 p2.add(b9);
	  
		 textPane2 = new JTextPane();
		 textPane2.setEditable(true);
		 textPane2.setBackground(new Color(255, 252, 242));
		 textPane2.setText("");
	  
	  
	  
	  
		 paneScroll2 = new JScrollPane(textPane2);
		 paneScroll2.setVerticalScrollBarPolicy(
			paneScroll2.VERTICAL_SCROLLBAR_AS_NEEDED); 
		 paneScroll2.setBorder(
			BorderFactory.createCompoundBorder(
			BorderFactory.createEmptyBorder(2, 2, 2, 2), 
			BorderFactory.createEtchedBorder())); 
	  
		 setStyleNew(textPane2);
	  
		 p3.setMinimumSize(new Dimension(100, 100)); //100,100
		 p3.setPreferredSize(new Dimension(400, 400)); //300,200
	  
	  
	  
	  
		 p3_text.setLayout(new BorderLayout());
		 p3_text.add(paneScroll2, BorderLayout.CENTER);
	  
		 p3_graph.setLayout(new GridLayout(1,2));
		 p3_graph.add(mappa_graph_curr);
		 p3_graph.add(mappa_graph);
	  
		 p3_curve.setLayout(new BorderLayout());
		 p3_curve.add(mappa_curve, BorderLayout.CENTER);
	  
	  
		 p3.validate();
		 p3.repaint();
	  
	  
		 pmain = new JPanel();
		 gbl = new GridBagLayout();
		 pmain.setLayout(gbl);
	  
		 limiti = new GridBagConstraints();
		 buildConstraints(limiti, 0, 0, 1, 5, 0, 100);
		 limiti.anchor = GridBagConstraints.WEST;
		 limiti.fill = GridBagConstraints.VERTICAL;
		 pmain.add(p2); 
		 gbl.setConstraints(p2, limiti);
	  
		 limiti = new GridBagConstraints();
		 buildConstraints(limiti, 1, 1, 2, 5, 100, 0);
		 limiti.anchor = GridBagConstraints.WEST;
		 limiti.fill = GridBagConstraints.BOTH;
		 pmain.add(p3);
		 gbl.setConstraints(p3, limiti);
	  
	  // interface to main method of this class
		 Container contentPane = f1.getContentPane();
		 BorderLayout bl = new BorderLayout();
		 contentPane.setLayout(bl);
		 contentPane.add(pmain, BorderLayout.CENTER);
		 contentPane.add(logger, BorderLayout.SOUTH);
	  
		 EnvConstant.OP_SYSTEM = System.getProperty("os.name");
		 EnvConstant.OS_VERSION = System.getProperty("os.version");
		 EnvConstant.JNEAT_DIR = System.getProperty("user.dir");
		 EnvConstant.OS_FILE_SEP = System.getProperty("file.separator");
	  
	  
		 initAllMap();
	  
	  }
   /**
   * Starts the application.
   * @param args an array of command-line arguments
   */
	   public static void main(java.lang.String[] args) {
	  
		 JFrame jp = null;
		 Generation pn1 = null;
	     MainFrame frame = null;
	  
		 try 
		 {
			jp = new JFrame("  Generation of   n. e. a. t.  ");
			pn1 = new Generation(jp); 
		 
		 //	  jp.getContentPane().add(pn1); 
			jp.addWindowListener(
				   new java.awt.event.WindowAdapter() 
				  {
					  public void windowClosing(WindowEvent e) 
					 {
						System.exit(0);
					 }
				  }
			   );
		 
			jp.pack();
			jp.setSize(800, 600);
			jp.setVisible(true);
		 } 
		 
			 catch (Exception ex) 
			{
			   System.err.println("ERRORE");
			}
	  
	  
	  
	  // Insert code to start the application here.
	  }
   
	   public void buildConstraints(
	   GridBagConstraints gbc, 
	   int gx, 
	   int gy, 
	   int gw, 
	   int gh, 
	   int wx, 
	   int wy) 
	  {
		 gbc.gridx = gx;
		 gbc.gridy = gy;
		 gbc.gridwidth = gw;
		 gbc.gridheight = gh;
		 gbc.weightx = wx;
		 gbc.weighty = wy;
	  }
   
   

public void itemStateChanged(ItemEvent e) {

	JRadioButton cb = (JRadioButton) e.getItem();
	String ckx = cb.getActionCommand();

	if (!(EnvConstant.RUNNING)) 
	{

		if (ckx.equalsIgnoreCase("text output")) 
		{
			p3.removeAll();
			p3.add(p3_text, BorderLayout.CENTER);
			p3_text.repaint();

		}

		if (ckx.equalsIgnoreCase("graph champion")) 
		{

			p3.removeAll();
			p3.add(p3_graph, BorderLayout.CENTER);

			mappa_graph.repaint();
			mappa_graph_curr.repaint();
			p3_graph.repaint();

		}

		if (ckx.equalsIgnoreCase("plot Error")) 
		{
			p3.removeAll();
			p3.add(p3_curve, BorderLayout.CENTER);
			mappa_curve.repaint();
			p3_curve.repaint();

		}

		p3.validate();
		p3.repaint();

	}

}
	   
   
   
	   public void startProcess() 
	  {
	  
	  
	  
		 try 
		 {
			logger.sendToLog(" generation:   for this experiment has :");
			if ( EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_CLASS)
			   logger.sendToLog(" generation:      data coming from class");
		 
			if ( EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_FILE)
			   logger.sendToLog(" generation:      data coming from file");
		 
			logger.sendToLog(" generation:      data input  is  "+ EnvConstant.DATA_INP);
			logger.sendToLog(" generation:      data output is  "+ EnvConstant.DATA_OUT);
			logger.sendToLog(" generation:      fitness class   "+ EnvConstant.CLASS_FITNESS);
		 //logger.sendToLog(" generation:      unit input are  "+ EnvConstant.NR_UNIT_INPUT);
		 //logger.sendToLog(" generation:      unit output are "+ EnvConstant.NR_UNIT_OUTPUT);
		 
		 
			if (( EnvConstant.TYPE_OF_START  == EnvConstant.START_FROM_GENOME) &&  (!EnvConstant.FORCE_RESTART ))
			   logger.sendToLog(" generation:      start from genome : "+EnvConstant.NAME_GENOMEA);
		 
			if (( EnvConstant.TYPE_OF_START  == EnvConstant.START_FROM_NEW_RANDOM_POPULATION) &&  (!EnvConstant.FORCE_RESTART ))
			{
			   logger.sendToLog(" generation:      start from rnd population : "+EnvConstant.NAME_CURR_POPULATION);
			   logger.sendToLog(" generation:                       max unit : "+EnvConstant.NR_UNIT_MAX);
			   logger.sendToLog(" generation:                      recursion : "+EnvConstant.RECURSION);
			   logger.sendToLog(" generation:                    % Prob link : "+EnvConstant.PROBABILITY_OF_CONNECTION);
			   logger.sendToLog(" generation:      prefix genome random      : "+EnvConstant.PREFIX_GENOME_RANDOM);
			}
		 
			if ( EnvConstant.TYPE_OF_START  == EnvConstant.START_FROM_OLD_POPULATION)
			   logger.sendToLog(" generation:      start from old population : "+EnvConstant.NAME_CURR_POPULATION);
		 
			if (EnvConstant.FORCE_RESTART)
			   logger.sendToLog(" generation:      start temporary forced from last population : "+EnvConstant.NAME_CURR_POPULATION);
		 
		 
			logger.sendToLog(" generation:      number of epochs       : "+ EnvConstant.NUMBER_OF_EPOCH);
			logger.sendToLog(" generation:      prefix of species file : "+ EnvConstant.PREFIX_SPECIES_FILE);
			logger.sendToLog(" generation:      prefix of winner  file : "+ EnvConstant.PREFIX_WINNER_FILE);
		 
		 
		 
			if ( EnvConstant.ACTIVATION_PERIOD == EnvConstant.AUTOMATIC)
			   logger.sendToLog(" generation:      number of activaction A(t) = f(depth(net)) (automatic)");
			if ( EnvConstant.ACTIVATION_PERIOD == EnvConstant.MANUAL)
			   logger.sendToLog(" generation:      number of activaction A(t) = "+EnvConstant.ACTIVATION_TIMES+" (manual)");
		 
		 
			boolean  rc1  = startNeat();
		 
		 
		 } 
		 
			 catch (Throwable e1) 
			{
			   logger.sendToLog(" generation: error during generation.startProcess() :"+e1);
			}
	  
	  
	  
	  
	  
	  //	}
	  
	  
	  
	  
	  }
   
   
   
   
   
   
   
   
   
   
   
   
	   public void startProcessAsync()
	  {
		 Runnable lookupRun = 
			 new Runnable() 
			{
				public void run() {
			   //				  getSessionInformation();	
				  logger.sendToLog(" generation: start reading session file ->"+EnvRoutine.getJneatSession());
				  EnvRoutine.getSession();
				  logger.sendToLog(" generation: end read session file");
			   
				  startProcess();
			   
			   }
			};
		 lookupThread = new Thread(lookupRun," looktest" );
		 lookupThread.start();  
	  }                                                                                                                                                                                           
   
   
   
   
   
   
	   public void actionPerformed(ActionEvent e) 
	  {
	  
		 EnvConstant.FORCE_RESTART = false;
		 EnvConstant.STOP_EPOCH = false;
		 String name = null;
		 String tmp1 = null;
		 String tmp2 = null;
	  
		 String ckx = ck_group.getSelection().getActionCommand();
	  //	  " update screen "
	  
		 JButton Pulsante = (JButton) e.getSource();
	  
		 if (e.getActionCommand().equals(" E X I T ")) 
		 {
			System.exit(0);
		 } 
	  
	  
		 if (e.getActionCommand().equals(" stop ")) 
		 {
			logger.sendToStatus(" generation: wait...");
			EnvConstant.STOP_EPOCH = true;
			logger.sendToLog(" generation: request of *interrupt* .....");
			logger.sendToStatus("READY");		
		 } 
		 
		 else if (e.getActionCommand().equals(" start ")) 
		 {
		 
			logger.sendToStatus(" generation: wait...");
			initAllMap();
			EnvConstant.FORCE_RESTART = false;
			startProcessAsync();
			logger.sendToStatus("READY");		
		 } 
		 
		 
		 else if (e.getActionCommand().equals(" start from last")) 
		 {
		 
			logger.sendToStatus(" generation: wait...");
			initAllMap();
			EnvConstant.FORCE_RESTART = true;
			logger.sendToStatus(" generation: emergency restart ( from last generation)");
			startProcessAsync();
			logger.sendToStatus("READY");		
		 
		 }
	  
	  
	  
	  }
   
   
   
	   public boolean startNeat()
	  {
		 boolean rc = false;
		 String curr_name_pop_specie = null;
	  
		 String mask5 = "00000";
		 DecimalFormat fmt5 = new DecimalFormat(mask5);
	  
	  
		 Population u_pop = null;
		 Genome u_genome = null;
		 StringTokenizer st;
		 String curword;
		 String xline;
		 String fnamebuf;
		 IOseq xFile;
		 int id;
		 int expcount = 0;
		 double  u_prb_link = 0.5;
		 boolean u_recurrent = false;
		 int u_max_unit = 0;
		 int u_inp_unit = 0;
		 int u_out_unit = 0;
		 int gen = 0;
		 String xnome = null;
	  
	  
	  
	  
	  
	  
	  // imposta classe dinamica per fitness 
	  //
		 try
		 {
		 
		 
		 //System.out.println(EnvConstant.CLASS_FITNESS);
			Class_fit = evo_fit.class; //Class.forName(EnvConstant.CLASS_FITNESS);
			//System.out.println(Class_fit);
			//System.out.println("ciao");
			ObjClass_fit = Class_fit.newInstance();
		 
		 // read max Fitness possible		
			Method_fit = Class_fit.getMethod("getMaxFitness", null);
			ObjRet_fit = Method_fit.invoke(ObjClass_fit, null);
			EnvConstant.MAX_FITNESS =  Double.parseDouble(ObjRet_fit.toString());
		 //System.out.println(EnvConstant.MAX_FITNESS);
		 
		 
		 
			if ( EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_CLASS )
			{
			
			// data input
			//
			   Class_inp = evo_in.class; //Class.forName(EnvConstant.DATA_INP);
			   ObjClass_inp = Class_inp.newInstance();
			   Method_inp = Class_inp.getMethod("getNumUnit", null);
			   ObjRet_inp = Method_inp.invoke(ObjClass_inp, null);
			   EnvConstant.NR_UNIT_INPUT = Integer.parseInt(ObjRet_inp.toString());
			   //System.out.println(EnvConstant.NR_UNIT_INPUT);
			
			// number of samples
			//
			   Method_inp = Class_inp.getMethod("getNumSamples", null);
			   ObjRet_inp = Method_inp.invoke(ObjClass_inp, null);
			   EnvConstant.NUMBER_OF_SAMPLES = Integer.parseInt(ObjRet_inp.toString());
			   //System.out.println(EnvConstant.NUMBER_OF_SAMPLES);
			
			// data output
			//
			
			
			   Class_tgt = evo_out.class; //Class.forName(EnvConstant.DATA_OUT);
			   ObjClass_tgt = Class_tgt.newInstance();
			   Method_tgt = Class_tgt.getMethod("getNumUnit", null);
			   ObjRet_tgt = Method_tgt.invoke(ObjClass_tgt, null);
			   EnvConstant.NR_UNIT_OUTPUT = Integer.parseInt(ObjRet_tgt.toString());
			   //System.out.println(EnvConstant.NR_UNIT_OUTPUT);
			}
		 
		 
		 
		 
			if ( EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_FILE )
			{
			// legge il primo record e conta il numero di token nella prima riga che trova
			// in cui non compaia come primi 2 caratteri '//'
			   xnome = EnvRoutine.getJneatFile(EnvConstant.DATA_INP);
			   EnvConstant.NR_UNIT_INPUT = getNumberUnitForFile(xnome);
			
			// legge quanti record ci sono in cui il primo carattere non sia '//'
			// e restituisce il numero di tali record in input
			   xnome = EnvRoutine.getJneatFile(EnvConstant.DATA_INP);
			
			   EnvConstant.NUMBER_OF_SAMPLES  = EnvRoutine.getNumberSamplesForFile(xnome);
			//			   EnvConstant.NUMBER_OF_SAMPLES  = getNumberSamplesForFile(xnome);
			
			
			// legge il primo record e conta il numero di token nella prima riga che trova
			// in cui non compaia come primi 2 caratteri '//'
			   xnome = EnvRoutine.getJneatFile(EnvConstant.DATA_OUT);
			   EnvConstant.NR_UNIT_OUTPUT = getNumberUnitForFile(xnome);
			
			// legge quanti record ci sono in cui il primo carattere non sia '//'
			// e restituisce il numero di tali record in output
			   xnome = EnvRoutine.getJneatFile(EnvConstant.DATA_OUT);
			
			
			//			   int nout = getNumberSamplesForFile(xnome);
			   int nout = EnvRoutine.getNumberSamplesForFile(xnome);
			
			
			
			   if ( EnvConstant.NUMBER_OF_SAMPLES  != nout)
			   {
				  System.out.print("\n Number of input samples (="+EnvConstant.NUMBER_OF_SAMPLES);
				  System.out.print(") is different to number of target samples(="+nout+")");
				  System.out.print("\n correct files and rerun;  \n\t bye");
				  System.exit(8);
			   }
			}
		 
		 
			logger.sendToLog(" generation:  real    okay setting size inp ->"+EnvConstant.NR_UNIT_INPUT);
			logger.sendToLog(" generation:  real    okay setting size out ->"+EnvConstant.NR_UNIT_OUTPUT);
			logger.sendToLog(" generation:  real    okay setting sample   ->"+EnvConstant.NUMBER_OF_SAMPLES);
		 
		 
		 
		 
		 }
			 catch(Exception ed) 
			{
			   logger.sendToLog(" generation: error in startNeat() "+ed);
			   System.out.println("errore");
			}
	  
	  
	  
		 try 
		 {
		 
		 
		 
			logger.sendToLog(" generation:      read parameter file of neat ...");
		 
			Neat u_neat = new Neat();
			u_neat.initbase();
			rc = u_neat.readParam(EnvRoutine.getJneatParameter());
		 
			if (!rc)
			{
			   logger.sendToLog(" generation: error in read "+EnvRoutine.getJneatParameter());
			   return false;
			}   
		 
		 
			logger.sendToLog(" generation:   ok! "+EnvRoutine.getJneatParameter());
		 //
		 // gestisce start da genoma unico
		 //
			if ((EnvConstant.TYPE_OF_START  == EnvConstant.START_FROM_GENOME)  && (!EnvConstant.FORCE_RESTART ))
			{
			   xFile = new IOseq(EnvRoutine.getJneatFileData(EnvConstant.NAME_GENOMEA));
			   rc = xFile.IOseqOpenR();
			   if (!rc)
			   {
				  logger.sendToLog(" generation:   error open "+EnvRoutine.getJneatFileData(EnvConstant.NAME_GENOMEA));
				  return false;
			   }
			
			   logger.sendToLog(" generation:      open file genome "+EnvRoutine.getJneatFileData(EnvConstant.NAME_GENOMEA)+"...");
			   xline = xFile.IOseqRead();
			   st = new StringTokenizer(xline);
			//skip 
			   curword = st.nextToken();
			//id of genome can be readed
			   curword = st.nextToken();
			   id = Integer.parseInt(curword);
			   logger.sendToLog(" generation:  ok! created genome id -> "+id);
			   u_genome = new Genome(id, xFile);
			
			
			}
		 
		 //
		 // gestisce start da popolazione random
		 //
			if ( EnvConstant.TYPE_OF_START  == EnvConstant.START_FROM_NEW_RANDOM_POPULATION && (!EnvConstant.FORCE_RESTART ))
			{
			   logger.sendToLog(" generation:      cold start from random population.. ");
			   u_prb_link = EnvConstant.PROBABILITY_OF_CONNECTION;
			   u_recurrent = EnvConstant.RECURSION;
			   u_max_unit = EnvConstant.NR_UNIT_MAX;
			   u_inp_unit = EnvConstant.NR_UNIT_INPUT;
			   u_out_unit = EnvConstant.NR_UNIT_OUTPUT;
			}
		 
		 
		 //
		 // gestisce start da popolazione esistente
		 //
			if ((EnvConstant.TYPE_OF_START == EnvConstant.START_FROM_OLD_POPULATION) || (EnvConstant.FORCE_RESTART))
			{
			   logger.sendToLog(" generation:      warm start from old population -> "+EnvRoutine.getJneatFileData(EnvConstant.NAME_CURR_POPULATION));
			}
		 
		 
		 //			EnvConstant.SERIAL_WINNER = 0;
			EnvConstant.SERIAL_SUPER_WINNER = 0;
			EnvConstant.MAX_WINNER_FITNESS = 0;
		 
		 // 1.6.2002 : reset pointer to first champion
			EnvConstant.CURR_ORGANISM_CHAMPION = null;
			EnvConstant.FIRST_ORGANISM_WINNER = null;
		 
			EnvConstant.RUNNING = true;
		 
		 
		 
		 
		 
			for (expcount = 0; expcount < u_neat.p_num_runs; expcount++) 
			{
			
			   logger.sendToLog(" generation:      Spawned population ...");
			
			
			   if ((EnvConstant.TYPE_OF_START  == EnvConstant.START_FROM_GENOME) &&  (!EnvConstant.FORCE_RESTART ))
				  u_pop = new Population(u_genome, u_neat.p_pop_size);
			
			   if ((EnvConstant.TYPE_OF_START  == EnvConstant.START_FROM_NEW_RANDOM_POPULATION) && (!EnvConstant.FORCE_RESTART ))
				  u_pop = new Population(u_neat.p_pop_size, (u_inp_unit+1), u_out_unit, u_max_unit, u_recurrent, u_prb_link);
			
			   if (( EnvConstant.TYPE_OF_START  == EnvConstant.START_FROM_OLD_POPULATION) ||  (EnvConstant.FORCE_RESTART))
				  u_pop = new Population(EnvRoutine.getJneatFileData(EnvConstant.NAME_CURR_POPULATION));   									
			
			
			   logger.sendToLog(" generation:      Verifying Spawned Pop....");
			   u_pop.verify();
			
			// start ............
			//
			   for (gen = 1; gen <= EnvConstant.NUMBER_OF_EPOCH; gen++) 
			   {
			   //		        curr_name_pop_specie =  EnvConstant.PREFIX_SPECIES_FILE + fmt5.format(gen);
				  curr_name_pop_specie =  EnvConstant.PREFIX_SPECIES_FILE;
			   // 	  
				  EnvConstant.SUPER_WINNER_ = false;
				  boolean esito = epoch(u_neat, u_pop, gen, curr_name_pop_specie);
				  logger.sendToStatus(" running generation ->"+gen);
			   
				  if (EnvConstant.STOP_EPOCH)
					 break;
			   
			   }
			
			   if (EnvConstant.STOP_EPOCH)
				  break;
			
			}
		 
		 
		 // before exit save last population
			u_pop.print_to_file_by_species(EnvRoutine.getJneatFileData(EnvConstant.NAME_CURR_POPULATION));
			logger.sendToLog(" generation:      saved curr pop file "+EnvRoutine.getJneatFileData(EnvConstant.NAME_CURR_POPULATION));
			logger.sendToLog(" generation:  READY for other request");
		 
		 }
		 
		 
			 catch (Throwable e1) 
			{
			   logger.sendToLog(" error in generation.startNeat() "+e1);
			}


			EnvConstant.RUNNING = false;
			
		 logger.sendToStatus("READY");
		 return true;
	  
	  
	  }
   
   
   
   
   
   
   
	   public void setLog(HistoryLog _log) {
		 logger = _log;
	  }      
   
   
   
   
   
   
   
   
   
	   public boolean evaluate(Organism organism) 
	  {
		 double fit_dyn = 0.0;
		 double err_dyn = 0.0;
		 double win_dyn = 0.0;
		 double angle = 0.0;
		 double velocity = 0.0;
		 double y_target = 0.0;
		 double x_target = 0.0;
		 Map<Integer,ArrayList<Double>> map = null;
	  // per evitare errori il numero di ingressi e uscite viene calcolato in base
	  // ai dati ;
	  // per le unit di input a tale numero viene aggiunto una unit bias
	  // di tipo neuron
	  // le classi di copdifica input e output quindi dovranno fornire due
	  // metodi : uno per restituire l'input j-esimo e uno per restituire
	  // il numero di ingressi/uscite
	  // se I/O � da file allora � il metodo di acesso ai files che avr� lo
	  // stesso nome e che far� la stessa cosa.
	  
		 Network _net = null;
		 boolean success = false;
	  
		 double errorsum = 0.0;
		 int net_depth = 0; //The max depth of the network to be activated
		 int count = 0;
	  
	  
	  
	  //	  			   System.out.print("\n evaluate.step 1 ");
	  
		 double in[] = null;
		 in = new double[EnvConstant.NR_UNIT_INPUT + 1];
//		 in = new double[EnvConstant.NR_UNIT_INPUT];
	  
	  // setting bias
	  
		 in[EnvConstant.NR_UNIT_INPUT] = 1.0;
	  
		 double out[][] = null;
		 out = new double[EnvConstant.NUMBER_OF_SAMPLES][EnvConstant.NR_UNIT_OUTPUT];
	  
		 //double tgt[][] = null;
		 //tgt = new double[EnvConstant.NUMBER_OF_SAMPLES][EnvConstant.NR_UNIT_OUTPUT];
		 double tgt[][] = null;
		 tgt = new double[EnvConstant.NUMBER_OF_SAMPLES][EnvConstant.NR_UNIT_INPUT+1];
		 
	  
		 Integer ns = new Integer(EnvConstant.NUMBER_OF_SAMPLES);
	  
	  
		 _net = organism.net;
		 net_depth = _net.max_depth();
	  
	   // pass the number of node in genome for add a new 
	   // parameter of evaluate the fitness
	   //
		 int xnn = _net.getAllnodes().size();
		 Integer nn = new Integer(xnn);
	  
	  
		 //Class[] params = {int.class, int.class , double[][].class, double[][].class};
		 //Object paramsObj[] = new Object[] {ns, nn, out, tgt};
		 Class[] params = {int.class, double[][].class, double[][].class};
		 Object paramsObj[] = new Object[] {ns, out, tgt};
		 
//		 double minX = 40;
//		 double maxX = 60;	//X massima 100 [40+60]
//		 double minY = 20;
//		 double maxY = 80;	//Y massima 100 [20+80]
		 
//		 Random rx = new Random();
//		 long seedX = 10;
//		 rx.setSeed(seedX);
//		 Random ry = new Random();
//		 long seedY = 1000;
//		 ry.setSeed(seedY);
		 
//		 double[] inputX = new double[EnvConstant.NUMBER_OF_SAMPLES];
//		 double[] inputY = new double[EnvConstant.NUMBER_OF_SAMPLES];
//		 double[] inputX = {rx.nextDouble(), rx.nextDouble(), rx.nextDouble(), rx.nextDouble(), rx.nextDouble(),
//				 rx.nextDouble(), rx.nextDouble(), rx.nextDouble(), rx.nextDouble(), rx.nextDouble()};
//		 double[] inputY = {ry.nextDouble(), ry.nextDouble(), ry.nextDouble(), ry.nextDouble(), ry.nextDouble(),
//				 ry.nextDouble(), ry.nextDouble(), ry.nextDouble(), ry.nextDouble(), ry.nextDouble()};
//		 double[] inputX = {40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0, 45.0, 55.0, 65.0};
//		 double[] inputY = {70.0, 30.0, 20.0, 50.0, 60.0, 35.0, 45.0, 100.0, 75.0, 95.0};
		 
//		 for (int i=0; i<EnvConstant.NUMBER_OF_SAMPLES; i++)
//			 input[i]=Math.random();
		 


//		 for (count = 0; count < EnvConstant.NUMBER_OF_SAMPLES; count++) 
//		 {
////			 x = minX + Math.random()*maxX;
////			 y = minY + Math.random()*maxY;
////			 x = rx.nextFloat();
////			 y = ry.nextFloat();
//			 inputX[count] = x;
//			 inputY[count] = y;
//		 }
	  
		 if (EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_CLASS)
		 {
		 
		 
		 // case of input from class java
		 
			try 
			{
			   //int plist_in[] = new int[2];
			   //Class[] params_inp = {int[].class};
			   //Object[] paramsObj_inp = new Object[] {plist_in};

//			   Class[] params_inp = {double.class};
//			   Object[] paramsObj_inp = new Object[] {y};
				 String mask6d = "  0.00000";
				 DecimalFormat fmt6d = new DecimalFormat(mask6d);
				 
				 Random rx = new Random();

				 Random ry = new Random();

				 Random rm = new Random();
				 
				 //***** INPUT RIPETUTI *****//
				 rx.setSeed(100);
				 ry.setSeed(10000);
				 rm.setSeed(1000);
				 
//				 //***** INPUT RANDOM *****//
//				 long seedX = (long)(Math.random()*100);	
//				 rx.setSeed(seedX);
//				 long seedY = (long)(Math.random()*10000);
//				 ry.setSeed(seedY);
//				 long seedM = (long)(Math.random()*1000);
//				 rm.setSeed(seedM);
				 
				 
			   for (count = 0; count < EnvConstant.NUMBER_OF_SAMPLES; count++) 
			   {
//				   y = Math.random()*maxY;
//				   input[count] = y;
				  //plist_in[0] = count;
			   // first activation from sensor to first next level of neurons
//				  for (int j = 0; j < EnvConstant.NR_UNIT_INPUT; j++) 
//				  {
//					 //plist_in[1] = j;
//					 Method_inp = Class_inp.getMethod("getInput", params_inp);
//					 ObjRet_inp = Method_inp.invoke(ObjClass_inp, paramsObj_inp);
//					 double v1 = Double.parseDouble(ObjRet_inp.toString());
//					 in[j] = v1;
//				  }
			   
//			   in[0] = inputX[count];
//			   in[1] = inputY[count];
			   in[0] = rx.nextDouble();
			   in[1] = ry.nextDouble();
			   in[2] = rm.nextDouble();
			   //in[2] = rm.nextDouble();
//			   in[0] = NeatRoutine.randfloat();
//			   in[1] = NeatRoutine.randfloat();
			   tgt[count][0] = in[0];
			   tgt[count][1] = in[1];
			   tgt[count][2] = in[2];
			   //tgt[count][2] = in[2];

//			   System.out.println("------ LANCIO: "+count+" ------");
//			   System.out.println("INPUT 0:"+in[0]);
//			   System.out.println("INPUT 1:"+in[1]);
			   // load sensor   
				  _net.load_sensors(in);
			   /*
			   // activate net	  
			   success = _net.activate();
			   
			   // next activation while last level is reached !
			   // use depth to ensure relaxation
			   
			   for (int relax = 0; relax <= net_depth; relax++)
				success = _net.activate();
			   */
			   
				  if (EnvConstant.ACTIVATION_PERIOD == EnvConstant.MANUAL)
				  {
					 for (int relax = 0; relax < EnvConstant.ACTIVATION_TIMES; relax++)
					 {
						success = _net.activate();
					 }
				  }
				  else
				  {   	            
				  // first activation from sensor to next layer....
//					  System.out.println("LANCIO "+count);
					 success = _net.activate();
					 
				  // next activation while last level is reached !
				  // use depth to ensure relaxation
					 for (int relax = 0; relax <= net_depth; relax++)
					 {
						success = _net.activate();
					 }
				  }
			   
			   
			   
			   // for each sample save each output	
				   
//				   System.out.println("INPUT X: " +inputX[count]);
//				   System.out.println("INPUT Y: "+inputY[count]);
//				  for( int j=0; j < EnvConstant.NR_UNIT_OUTPUT; j++){
//					  out[count][j] = ((NNode) _net.getOutputs().elementAt(j)).getActivation();
////					  System.out.println(fmt6d.format(out[count][j]));
////					  System.out.println();
//				  }
	
			   // for each sample save each output	
//				  System.out.println("ESEMPIO NUMERO: "+count);
				  for( int j=0; j < EnvConstant.NR_UNIT_OUTPUT; j++)
				  {
					 out[count][j] = ((NNode) _net.getOutputs().elementAt(j)).getActivation();
//					 System.out.println(out[count][j]);
				  }
//				  double o1 = ((NNode) _net.getOutputs().elementAt(0)).getActivation();
//				  double o2 = ((NNode) _net.getOutputs().elementAt(1)).getActivation();
//				  out[count][0] = o1;
//				  out[count][1] = o2;
//			  System.out.println(fmt6d.format(o1));
//			  System.out.println(fmt6d.format(o2));
//			  System.out.println();
					 
				  
			   
			   // clear net		 
				  _net.flush();
			   }
			} 
			
				catch (Exception e2) 
			   {
				  System.out.print("\n Error generic in Generation.input signal : err-code = \n" + e2); 
				  System.out.print("\n re-run this application when the class is ready\n\t\t thank! "); 
				  System.exit(8);
			   
			   }
		 
		 }
		 
		 
		 
		 
		 
		 
		 
		 
		 else if (EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_FILE)
		 {
		 
		 //					System.out.print("\n evaluate.step 3a ");
		 
		 
			String nomef;
			nomef = EnvRoutine.getJneatFile(EnvConstant.DATA_INP);
		 
			StringTokenizer riga;
			String xline;
			String elem = null;
			IOseq xFile;
			xFile = new IOseq(nomef);
			boolean ret = xFile.IOseqOpenR();
		 
			if (ret)
			{
			   xline = xFile.IOseqRead();
			   count = 0;
			   while ((xline != "EOF")) 
			   {
				  if (!(xline.startsWith("//", 0))) 
				  {
					 riga = new StringTokenizer(xline);
					 int sz = riga.countTokens();
					 if (sz != EnvConstant.NR_UNIT_INPUT)
					 {
						System.out.print("\n *ALERT* in rec "+ count);
						System.out.print(" number of input = "+ sz);
						System.out.print(" different from declared "+EnvConstant.NR_UNIT_INPUT+" unit");
						System.out.print("\n correct and re-run;\n\t  Bye");
						System.exit(9);
					 }
				  
					 for (int j = 0; j < EnvConstant.NR_UNIT_INPUT; j++) 
					 {
						elem = riga.nextToken();
						double v1 = Double.parseDouble(elem);
						in[j] = v1;
					 
					 //						System.out.print("\n time("+count+") inp("+j+") = "+v1); 
					 
					 
					 }
				  
				  
				  // get sensor  
					 _net.load_sensors(in);
				  
				  /*				  // first activation	 
				  success = _net.activate();
				  
				  // next activation while last level is reached !
				  // use depth to ensure relaxation
				  for (int relax = 0; relax <= net_depth; relax++)
				  success = _net.activate();
				  */
				  
				  
				  //					System.out.print("\n evaluate.step 3b ");
				  
				  
				  
					 if (EnvConstant.ACTIVATION_PERIOD == EnvConstant.MANUAL)
					 {
						for (int relax = 0; relax < EnvConstant.ACTIVATION_TIMES; relax++)
						{
						   success = _net.activate();
						}
					 }
					 else
					 {   	            
					 // first activation from sensor to next layer....
						success = _net.activate();
					 
					 // next activation while last level is reached !
					 // use depth to ensure relaxation
						for (int relax = 0; relax <= net_depth; relax++)
						{
						   success = _net.activate();
						}
					 }
				  
				  
				  //			      					System.out.print("\n evaluate.step 3c ");
				  
				  
				  // for each sample save each output	
					 for( int j=0; j < EnvConstant.NR_UNIT_OUTPUT; j++)
					 {
					 //						System.out.print("\n time("+count+") out("+j+") ");
					 //						System.out.print(" = "+out[count][j]);
					 
						out[count][j] = ((NNode) _net.getOutputs().elementAt(j)).getActivation();
					 }
				  
				  // reset net		
					 _net.flush();
				  
					 count++;
				  }
				  xline = xFile.IOseqRead();
			   
			   }
			}
		 
		 
		 //								System.out.print("\n evaluate.step 3z ");
		 
		 
		 
		 }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  //success = true;
	  
	  
	  // control the result 
		 if (success) 
		 {
			try 
			{
			   if (EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_CLASS)
			   {
			   
			   
			   // prima di passare a calcolare il fitness legge il tgt da ripassare
			   // al chiamante;
				  //int plist_tgt[] = new int[2];
				  //Class [] params_tgt = {int[].class};
				  //Object[] paramsObj_tgt = new Object[] {plist_tgt};
//				   Class [] params_tgt = {double.class};
//				   Object[] paramsObj_tgt = new Object[] {y};
				   
		//	   System.out.println(EnvConstant.NUMBER_OF_SAMPLES);
				  for (count = 0; count < EnvConstant.NUMBER_OF_SAMPLES; count++) 
				  {
				  //					 System.out.print("\n sample : "+count);
				  
					 //plist_tgt[0] = count;
//					  y=inputY[count];
//					 for (int j = 0; j < EnvConstant.NR_UNIT_OUTPUT; j++)
//					 {
//						//plist_tgt[1] = j;
//						Method_tgt = Class_tgt.getMethod("getTarget", params_tgt);
//						ObjRet_tgt = Method_tgt.invoke(ObjClass_tgt, paramsObj_tgt);
//						double v1 = Double.parseDouble(ObjRet_tgt.toString());
//					 //						System.out.print(" ,  o["+j+"] = "+v1);
//						//tgt[count][j] = v1;
//						tgt[count] = v1;
//					 }
//					 tgt[count][0] = inputX[count];
//					 tgt[count][1] = inputY[count];
 					 
				  }

			   }
			   
			   
			  
			   else if (EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_FILE)
			   {
			   
				  String nomef;
				  nomef = EnvRoutine.getJneatFile(EnvConstant.DATA_OUT);
				  StringTokenizer riga;
				  String xline;
				  String elem = null;
				  IOseq xFile;
				  xFile = new IOseq(nomef);
				  boolean ret = xFile.IOseqOpenR();
			   
				  if (ret)
				  {
					 xline = xFile.IOseqRead();
					 count = 0;
					 while ((xline != "EOF")) 
					 {
						if (!(xline.startsWith("//", 0))) 
						{
						   riga = new StringTokenizer(xline);
						   int sz = riga.countTokens();
						   if (sz != EnvConstant.NR_UNIT_OUTPUT)
						   {
							  System.out.print("\n *ALERT* in rec "+ count);
							  System.out.print(" number of output = "+ sz);
							  System.out.print(" different from declared "+EnvConstant.NR_UNIT_OUTPUT+" unit");
							  System.out.print("\n correct and re-run;\n\t  Bye");
							  System.exit(9);
						   }
						
						   for (int j = 0; j < EnvConstant.NR_UNIT_OUTPUT; j++) 
						   {
							  elem = riga.nextToken();
							  double v1 = Double.parseDouble(elem);
							  //tgt[count][j] = v1;
							  tgt[count][j] = v1;
						   //					  System.out.print("\n per epoch "+count+" tgt("+j+"�) = "+v1);
						   }
						   count++;
						}
						xline = xFile.IOseqRead();
					 }
				  }    
			   
			   }
			
			
			//System.out.println(Class_fit);
			   Method_fit = Class_fit.getMethod("computeFitness", params);
			   //System.out.println("CAZZO");
			   ObjRet_fit = Method_fit.invoke(ObjClass_fit, paramsObj);
			   //System.out.println(ObjRet_fit);
//			   fit_dyn = Array.getDouble(ObjRet_fit, 0);
//			   err_dyn = Array.getDouble(ObjRet_fit, 1);
//			   win_dyn = Array.getDouble(ObjRet_fit, 2);
//			   angle = Array.getDouble(ObjRet_fit, 3);
//			   velocity = Array.getDouble(ObjRet_fit, 4);
//			   y_target = Array.getDouble(ObjRet_fit, 5);
//			   x_target = Array.getDouble(ObjRet_fit, 6);
			   HashMap<Integer,ArrayList<Double>> mappa = (HashMap<Integer, ArrayList<Double>>) ObjRet_fit;
			   ArrayList<Double> arrayBest = mappa.get(EnvConstant.NUMBER_OF_SAMPLES);
			   fit_dyn = arrayBest.get(MyConstants.FITNESS_TOTALE_INDEX);
			   err_dyn = arrayBest.get(MyConstants.ERRORE_INDEX);
			   win_dyn = arrayBest.get(MyConstants.WIN_INDEX);
			   angle = arrayBest.get(MyConstants.ANGOLO_INDEX);
			   velocity = arrayBest.get(MyConstants.VELOCITA_INDEX);
			   y_target = arrayBest.get(MyConstants.Y_TARGET_INDEX);
			   x_target = arrayBest.get(MyConstants.X_TARGET_INDEX);
			   map = mappa;
			//			   System.out.print("\n ce so passo!");
			
			
			
			} 
			
				catch (Exception e3) 
			   {
				  System.out.print("\n Error generic in Generation.success : err-code = \n" + e3); 
				  System.out.print("\n re-run this application when the class is ready\n\t\t thank! "); 
				  System.exit(8);
			   }
		 
			organism.setFitness(fit_dyn);
			organism.setError(err_dyn);
			organism.setAngle(angle);
			organism.setVelocity(velocity);
			organism.setYTarget(y_target);
			organism.setXTarget(x_target);
			organism.setMap(map);
		 } 
		 
		 
		 else 
		 {
			errorsum = 999.0;
			organism.setFitness(0.001);
			organism.setError(errorsum);
			organism.setAngle(0.0);
			organism.setVelocity(0.0);
			organism.setYTarget(0.0);
		 }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
		 if (win_dyn == 1.0) 
		 {
			organism.setWinner(true);
			return true;
		 } 
	  
		 if (win_dyn == 2.0) 
		 {
			organism.setWinner(true);
			EnvConstant.SUPER_WINNER_ = true;
			return true;
		 } 
	  
		 organism.setWinner(false);
		 return false;
	  
	  
	  }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	   public boolean epoch(
	   Neat _neat, 
	   Population pop, 
	   int generation, 
	   String filename) {
	  
		 String winner_prefix = EnvConstant.PREFIX_WINNER_FILE;
		 String riga1 = null;
		 boolean esito = false;
		 boolean win = false;
		 Genome _genome_win = null;
	  
		 Document doc2 = textPane2.getDocument();
		 String ckx = ck_group.getSelection().getActionCommand();
	  

		 if (generation == 1) 
		 {
			v1_fitness_win = new Vector(1, 0);
			v1_fitness = new Vector(1, 0);
			v1_species = new Vector(1, 0);
		 }
	  
	  
		 try 
		 {
		 
		 // Evaluate each organism if exist the winner.........
		 // flag and store only the first winner
		 
			Iterator itr_organism;
			itr_organism = pop.organisms.iterator();
			double max_fitness_of_winner = 0.0;
		 
			while (itr_organism.hasNext()) 
			{
			//point to organism
			   Organism _organism = ((Organism) itr_organism.next());
			
			//evaluate 
			   esito = evaluate(_organism);

			// if is a winner , store a flag
			   if (esito) {
				  win = true;
			   
				  if (_organism.getFitness() > max_fitness_of_winner) 
				  {
					 max_fitness_of_winner = _organism.getFitness();
					 EnvConstant.MAX_WINNER_FITNESS = max_fitness_of_winner;
				  }
			   //
			   // 01.06.2002 : store only first organism 
				  if (EnvConstant.FIRST_ORGANISM_WINNER == null) 
				  {
					 EnvConstant.FIRST_ORGANISM_WINNER = _organism;
				  // System.out.print("\n okay flagged first *****");
				  
				  }
			   
			   }
			}
		 
		 //compute average and max fitness for each species
			Iterator itr_specie;
			itr_specie = pop.species.iterator();
			while (itr_specie.hasNext()) 
			{
			   Species _specie = ((Species) itr_specie.next());
			   _specie.compute_average_fitness();
			   _specie.compute_max_fitness();
			}
		 // Only print to file every print_every generations
		 
			String cause1 = " ";
			String cause2 = " ";
			if (((generation % _neat.p_print_every) == 0) || (win)) 
			{
			
			   if ((generation % _neat.p_print_every) == 0)
				  cause1 = " request";
			   if (win)
				  cause2 = " winner";
			
			   String name_of_specie = EnvRoutine.getJneatFileData(filename) + generation;
			   pop.print_to_file_by_species(name_of_specie);
			   logger.sendToLog( " generation:      write/rewrite file specie  " + name_of_specie + " -> " + cause1 + cause2); 
			
			}
		 
		 // if exist a winner write to file   
			if (win) 
			{
			   String name_of_winner;
			   logger.sendToLog( " generation:      in this generation " + generation + " i have found at leat one WINNER  "); 
			   int conta = 0;
			   itr_organism = pop.getOrganisms().iterator();
			   while (itr_organism.hasNext()) 
			   {
				  Organism _organism = ((Organism) itr_organism.next());
				  if (_organism.winner) 
				  {
					 name_of_winner = EnvRoutine.getJneatFileData(winner_prefix) + generation + "_" + _organism.getGenome().genome_id; 
					 _organism.getGenome().print_to_filename(name_of_winner);
				  // EnvConstant.SERIAL_WINNER++;
					 conta++;
				  }
				  if (EnvConstant.SUPER_WINNER_) 
				  {
					 logger.sendToLog(" generation:      in this generation " + generation + " i have found a SUPER WINNER "); 
					 name_of_winner = EnvRoutine.getJneatFileData(winner_prefix)+ "_SUPER_" + generation + "_" + _organism.getGenome().genome_id; 
					 _organism.getGenome().print_to_filename(name_of_winner);
				  //  EnvConstant.SERIAL_SUPER_WINNER++;
					 EnvConstant.SUPER_WINNER_ = false;
				  
				  }
			   
			   }
			
			   logger.sendToLog(" generation:      number of winner's is " + conta);
			
			}
		 
		 // wait an epoch and make a reproduction of the best species
			
			pop.epoch(generation);
			
			
			if (!EnvConstant.REPORT_SPECIES_TESTA.equalsIgnoreCase("")) 
			{
				
			   doc2.insertString(doc2.getLength(),  "\n\n GENERATION : " + generation,  textPane2.getStyle(My_styles[2])); 
			   doc2.insertString(doc2.getLength(), EnvConstant.REPORT_SPECIES_TESTA, textPane2.getStyle(My_styles[1])); 
			   doc2.insertString(doc2.getLength(), EnvConstant.REPORT_SPECIES_CORPO, textPane2.getStyle(My_styles[3])); 
			   doc2.insertString(doc2.getLength(), EnvConstant.REPORT_SPECIES_CODA,  textPane2.getStyle(My_styles[1])); 
			
			   if (win)
				  logger.sendToLog(" generation:    This specie contains the  << WINNER >> ");
			
			   textPane2.setCaretPosition(doc2.getLength());
			
			
			   if (!(EnvConstant.FIRST_ORGANISM_WINNER == null)) 
			   {
				  int idx = ((Organism) EnvConstant.FIRST_ORGANISM_WINNER).genome.genome_id;
			   
				  if (win)
					 riga1 = "Time : " + generation + " genome (id=" + idx + ") is Current CHAMPION - WINNER "; 
				  else
					 riga1 = "Time : " + generation + " genome (id=" + idx + ") is Current CHAMPION "; 
			   
			
				  drawGraph((Organism) EnvConstant.FIRST_ORGANISM_WINNER, riga1, mappa_graph);
				 // updateNewGui((Organism) EnvConstant.FIRST_ORGANISM_WINNER);
			
			   
			   }
			
			   if (!(EnvConstant.CURR_ORGANISM_CHAMPION == null)) 
			   {
				   System.out.println("banana");
				  drawGraph((Organism) EnvConstant.CURR_ORGANISM_CHAMPION, " ", mappa_graph_curr); 
				 // updateNewGui((Organism) EnvConstant.CURR_ORGANISM_CHAMPION);
			
			   }
			
			
			}
			v1_species.add(new Double(generation));
			v1_species.add(new Double(pop.getSpecies().size()));
		 
			v1_fitness.add(new Double(generation));
			v1_fitness.add(new Double(pop.getHighest_fitness()));
		 
			v1_fitness_win.add(new Double(generation));
			v1_fitness_win.add(new Double(EnvConstant.MAX_WINNER_FITNESS));


		    drawCurve(riga1, pop.getHighest_fitness(), generation,  pop.getSpecies().size(),  _neat.p_pop_size);
			 
			if (win)
			   riga1 = "Time : " + generation + " found WINNER ! ";
			else
			   riga1 = "Time : " + generation + " ";

			drawCurve(riga1, pop.getHighest_fitness(), generation,  pop.getSpecies().size(),  _neat.p_pop_size);
			    
			if (ckx.equalsIgnoreCase("text output")) 
			{
			   p3.removeAll();
			   p3.add(p3_text, BorderLayout.CENTER);
			   p3_text.repaint();
			
			}
		 
			if (ckx.equalsIgnoreCase("graph champion")) 
			{
			
			   p3.removeAll();
			   p3.add(p3_graph, BorderLayout.CENTER);
			
			   mappa_graph.setAxis(false);
			   mappa_graph.setGrid(false);
			   mappa_graph.setBackground(Color.lightGray);
			   mappa_graph.setTitle(" First Winner genome ");
			   mappa_graph_curr.setTitle("Current genome (best fitness) ");
			   mappa_graph.setScale((p3.getWidth()) / 2, p3.getHeight());
			   mappa_graph_curr.setScale((p3.getWidth()) / 2, p3.getHeight());
			   mappa_graph.repaint();
			   mappa_graph_curr.repaint();
			   p3_graph.repaint();
			
			}
		 
			if (ckx.equalsIgnoreCase("plot Error")) {
			   p3.removeAll();
			   p3.add(p3_curve, BorderLayout.CENTER);
			   mappa_curve.setScale((p3.getWidth()), p3.getHeight());
			   drawCurve(riga1, pop.getHighest_fitness(), generation,  pop.getSpecies().size(),  _neat.p_pop_size); 
			   mappa_curve.repaint();
			   p3_curve.repaint();
			
			}
		 
			p3.validate();
			p3.repaint();
		 
		 
			if (win) 
			{
			   return true;
			} 
			else
			   return false;
		 
		 } 
		 
		 
		 
			 catch (Exception e) 
			{
			
			   System.out.print("\n exception in generation.epoch ->" + e);
			   System.exit(12);
			   return false;
			}
	  
	  }  
   
   
   
	public void setStyleNew(JTextPane textPane1) 
	  {
	  
		 StyleContext      stylecontext = StyleContext.getDefaultStyleContext();
		 Style             defstyle     = stylecontext.getStyle(StyleContext.DEFAULT_STYLE);
	  
		 Style             style        = textPane1.addStyle("normal", defstyle);
		 StyleConstants.setFontFamily(style, "Verdana ");
		 StyleConstants.setFontSize(style, 12);
	  
	  
		 style = textPane1.addStyle("italic", defstyle);
	  //		 StyleConstants.setForeground(style, new Color(24, 35, 87));
		 StyleConstants.setItalic(style, true);
		 StyleConstants.setFontSize(style, 11);
	  
	  
		 style = textPane1.addStyle("bold", defstyle);
	  //		 StyleConstants.setForeground(style, new Color(24, 35, 87));
		 StyleConstants.setBold(style, true);
		 StyleConstants.setFontSize(style, 13);
	  
	  
		 style = textPane1.addStyle("bold-italic", defstyle);
		 StyleConstants.setItalic(style, false);
		 StyleConstants.setBold(style, false);
		 StyleConstants.setFontSize(style, 12);
	  
	  
	  
	  
	  
	  
	  
	  
	  }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	   public int  getNumberUnitForFile(String _file)
	  {
		 String nomef;
		 StringTokenizer riga;
		 String xline;
		 int rc = 0;
		 IOseq xFile;
		 xFile = new IOseq(_file);
		 boolean ret = xFile.IOseqOpenR();
	  
		 if (ret)
		 {
		 
			xline = xFile.IOseqRead();
			boolean done = false;
			while ((xline != "EOF") && (!done)) 
			{
			
			   if (!(xline.startsWith("//", 0))) 
			   {
				  done = true;
				  riga = new StringTokenizer(xline);
				  rc  = riga.countTokens();
			   }
			   xline = xFile.IOseqRead();
			
			}
		 }
		 
		 else
		 {
			System.out.print("\n error in open ->" + _file);
			System.out.print("\n correct and re-run! \n\t Bye");
			System.exit(8);
		 
		 }
		 return rc;
	  
	  }
   
   
   
   
	   public void drawCurve(String _riga, double _fitness,int _generation,int _nrspecies,int _popsize) 
	  {
	  
	  
	  
	  
	  
	  //	private Vector vCurve;
		 double _xc1;
		 double _yc1;
		 double _xc2;
		 double _yc2;
	  
	  
	  
		 code xcodew = null;
		 Iterator _itr = null;
	  
		 mappa_curve.setScale(p3.getWidth(), p3.getHeight());
		 mappa_curve.setAxis(" time of epoch's"," Level fitness / Level winner fitness / #species ");
		 mappa_curve.setAxis(true);
		 mappa_curve.initAzioni();
		 mappa_curve.setLabelValueY2(_popsize);
		 mappa_curve.setLabelValueY(EnvConstant.MAX_FITNESS);
		 mappa_curve.setLabelValueX(EnvConstant.NUMBER_OF_EPOCH);

		 mappa_curve.azioni.add(new code(10, p3.getHeight()+8,  " Fitness  (0-"+EnvConstant.MAX_FITNESS+")", 1,CodeConstant.DESCRIPTOR_CURVE));
		 mappa_curve.azioni.add(new code(210, p3.getHeight()+8, " Species (1-"+_popsize+")", -1,CodeConstant.DESCRIPTOR_CURVE));
		 mappa_curve.azioni.add(new code(410, p3.getHeight()+8, " Fitness Winner", 2,CodeConstant.DESCRIPTOR_CURVE));

		 
		 mappa_curve.setGrid(true);
	  
	  //
	  // draw current fitness
	  //
	  
	  
		 Double nx = null;
		 Double ny = null;
		 double _x1 = 0.0;
		 double _y1 = 0.0;
		 double _x2 = 0.0;
		 double _y2 = 0.0;
	  
		 Iterator itr_fit = v1_fitness.iterator();
		 boolean first_time = true;
		 while (itr_fit.hasNext()) 
		 {
		 
			if ((first_time))
			{
			   _x1 = 0.0;
			   _y1 = 0.0;
			   first_time = false;
			}
			
			else
			{
			   _x1 = _x2;
			   _y1 = _y2;
			}
		 
			nx = (Double) itr_fit.next();
			ny = (Double) itr_fit.next();
			_x2 = nx.doubleValue();
			_y2 = ny.doubleValue();
		 
			_yc1 =  (_y1 * p3.getHeight())  / EnvConstant.MAX_FITNESS;
			_xc1  = (_x1  * p3.getWidth()) / EnvConstant.NUMBER_OF_EPOCH;
			_yc2 =  (_y2 * p3.getHeight())  / EnvConstant.MAX_FITNESS;
			_xc2  = (_x2  * p3.getWidth()) / EnvConstant.NUMBER_OF_EPOCH;
		 
		  // current fitness
			xcodew = new code((int)_xc1,(int)_yc1,(int)_xc2,(int)_yc2,CodeConstant.LINE_TYPE_1,+1);
			mappa_curve.addLineToGrafo(xcodew);
		 
		 }
	  
	  
	  //
	  // draw fitness of winner
	  //
	  
	  
		 nx = null;
		 ny = null;
		 _x1 = 0.0;
		 _y1 = 0.0;
		 _x2 = 0.0;
		 _y2 = 0.0;
	  
		 itr_fit = v1_fitness_win.iterator();
		 first_time = true;
		 while (itr_fit.hasNext()) 
		 {
			if ((first_time))
			{
			   _x1 = 0.0;
			   _y1 = 0.0;
			   first_time = false;
			}
			else
			{
			   _x1 = _x2;
			   _y1 = _y2;
			}
		 
			nx = (Double) itr_fit.next();
			ny = (Double) itr_fit.next();
			_x2 = nx.doubleValue();
			_y2 = ny.doubleValue();
		 
			_yc1 =  (_y1 * p3.getHeight())  / EnvConstant.MAX_FITNESS;
			_xc1  = (_x1  * p3.getWidth()) / EnvConstant.NUMBER_OF_EPOCH;
			_yc2 =  (_y2 * p3.getHeight())  / EnvConstant.MAX_FITNESS;
			_xc2  = (_x2  * p3.getWidth()) / EnvConstant.NUMBER_OF_EPOCH;
		 
		  // current fitness of winner
			xcodew = new code((int)_xc1,(int)_yc1,(int)_xc2,(int)_yc2,CodeConstant.LINE_TYPE_1,+2);
			mappa_curve.addLineToGrafo(xcodew);
		 
		 }
	  
	  
	  
	  
	  
	  //
	  // draw number of species 
	  //
	  
	  
		 nx = null;
		 ny = null;
		 _x1 = 0.0;
		 _y1 = 0.0;
		 _x2 = 0.0;
		 _y2 = 0.0;
	  
		 _itr = v1_species.iterator();
		 first_time = true;
		 while (_itr.hasNext()) 
		 {
		 
			if ((first_time))
			{
			   _x1 = 0.0;
			   _y1 = 0.0;
			   first_time = false;
			}
			else
			{
			   _x1 = _x2;
			   _y1 = _y2;
			}
		 
			nx = (Double) _itr.next();
			ny = (Double) _itr.next();
			_x2 = nx.doubleValue();
			_y2 = ny.doubleValue();
		 
			_yc1 =  (_y1 * p3.getHeight())  /    _popsize;
			_xc1  = (_x1  * p3.getWidth()) /  EnvConstant.NUMBER_OF_EPOCH;
			_yc2 =  (_y2 * p3.getHeight())  /    _popsize;
			_xc2  = (_x2  * p3.getWidth()) / EnvConstant.NUMBER_OF_EPOCH;
		 
		  // current fitness of winner
			xcodew = new code((int)_xc1,(int)_yc1,(int)_xc2,(int)_yc2,CodeConstant.LINE_TYPE_1,-1);
			mappa_curve.addLineToGrafo(xcodew);
		 
		 }
	  
		 mappa_curve.setAxis(true);
		 mappa_curve.setGrid(true);
	  //		 mappa_curve.repaint();
	  
	  
	  }	   	    
	   public void setGraphView() 
	  {
	  /*	           String mask6d = "  0.00000";
			  DecimalFormat fmt6d = new DecimalFormat(mask6d);
	  	   mappa.setTitle(" First Winner genome ->"+ fmt6d.format(vf1));	  
	  */
	  
	  
	  
		 p3.removeAll();
		 p3.add(p3_graph,BorderLayout.CENTER);
	 
		 p3_graph.validate();
		 p3_graph.repaint();				
	  
	  }
	   
	   public void drawGraph(Organism  _o1,String _riga,chartXY _mappa) 
	  {
	  
		 String mask6d = "  0.0000000";
		 DecimalFormat fmt6d = new DecimalFormat(mask6d);
	  
		 Genome _g1 = _o1.genome;
	  
		 Vector v1 = new Vector(1, 0);
		 Structure sx = new Structure();
	  
		 _mappa.initAzioni();
	  
		 sx.LoadGenome(_g1);
		 sx.generate_Grafo();
	  
		 sx.compute_Coordinate(p3.getWidth()/2, p3.getHeight());
		 double x_tgt = _o1.getXTarget();
		 double y_tiro = Math.tan(_o1.getAngle())*x_tgt - ((9.81/(2*Math.pow(_o1.getVelocity(), 2)*Math.pow(Math.cos(_o1.getAngle()), 2)))*Math.pow(x_tgt, 2));
		 
		 
		 String riga_r1 = " Fitness ->"+fmt6d.format(_o1.getOrig_fitness())+"  , Error->"+fmt6d.format(_o1.getError());
		 String riga_r2 = " Angle ->"+fmt6d.format(_o1.getAngle())+"  , Velocity->"+fmt6d.format(_o1.getVelocity());
		 String riga_r3 = " Y_Target ->"+fmt6d.format(_o1.getYTarget())+"  , Y_Tiro->"+fmt6d.format(y_tiro);
		 String riga_r4 = " X_Target ->"+fmt6d.format(x_tgt)+ "  , Generation->"+_o1.getGeneration();
		 //String riga_r4 = " X_Target ->"+fmt6d.format(x_tgt);
		 
	  
		 v1.add(new code(10,p3.getHeight()-20 ,  riga_r1, 0,CodeConstant.DESCRIPTOR));
		 v1.add(new code(10,p3.getHeight()+0 ,  riga_r2, 0,CodeConstant.DESCRIPTOR));
		 v1.add(new code(10,p3.getHeight()+20 ,  riga_r3, 0,CodeConstant.DESCRIPTOR));
		 v1.add(new code(10,p3.getHeight()+40 ,  riga_r4, 0,CodeConstant.DESCRIPTOR));
		 //v1.add(new code(10,p3.getHeight()+30 ,  riga_r4, 0,CodeConstant.DESCRIPTOR));
	 
		 
		 Map<Integer,ArrayList<Double>> mappa = _o1.getMap();
		 for (int i=0; i<EnvConstant.NUMBER_OF_SAMPLES; i++)
		 {
			 System.out.println("------ LANCIO: "+ (i+1) +" ------");
			 ArrayList<Double> array = mappa.get(i);
			 System.out.println("X_OBJ: "+fmt6d.format(array.get(MyConstants.X_TARGET_INDEX)));
			 System.out.println("Y_OBJ: "+fmt6d.format(array.get(MyConstants.Y_TARGET_INDEX)));
			 System.out.println("Y_TIRO: "+fmt6d.format(array.get(MyConstants.Y_LANCIO_INDEX)));
			 System.out.println("ANGOLO: "+fmt6d.format(array.get(MyConstants.ANGOLO_INDEX)));
			 System.out.println("VELOCITA': "+fmt6d.format(array.get(MyConstants.VELOCITA_INDEX)));
			 System.out.println("FORZA: "+fmt6d.format(array.get(MyConstants.FORZA_INDEX)));
			 System.out.println("TEMPO: "+fmt6d.format(array.get(MyConstants.TEMPO_INDEX)));
			 System.out.println("ACCELERAZIONE: "+fmt6d.format(array.get(MyConstants.ACCELERAZIONE_INDEX)));
			 System.out.println("MASSA: "+fmt6d.format(array.get(MyConstants.MASSA_INDEX)));
			 System.out.println("ERRORE: "+fmt6d.format(array.get(MyConstants.ERRORE_INDEX)));
			 //System.out.println("FITNESS: "+fmt6d.format(array.get(6)));
		 }
		 System.out.println("GENERAZIONE: "+_o1.getGeneration());
		 System.out.println("ERRORE TOTALE: "+fmt6d.format(mappa.get(EnvConstant.NUMBER_OF_SAMPLES).get(MyConstants.ERRORE_TOTALE_INDEX)));
		 System.out.println("FITNESS TOTALE: "+fmt6d.format(mappa.get(EnvConstant.NUMBER_OF_SAMPLES).get(MyConstants.FITNESS_TOTALE_INDEX)));
		 System.out.println("FITNESS VECCHIA: "+fmt6d.format(mappa.get(EnvConstant.NUMBER_OF_SAMPLES).get(MyConstants.FITNESS_VECCHIA_INDEX)));
		 System.out.println();
		
		 
		 
		 
//			System.out.println("ANGOLO = "+_o1.getAngle());
//			System.out.println("VELOCITA' = "+_o1.getVelocity());
		 
//		 System.out.println("GENERAZIONE:   "+_o1.getGeneration());
//		 System.out.println("Y_TIRO:     "+y_tiro);
//		 System.out.println("Y_TARGET:    "+_o1.getYTarget());
//		 System.out.println("ERRORE:    "+_o1.getError());
		 
	  
	  
		 Iterator itr_point = sx.vVertex.iterator();
		 while (itr_point.hasNext()) 
		 {
			Vertex _point = ((Vertex) itr_point.next());
		 
			if ((_point.x) != 0 && (_point.y != 0) && (_point.is_real()))
			   v1.add(new code(_point, CodeConstant.NODO_N));
			if ((_point.x) != 0 && (_point.y != 0) && (_point.is_recurrent()))
			   v1.add(new code(_point, CodeConstant.NODO_R));
		 
		 }
	  
	  // store edge for interpreter
		 Iterator itr_edge = sx.vEdge.iterator();
		 while (itr_edge.hasNext()) 
		 {
			Edge _edge = ((Edge) itr_edge.next());
			Vertex _inode = _edge.in_node;
			Vertex _onode = _edge.out_node;
			int type = _edge.type;
		 
			double weight_edge = _edge.weight;
			int sign_edge;
			if (weight_edge >= 0 )
			   sign_edge = +1;
			else
			   sign_edge = -1;
		 
		 
		 
			if ((_inode.x) != 0
			&& (_inode.y != 0)
			&& (_onode.x) != 0
			&& (_onode.y != 0)
			&& (_edge.active)) 
			{
			   int x1 = _inode.x;
			   int y1 = _inode.y;
			   int x2 = _onode.x;
			   int y2 = _onode.y;
			   v1.add(new code(_inode, _onode, type, sign_edge));
			}
		 }
	  
		 _mappa.setScale((p3.getWidth())/2 , p3.getHeight());
		 _mappa.setAxis(false);
		 _mappa.setGrid(false);
		 _mappa.setGrafo(v1);
		 _mappa.repaint();	  
	  }
   
   
   
	   public void initAllMap() 
	  {
		 textPane2.setText("");
		 p3_text.repaint();
	  
		 p3.removeAll();
		 p3.add(p3_text, BorderLayout.CENTER);
	  
	  
		 p3.removeAll();
		 p3.add(p3_graph, BorderLayout.CENTER);
		 mappa_graph.setAxis(false);
		 mappa_graph.setGrid(false);
		 mappa_graph.setBackground(Color.lightGray);
		 mappa_graph.setTitle(" First Winner genome ");
		 mappa_graph_curr.setTitle("Current genome (best fitness) ");
		 mappa_graph.setScale((p3.getWidth()) / 2, p3.getHeight());
		 mappa_graph_curr.setScale((p3.getWidth()) / 2, p3.getHeight());
		 mappa_graph.initAzioni();
		 mappa_graph_curr.initAzioni();
		 mappa_graph.repaint();
		 mappa_graph_curr.repaint();
		 p3_graph.validate();
		 p3_graph.repaint();
	  
		 p3.removeAll();
		 p3.add(p3_curve, BorderLayout.CENTER);
		 mappa_curve.setScale((p3.getWidth()), p3.getHeight());
		 mappa_curve.repaint();
		 p3_curve.validate();
		 p3_curve.repaint();
	  
		 p3.removeAll();
		 v1_fitness_win = new Vector(1, 0);
		 v1_fitness = new Vector(1, 0);
		 v1_species = new Vector(1, 0);
	  
	  }
   
   
	   public void ViewGeneric() 
	  {
		 String ckx = ck_group.getSelection().getActionCommand();
	  
		 if (ckx.equalsIgnoreCase("text output")) 
		 {
			p3.removeAll();
			p3.add(p3_text, BorderLayout.CENTER);
			p3_text.repaint();
		 
		 }
	  
		 if (ckx.equalsIgnoreCase("graph champion")) 
		 {
		 
			p3.removeAll();
			p3.add(p3_graph, BorderLayout.CENTER);
		 
			mappa_graph.repaint();
			mappa_graph_curr.repaint();
			p3_graph.repaint();
		 
		 }
	  
		 if (ckx.equalsIgnoreCase("plot Error")) {
			p3.removeAll();
			p3.add(p3_curve, BorderLayout.CENTER);
			mappa_curve.repaint();
			p3_curve.repaint();
		 
		 }
	  
	  //		System.out.print("\n init 4a");
		 p3.validate();
		 p3.repaint();
	  
	  }}