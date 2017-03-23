package newGui;

public class MyConstants 
{
	public static String TITLE = "New NEAT GUI";
	public static int WIDTH = 800;
	public static int HEIGHT = 480;
	public static int OPTIONS_WIDTH = 250;
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
	
	/////INDICI DELLE INFORMAZIONI SULLA RETE
	public static int ERRORE_TOTALE_INDEX = 11;
	public static int FITNESS_TOTALE_INDEX = 12;
	public static int FITNESS_VECCHIA_INDEX = 13;
	public static int LANCIO_MIGLIORE_INDEX = 14;
	public static int WIN_INDEX = 15;
	
	/////NUMERO INDICI
	public static int INFO_LANCIO_SIZE = 11;
	public static int INFO_RETE_SIZE = 16;
	
	
	/////INFORMAZIONI PER START
	public static String DATA_DIR = "data\\";
	public static String RESULTS_DIR = "results\\";
	public static String PARAMETRI_NOMEFILE = "parametri";
	public static String GENOMA_NOMEFILE = DATA_DIR +"genome_3in_4hid_3out";
	public static String POP_NOMEFILE = DATA_DIR + "primitive";
	
	////GESTIONE UPDATE E RENDER
	public static long MILLISECOND = 1000L;
	public static long FPS = 60;
	
	////COSTANTI PARABOLA
	public static double GRAVITY = 9.81;
	public static int BORDER_X = 20;
	public static int BORDER_Y = 20;
	public static int ASSE_X = 100;
	public static int ASSE_Y = 100;	
}
