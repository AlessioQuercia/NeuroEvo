package gui;

public class evo_out 
{
	public static String[] outputNames = {"angle", "force", "leave-boolean"};
	
	   public static int getNumUnit() { return 3; } 
	   
	   public static double getTarget(double y_obj) 
	   { 
		   //double g = 2.8;				//costante gravitazionale (accelerazione di gravit�)
		   //double a_tgt = 45 + Math.atan(y_obj/x_obj)/2; 	//angolo di tiro pi� efficiente (che permette di utilizzare la velicit� minima)
		   //double v_tgt = x_obj*Math.cos(a_tgt)*Math.sqrt(g/2*(Math.tan(a_tgt)*x_obj-y_obj)); 	//velocit� di lancio
		   //double [] target = {a_tgt,v_tgt};
		   //double maxY = 100;
		   double target = y_obj;//  *maxY;
		   return target;
	   } 
}
