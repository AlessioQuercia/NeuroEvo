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
		 double minM = 1;	// massa minima
		 double maxM = 2;	// massa massima
		 double maxA = 1.5708;	// angolo massimo in radianti (corrisponde a 90 gradi)
		 double minV = 40;	// velocità minima 20
		 double maxV = 40;	// velocità massima 100 [50 + 50]
		 double minF = 75;	// forza minima
		 double maxF = 225;	// forza massima
		 double minT = 0.5;	// tempo minimo
		 double maxT = 1.5;	//tempo massimo
//		 double x_obj = minX + 0.4*maxX;
		 double g = 9.81;
		 int iter = -1;
		 double min_error = 100000.0;
		 double error = 0.0;
	     double errorsum = 0.0; 
	     double errorsumquad = 0.0;
	     double fitness = 0.0;
	     double fitness2 = 0.0;
	     double win = 0.0;
	     double k = 0.01;	//costante per poter calcolare la fitness anche quando l'errore è 0
	     //d[2] = 0.0;
	     ArrayList<Double> arrayBest = new ArrayList<Double> ();
	     for (int i = 0; i<9; i++)
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
	    	 	double m = minM + _tgt[j][2]*maxM;
//	    	 	double x_obj = _tgt[j][0];
//	    	 	double y_obj = _tgt[j][1];
	    	 	double a = _out[j][0]*maxA;
//	    	 	double v = minV + _out[j][1]*maxV;
	    	 	double F = minF + _out[j][1]*maxF;
	    	 	double t = minT + _out[j][2]*maxT;
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
	    	 	double acc = F/m;
	    	 	double v = acc*t;
	    	 	double y_tiro = Math.tan(a)*x_obj - ((g/(2*Math.pow(v, 2)*Math.pow(Math.cos(a), 2)))*Math.pow(x_obj, 2));
//	    	 	errorsum  += ( double ) (Math.abs(_tgt[j] - y_tiro));		//FITNESS VECCHIA
	    	 	error = Math.abs(y_obj - y_tiro);
//	    	 	errorsum += Math.pow(error, 2);		//fitness_somma_quadrati
	    	 	errorsum += error;			//fitness_quadrato_somma
//	    	 	errorsumquad += Math.pow(error, 2);		//fitness_somma_quadrati
	    	 	
	    	 	//TIRO MIGLIORE
	    	 	if (error<min_error)
	    	 	{
	    	 		iter = j;
	    	 		min_error = error;
	    	 		//System.out.println("CACACA");
	    	 		arrayBest.add(0, x_obj);
	    	 		//System.out.println("CiaO");
	    	 		arrayBest.add(1, y_obj);
	    	 		arrayBest.add(2, y_tiro);
	    	 		arrayBest.add(3, a);
	    	 		arrayBest.add(4, v);
	    	 		arrayBest.add(5, min_error);
		    	 	arrayBest.add(10, F);
		    	 	arrayBest.add(11, t);
		    	 	arrayBest.add(12, acc);
		    	 	arrayBest.add(13, m);
	    	 	}
	    	 	fitness2 += 1/(error+k);
	    	 	//fitness += 1/(errorsum+k);		//FITNESS VECCHIA
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
	    	 	array.add(5, error);
	    	 	array.add(6, fitness);
	    	 	array.add(7, F);
	    	 	array.add(8, t);
	    	 	array.add(9, acc);
	    	 	array.add(10, m);
	    	 	mappa.put(j, array);
	        } 
//	     fitness = 1000000 - Math.pow(errorsum, 3);		//fitness_cubo_somma
	     fitness = 1000000 - Math.pow(errorsum, 2);		//fitness_quadrato_somma
//	     fitness = 100000 - errorsumquad;		//fitness_somma_quadrati

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
	     
	    //if (fitness>=500) win = 1.0;
 	 	 arrayBest.add(6, fitness);
 	 	 arrayBest.add(7, win);
 	 	 arrayBest.add(8, errorsum);
 	 	 arrayBest.add(9, fitness2);
 	 	 mappa.put(_sample, arrayBest);
	     //d[2] = 0.0;
	     //if (fitness > 20) d[2] = 1;
	     return mappa; 
	  } 
}
