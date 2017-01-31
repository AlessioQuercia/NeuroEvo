package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class evo_fit 
{
	  public static double getMaxFitness() { return 1000; }  //ovvero quando l'errore è 0,1
	  
	  public static HashMap<Integer, ArrayList<Double>>  computeFitness(int _sample, double _out[][], double _tgt[][]) 
	  {     
		 //double d[] = new double[7];
		 HashMap<Integer,ArrayList<Double>> mappa= new HashMap<Integer,ArrayList<Double>>();
		 double minX = 20;	// x minima
		 double maxX = 80;	// x massima
		 double minY = 20;	// y minima
		 double maxY = 80;	// y massima
		 double maxA = 1.5708;	// angolo massimo in radianti (corrisponde a 90 gradi)
		 double minV = 50;	// velocità minima 20
		 double maxV = 50;	// velocità massima 100 [50 + 50]
//		 double x_obj = minX + 0.4*maxX;
		 double g = 9.81;
		 int iter = -1;
		 double min_error = 100000.0;
	     double errorsum = 0.0; 
	     double fitness = 0.0;
	     double win = 0.0;
	     double k = 0.01;	//costante per poter calcolare la fitness anche quando l'errore è 0
	     //d[2] = 0.0;
	     ArrayList<Double> arrayBest = new ArrayList<Double> ();
	     for (int i = 0; i<8; i++)
	     {
	    	 arrayBest.add(0.0);
	     }
//	     System.out.println(array.size());
//	     System.out.println(arrayBest.size());
	     //mappa.put(10, 0.0);
	     //System.out.println("SAMPLE :   "+_sample);

	     for ( int j = 0; j < _sample; j++) 
	        { 
//	    	 System.out.println("X_TARGET:    "+_tgt[j][0]);
//	    	 System.out.println("Y_TARGET:    "+_tgt[j][1]);
		     ArrayList<Double> array = new ArrayList<Double> ();
		     
//		     array.add(0.0);
//		     for (int i = 0; i<7; i++)
//		     {
//		    	 array.add(0.0);
//		    	 arrayBest.add(0.0);
//		     }
//			     arrayBest.add(0.0);
	    	 	double x_obj = minX + _tgt[j][0]*maxX;
	    	 	double y_obj = minY + _tgt[j][1]*maxY;
//	    	 	double x_obj = _tgt[j][0];
//	    	 	double y_obj = _tgt[j][1];
	    	 	double a = _out[j][0]*maxA;
	    	 	double v = minV + _out[j][1]*maxV;
//	    	 	System.out.println(x_obj);
//	    	 	System.out.println(y_obj);
//	    	 	System.out.println(a);
//	    	 	System.out.println(v);
//	    	 	d[3] = a;
//	    	 	d[4] = v;
//	    	 	d[5] = _tgt[j][1];	//y_obj
//	    	 	d[6] = _tgt[j][0];	//x_obj
	    	 	//if (v>100) System.out.println("V");
	    	 	//if (a>90) System.out.println("A");
	    	 	//System.out.println("ANGOLO:  "+a);
	    	 	//System.out.println("VELOCITA':   " + v);
	    	 	double y_tiro = Math.tan(a)*x_obj - ((g/(2*Math.pow(v, 2)*Math.pow(Math.cos(a), 2)))*Math.pow(x_obj, 2));
//	    	 	errorsum  += ( double ) (Math.abs(_tgt[j] - y_tiro));
	    	 	errorsum  = Math.abs(y_obj - y_tiro);
	    	 	
	    	 	//TIRO MIGLIORE
	    	 	if (errorsum<min_error)
	    	 	{
	    	 		iter = j;
	    	 		min_error = errorsum;
	    	 		//System.out.println("CACACA");
	    	 		arrayBest.add(0, x_obj);
	    	 		//System.out.println("CiaO");
	    	 		arrayBest.add(1, y_obj);
	    	 		arrayBest.add(2, y_tiro);
	    	 		arrayBest.add(3, a);
	    	 		arrayBest.add(4, v);
	    	 		arrayBest.add(5, min_error);
	    	 	}
	    	 	
	    	 	fitness += 1/(errorsum+k);
//	    	 	System.out.println("LANCIO:  "+j);
//	    	 	System.out.println("ERRORE:  "+errorsum);
//	    	 	System.out.println("FITNESS:  "+fitness);
//	    	 	if (errorsum==0) {
//	    	 		System.out.println("SUPERWINNER");
//	    	 		d[2] = 2.00;
//	    	 	}
//	    	 	fitness = 1/errorsum;
//	    	 	System.out.println("ERRORE "+j+":      "+errorsum);
//	    	 	System.out.println("ANGOLO: "+a);
//	    	 	System.out.println("VELOCITA': "+v);
//	    	 	System.out.println("Y_TIRO:  "+y_tiro);
//	    	 	System.out.println("Y_OBJ:   "+_tgt[j]);
//	    	 	System.out.println("FITNESS:   "+fitness);
	    	 	array.add(0, x_obj);
	    	 	array.add(1, y_obj);
	    	 	array.add(2, y_tiro);
	    	 	array.add(3, a);
	    	 	array.add(4, v);
	    	 	array.add(5, errorsum);
	    	 	array.add(6, fitness);
	    	 	mappa.put(j, array);
	        } 
//	     d[0] = fitness; 
//	     d[1] = errorsum;
//	     if (iter>=0)
//	     {
//	    	 d[1] = min_error;
//	 		 d[3] = _out[iter][0]*maxA;
//	 	 	 d[4] = minV + _out[iter][1]*maxV;
//	 	 	 d[5] = _tgt[iter][1];	//y_obj
//	 	 	 d[6] = _tgt[iter][0];	//x_obj
//	     }
	     
	     if (fitness>=200) win = 1.0;
 	 	 arrayBest.add(6, fitness);
 	 	 arrayBest.add(7, win);
 	 	 mappa.put(_sample, arrayBest);
	     //d[2] = 0.0;
	     //if (fitness > 20) d[2] = 1;
	     return mappa; 
	  } 
}
