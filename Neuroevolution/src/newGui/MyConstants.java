package newGui;

import java.util.ArrayList;

public class MyConstants 
{
	public static String TITLE = "New NEAT GUI";
	public static int WIDTH = 800;
	public static int HEIGHT = 480;
	public static int OPTIONS_WIDTH = 220;
	public static int LANCIO_WIDTH = 520;
	

	/////INDICI DELLE INFORMAZIONI SUL LANCIO
	public static int X_TARGET_INDEX = 0;
	public static int Y_TARGET_INDEX = 1;
	public static int Y_LANCIO_INDEX = 2;
	public static int ANGOLO_INDEX = 3;
	public static int VELOCITA_INDEX = 4;
	public static int ERRORE_INDEX = 5;
	public static int FITNESS_INDEX = 6;
	public static int FORZA_INDEX = 7;
	public static int TEMPO_INDEX = 8;
	public static int ACCELERAZIONE_INDEX = 9;
	public static int MASSA_INDEX = 10;
	public static int X_MIGLIORE_INDEX = 11;
	public static int Y_MIGLIORE_INDEX = 12;
	
	/////INDICI DELLE INFORMAZIONI SULLA RETE
	public static int ERRORE_TOTALE_INDEX = 13;
	public static int FITNESS_TOTALE_INDEX = 14;
	public static int FITNESS_VECCHIA_INDEX = 15;
	public static int LANCIO_MIGLIORE_INDEX = 16;
	public static int WIN_INDEX = 17;
	
	/////NUMERO INDICI
	public static int INFO_LANCIO_SIZE = 13;
	public static int INFO_RETE_SIZE = 18;
	
	
	/////INFORMAZIONI PER START
	public static String DATA_DIR = "data\\";
	public static String RESULTS_DIR = "results\\";
	public static String PARAMETRI_NOMEFILE = "parametri";
	public static String GENOMA_NOMEFILE = DATA_DIR +"genome_5in_4hid_3out";
	public static String POP_NOMEFILE = DATA_DIR + "primitive";
	
	////GESTIONE UPDATE E RENDER
	public static long MILLISECOND = 1000L;
	public static long FPS = 60;
	
	////COSTANTI PARABOLA
	public static double GRAVITY = 9.81;
	public static int BORDER_X = 20;
	public static int BORDER_Y = 20;
	public static int ASSE_X = 150;
	public static int ASSE_Y = 150;	
	public static int TAIL_LENGTH = 15;
	
	////COSTANTI LOAD INPUT
	public static double LOADED_X;
	public static double LOADED_Y;
	public static boolean LOADED_INPUTS = false;
	
	////COSTANTI SETTINGS
	public static final String OTHER_SETTINGS = "otherSettings";
	public static final String DEFAULT_OTHER_SETTINGS = "defaultOtherSettings";
	public static boolean[] SETTINGS_VALUES = null;
	public static final int SIM_AUTO_DRAW_INDEX = 0;
	public static final int SIM_SHOW_BEST_INDEX = 1;
	public static final int SIM_PHYSICS = 2;
	public static final int GRAPHS_AUTO_DRAW_INDEX = 3;
	public static final int GRAPHS_GRID_INDEX = 4;
	public static final int NET_AUTO_DRAW_INDEX = 5;
	
	////COSTANTI ID NODI
	public static ArrayList<Integer> INPUT_NODES_ID = new ArrayList<Integer>();
	public static ArrayList<Integer> BIAS_NODES_ID = new ArrayList<Integer>();
	public static ArrayList<Integer> OUTPUT_NODES_ID = new ArrayList<Integer>();
	
	////INDICI PER LA SIMULAZIONE DI LANCIO DELLA RETE (da salvare nell'array tgt[][])
	public static int SIM_X_TGT_INDEX = 0;
	public static int SIM_Y_TGT_INDEX = 1;
	public static int SIM_VEL_INDEX = 2;
	public static int SIM_MASSA_INDEX = 3;
	public static int SIM_ANGOLO_INDEX = 4;
	public static int SIM_FORZA_INDEX = 5;
	public static int SIM_ACCELERAZIONE_INDEX = 6;
	public static int SIM_TEMPO = 7;
	public static int SIM_DISTANZA_MINIMA = 8;
	public static int SIM_X_MIGLIORE = 9;
	public static int SIM_Y_MIGLIORE = 10;
}
