package newGui;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.joml.Vector2d;

import gui.evo_fit;
import jNeatCommon.EnvConstant;
import jneat.NNode;
import jneat.Network;
import jneat.Organism;

public class OrganismRunnableFirst implements Runnable
{
	private Organism o;
	
	// dynamic definition for fitness
		  Class  Class_fit;
		  Object ObjClass_fit;
		  Method Method_fit;
		  Object ObjRet_fit;
	
	public OrganismRunnableFirst(Organism o) 
	{
		this.o = o;
		
		Class_fit = evo_fit.class; //Class.forName(EnvConstant.CLASS_FITNESS);
		try {
			ObjClass_fit = Class_fit.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() 
	{
		evaluate(o);
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
			 double total_err = 0.0;
			 Map<Integer,ArrayList<Double>> map = null;
		  // per evitare errori il numero di ingressi e uscite viene calcolato in base
		  // ai dati ;
		  // per le unit di input a tale numero viene aggiunto una unit bias
		  // di tipo neuron
		  // le classi di copdifica input e output quindi dovranno fornire due
		  // metodi : uno per restituire l'input j-esimo e uno per restituire
		  // il numero di ingressi/uscite
		  // se I/O  da file allora il metodo di acesso ai files che avr lo
		  // stesso nome e che far la stessa cosa.
		  
			 Network _net = null;
			 boolean success = false;
		  
			 double errorsum = 0.0;
			 int net_depth = 0; //The max depth of the network to be activated
			 int count = 0;
		  
		  
		  
		  //	  			   System.out.print("\n evaluate.step 1 ");
		  
			 double in[] = null;
			 in = new double[EnvConstant.NR_UNIT_INPUT + 1];
//			 in = new double[EnvConstant.NR_UNIT_INPUT];
		  
		  // setting bias
		  
			 in[EnvConstant.NR_UNIT_INPUT] = 1.0;
		  
			 double out[][] = null;
			 out = new double[EnvConstant.NUMBER_OF_SAMPLES][EnvConstant.NR_UNIT_OUTPUT];
		  
			 //double tgt[][] = null;
			 //tgt = new double[EnvConstant.NUMBER_OF_SAMPLES][EnvConstant.NR_UNIT_OUTPUT];
			 double tgt[][] = null;
			 tgt = new double[EnvConstant.NUMBER_OF_SAMPLES][EnvConstant.NR_UNIT_INPUT + MyConstants.SIM_TGT_OTHER_INFO_SIZE];
			 
		  
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
			 
//			 double minX = 40;
//			 double maxX = 60;	//X massima 100 [40+60]
//			 double minY = 20;
//			 double maxY = 80;	//Y massima 100 [20+80]
			 
//			 Random rx = new Random();
//			 long seedX = 10;
//			 rx.setSeed(seedX);
//			 Random ry = new Random();
//			 long seedY = 1000;
//			 ry.setSeed(seedY);
			 
//			 double[] inputX = new double[EnvConstant.NUMBER_OF_SAMPLES];
//			 double[] inputY = new double[EnvConstant.NUMBER_OF_SAMPLES];
//			 double[] inputX = {rx.nextDouble(), rx.nextDouble(), rx.nextDouble(), rx.nextDouble(), rx.nextDouble(),
//					 rx.nextDouble(), rx.nextDouble(), rx.nextDouble(), rx.nextDouble(), rx.nextDouble()};
//			 double[] inputY = {ry.nextDouble(), ry.nextDouble(), ry.nextDouble(), ry.nextDouble(), ry.nextDouble(),
//					 ry.nextDouble(), ry.nextDouble(), ry.nextDouble(), ry.nextDouble(), ry.nextDouble()};
//			 double[] inputX = {40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0, 45.0, 55.0, 65.0};
//			 double[] inputY = {70.0, 30.0, 20.0, 50.0, 60.0, 35.0, 45.0, 100.0, 75.0, 95.0};
			 
//			 for (int i=0; i<EnvConstant.NUMBER_OF_SAMPLES; i++)
//				 input[i]=Math.random();
			 


//			 for (count = 0; count < EnvConstant.NUMBER_OF_SAMPLES; count++) 
//			 {
////				 x = minX + Math.random()*maxX;
////				 y = minY + Math.random()*maxY;
////				 x = rx.nextFloat();
////				 y = ry.nextFloat();
//				 inputX[count] = x;
//				 inputY[count] = y;
//			 }
		  
			 if (EnvConstant.TYPE_OF_SIMULATION == EnvConstant.SIMULATION_FROM_CLASS)
			 {
			 
			 
			 // case of input from class java
			 
				try 
				{
				   //int plist_in[] = new int[2];
				   //Class[] params_inp = {int[].class};
				   //Object[] paramsObj_inp = new Object[] {plist_in};

//				   Class[] params_inp = {double.class};
//				   Object[] paramsObj_inp = new Object[] {y};
					 String mask6d = "  0.00000";
					 DecimalFormat fmt6d = new DecimalFormat(mask6d);
					 
					 Random rx = new Random();

					 Random ry = new Random();

					 Random rm = new Random();
					 
//					 //***** INPUT RIPETUTI *****//
//					 rx.setSeed(100);
//					 ry.setSeed(10000);
//					 rm.setSeed(1000);
					 
					 //***** INPUT RANDOM *****//
					 long seedX = (long)(Math.random()*100);	
					 rx.setSeed(seedX);
					 long seedY = (long)(Math.random()*10000);
					 ry.setSeed(seedY);
					 long seedM = (long)(Math.random()*1000);
					 rm.setSeed(seedM);
					 
					 HashMap<Integer,ArrayList<Double>> mappa= new HashMap<Integer,ArrayList<Double>>();
					 HashMap<Integer,ArrayList<Vector2d>> targetMap= new HashMap<Integer,ArrayList<Vector2d>>();
					 
				   for (count = 0; count < EnvConstant.NUMBER_OF_SAMPLES; count++) 
				   {
						 ArrayList<Double> arrayForza = new ArrayList<Double> ();
						 ArrayList<Vector2d> arrayTarget = new ArrayList<Vector2d> ();
//					   y = Math.random()*maxY;
//					   input[count] = y;
					  //plist_in[0] = count;
				   // first activation from sensor to first next level of neurons
//					  for (int j = 0; j < EnvConstant.NR_UNIT_INPUT; j++) 
//					  {
//						 //plist_in[1] = j;
//						 Method_inp = Class_inp.getMethod("getInput", params_inp);
//						 ObjRet_inp = Method_inp.invoke(ObjClass_inp, paramsObj_inp);
//						 double v1 = Double.parseDouble(ObjRet_inp.toString());
//						 in[j] = v1;
//					  }
					   

					   ///IMPLEMENTAZIONE DECISIONE DI LANCIO   
					   
					   double delta_t = 0.04;
					   double current_time = 0;
					   double minX = 20;
					   double maxX = 80;
					   double minY = 20;
					   double maxY = 80;
					   double minM = 1;
					   double maxM = 2;
					   double massa = minM + rm.nextDouble()*maxM;	// 1-3kg
					   double v = 0;
					   double minF = 15;	// forza minima
					   double maxF = 60;	// forza massima
					   double maxA = 1.5708;
					   double minV = 0;
					   double maxV = 150;
					   
					   in[0] = rx.nextDouble();
					   in[1] = ry.nextDouble();
					   in[2] = v;
					   
					   tgt[count][0] = in[0];
					   tgt[count][1] = in[1];
					   tgt[count][2] = in[2];
					   tgt[count][3] = massa;
					   
					   double x_tgt = minX + in[0]*maxX;
					   double y_tgt = minY + in[1]*maxY;
					   arrayTarget.add(new Vector2d(x_tgt, y_tgt));
					   
					   for (int i = 0; i<50; i++)
					   {
						   current_time += delta_t;
						   
						   // load sensor   
						   _net.load_sensors(in);
						   
						   if (EnvConstant.ACTIVATION_PERIOD == EnvConstant.MANUAL)
						   {
							   for (int relax = 0; relax < EnvConstant.ACTIVATION_TIMES; relax++)
							   {
								   success = _net.activate();
							   }
						   }
						   else
						   {   	            
							   //first activation from sensor to next layer....
							 success = _net.activate();
							 
						  // next activation while last level is reached !
						  // use depth to ensure relaxation
							 for (int relax = 0; relax <= net_depth; relax++)
							 {
								 success = _net.activate();
							 }
						   }
						   
						   //output
						   for( int j=0; j < EnvConstant.NR_UNIT_OUTPUT; j++)
						   {
							   out[count][j] = ((NNode) _net.getOutputs().elementAt(j)).getActivation();
						   }
						   
						   // clear net		 
						   _net.flush();
						   
						   double a = out[count][0]*maxA;
						   double F = minF + out[count][1]*maxF;
						   double lascia = out[count][2];
						   
						   double acc = F/massa;
						   double delta_v = acc*delta_t;
						   
						   v += delta_v;
						   
						   double V = (v - minV)/maxV;
						   
						   in[2] = V;
						   tgt[count][2] = v;
						   tgt[count][4] = a;
						   tgt[count][5] = F;
						   tgt[count][6] = acc;
						   tgt[count][7] = current_time;
						   
//						   double x_tgt = minX + tgt[count][0]*maxX;
//						   double y_tgt = minY + tgt[count][1]*maxY;
//						   
//						   x_tgt++;
//						   
//						   double X = (x_tgt - minX)/maxX;
//						   
//						   in[0] = X;
//						   tgt[count][0] = in[0];
						   
						   arrayForza.add(F);
						   
						   if (lascia >= 0.5) 
						   {
							   break;
						   }
					   }
					   
					   tgt[count][MyConstants.SIM_X0_TARGET_INDEX] = x_tgt;
					   tgt[count][MyConstants.SIM_Y0_TARGET_INDEX] = y_tgt;
			    	   tgt[count][MyConstants.SIM_BEST_TARGET_X_INDEX] = x_tgt;
			    	   tgt[count][MyConstants.SIM_BEST_TARGET_Y_INDEX] = y_tgt;
					   tgt[count][MyConstants.SIM_FIRST_X_TGT_INDEX] = x_tgt;
					   tgt[count][MyConstants.SIM_FIRST_Y_TGT_INDEX] = y_tgt;
					   
					   mappa.put(count, arrayForza);
					   targetMap.put(count, arrayTarget);
					   o.setForzaMap(mappa);
					   o.setTargetMap(targetMap);
					   
					   
			 ///IMPLEMENTAZIONE VECCHIA  				   
//					   
////				   in[0] = inputX[count];
////				   in[1] = inputY[count];
//				   in[0] = rx.nextDouble();
//				   in[1] = ry.nextDouble();
//				   in[2] = rm.nextDouble();
//				   //in[2] = rm.nextDouble();
////				   in[0] = NeatRoutine.randfloat();
////				   in[1] = NeatRoutine.randfloat();
//				   tgt[count][0] = in[0];
//				   tgt[count][1] = in[1];
//				   tgt[count][2] = in[2];
//				   //tgt[count][2] = in[2];
//
////				   System.out.println("------ LANCIO: "+count+" ------");
////				   System.out.println("INPUT 0:"+in[0]);
////				   System.out.println("INPUT 1:"+in[1]);
//				   // load sensor   
//					  _net.load_sensors(in);
//				   /*
//				   // activate net	  
//				   success = _net.activate();
//				   
//				   // next activation while last level is reached !
//				   // use depth to ensure relaxation
//				   
//				   for (int relax = 0; relax <= net_depth; relax++)
//					success = _net.activate();
//				   */
//				   
//					  if (EnvConstant.ACTIVATION_PERIOD == EnvConstant.MANUAL)
//					  {
//						 for (int relax = 0; relax < EnvConstant.ACTIVATION_TIMES; relax++)
//						 {
//							success = _net.activate();
//						 }
//					  }
//					  else
//					  {   	            
//					  // first activation from sensor to next layer....
////						  System.out.println("LANCIO "+count);
//						 success = _net.activate();
//						 
//					  // next activation while last level is reached !
//					  // use depth to ensure relaxation
//						 for (int relax = 0; relax <= net_depth; relax++)
//						 {
//							success = _net.activate();
//						 }
//					  }
//				   
//				   
//				   
//				   // for each sample save each output	
//					   
////					   System.out.println("INPUT X: " +inputX[count]);
////					   System.out.println("INPUT Y: "+inputY[count]);
////					  for( int j=0; j < EnvConstant.NR_UNIT_OUTPUT; j++){
////						  out[count][j] = ((NNode) _net.getOutputs().elementAt(j)).getActivation();
//////						  System.out.println(fmt6d.format(out[count][j]));
//////						  System.out.println();
////					  }
//		
//				   // for each sample save each output	
////					  System.out.println("ESEMPIO NUMERO: "+count);
//					  for( int j=0; j < EnvConstant.NR_UNIT_OUTPUT; j++)
//					  {
//						 out[count][j] = ((NNode) _net.getOutputs().elementAt(j)).getActivation();
////						 System.out.println(out[count][j]);
//					  }
////					  double o1 = ((NNode) _net.getOutputs().elementAt(0)).getActivation();
////					  double o2 = ((NNode) _net.getOutputs().elementAt(1)).getActivation();
////					  out[count][0] = o1;
////					  out[count][1] = o2;
////				  System.out.println(fmt6d.format(o1));
////				  System.out.println(fmt6d.format(o2));
////				  System.out.println();
//						 
//					  
//				   
//				   // clear net		 
//					  _net.flush();
				   }
				} 
				
					catch (Exception e2) 
				   {
					  System.out.print("\n Error generic in Generation.input signal : err-code = \n" + e2); 
					  System.out.print("\n re-run this application when the class is ready\n\t\t thank! "); 
					  System.exit(8);
				   
				   }
			 
			 }  
		  
		  //success = true;
		  
		  
		  // control the result 
			 if (success) 
			 {
				 Map<Integer, Vector2d> bestPoints = new HashMap<Integer, Vector2d>();
				try 
				{
					//SIMULAZIONE DI LANCIO PER CALCOLO DISTANZA MINIMA TRAIETTORIA-BERSAGLIO
					
					for (int i=0; i<EnvConstant.NUMBER_OF_SAMPLES; i++)
					{
						bestPoints.put(i, computeMinDistance(o, i, tgt));
					}
					
					o.setBestPoints(bestPoints);
					
					//CALCOLO FITNESS
				   Method_fit = Class_fit.getMethod("computeFitness", params);
				   ObjRet_fit = Method_fit.invoke(ObjClass_fit, paramsObj);
				   //System.out.println(ObjRet_fit);
//				   fit_dyn = Array.getDouble(ObjRet_fit, 0);
//				   err_dyn = Array.getDouble(ObjRet_fit, 1);
//				   win_dyn = Array.getDouble(ObjRet_fit, 2);
//				   angle = Array.getDouble(ObjRet_fit, 3);
//				   velocity = Array.getDouble(ObjRet_fit, 4);
//				   y_target = Array.getDouble(ObjRet_fit, 5);
//				   x_target = Array.getDouble(ObjRet_fit, 6);
				   HashMap<Integer,ArrayList<Double>> mappa = (HashMap<Integer, ArrayList<Double>>) ObjRet_fit;
				   ArrayList<Double> arrayBest = mappa.get(EnvConstant.NUMBER_OF_SAMPLES);
				   fit_dyn = arrayBest.get(MyConstants.FITNESS_TOTALE_INDEX);
				   err_dyn = arrayBest.get(MyConstants.ERRORE_INDEX);
				   win_dyn = arrayBest.get(MyConstants.WIN_INDEX);
				   angle = arrayBest.get(MyConstants.ANGOLO_INDEX);
				   velocity = arrayBest.get(MyConstants.VELOCITA_INDEX);
				   y_target = arrayBest.get(MyConstants.Y_TARGET_INDEX);
				   x_target = arrayBest.get(MyConstants.X_TARGET_INDEX);
				   total_err = arrayBest.get(MyConstants.ERRORE_TOTALE_INDEX);
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
				organism.setTotalError(total_err);
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
	   
	private Vector2d simulate(Organism o, int i, double[][] tgt) 
	{
		double minX = 20;
		double maxX = 80;
		double minY = 20;
		double maxY = 80;
		double y = 0;
		double a = tgt[i][4];
		double v = tgt[i][2];
		double x_tgt = minX + tgt[i][0]*maxX;
		double y_tgt = minY + tgt[i][1]*maxY;
		
		Vector2d target = new Vector2d(x_tgt, y_tgt);
		Vector2d bestPoint = new Vector2d(-1,-1);
		Vector2d currPoint = new Vector2d(-1, -1);
		
		double bestDistance = Double.MAX_VALUE;
		double currDistance = Double.MAX_VALUE;
		
		for (double x = 0; x<x_tgt+50; x++)
		{
				y = Math.tan(a)*x - ((MyConstants.GRAVITY/(2*Math.pow(v, 2)*Math.pow(Math.cos(a), 2)))*Math.pow(x, 2));
				
				if (y < 0)	break;
				
				currPoint.set(x, y);
				currDistance = target.distance(currPoint);
				
				if (currDistance < bestDistance)
				{
					bestDistance = currDistance;
					bestPoint = new Vector2d(x, y);
				}
		}
		
		tgt[i][8] = bestDistance;
		tgt[i][9] = bestPoint.x;
		tgt[i][10] = bestPoint.y;
		
		return bestPoint;
	}
	
	private Vector2d computeMinDistance(Organism o, int i, double[][] tgt) 
	{
		double minX = 20;
		double maxX = 80;
		double minY = 20;
		double maxY = 80;
		double a = tgt[i][4];
		double v = tgt[i][2];
		double x_tgt = minX + tgt[i][0]*maxX;
		double y_tgt = minY + tgt[i][1]*maxY;
		
		// Fisso un punto P = (x, f(x)) sulla parabola,
		// creo la funzione distanza d(x) = |P - T|, dove T = (x_tgt, y_tgt) � il target,
		// calcolo i minimi di d(x) (che � equivalente a calcolare i minimi di d^2(x)
		
		double[] coeff_parabola = {-(MyConstants.GRAVITY/(2*Math.pow(v, 2)*Math.pow(Math.cos(a), 2))), Math.tan(a), 0};
		Funzione parabola = new Funzione(coeff_parabola);	// funzione f della parabola
		
//		System.out.println("f(x) = " + parabola);
		
		Vector2d target = new Vector2d(x_tgt, y_tgt);	// punto T (target)
//		Vector2d punto = new Vector2d(x, parabola.getValue(x));	// punto P fissato sulla parabola
		
		//Distanza tra due punti al quadrato (quindi scompare la radice)
//		double distance = Math.pow((punto.x-target.x), 2) + 
//				Math.pow((coeff_parabola[0]*Math.pow(x_tgt, 2) + coeff_parabola[1]*punto.x -target.y), 2);
		
		double c0 = coeff_parabola[0];	// (-coeff???)
		double c1 = coeff_parabola[1];
		
		double[] coeff_distanza = {};
		
		Funzione distanza = new Funzione(coeff_distanza);		// funzione d(x)
		
		double[] coeff_distanzaQuad = {
				Math.pow(c0, 2),								// a0 (coeff x^4)
				(2*c0*c1),										// a1 (coeff x^3)
				1 + Math.pow(c1, 2) - 2*c0*target.y,			// a2 (coeff x^2)
				- (2*target.x) - 2*c1*target.y,					// a3 (coeff x^1)
				Math.pow(target.x, 2) + Math.pow(target.y, 2)	// a4 (coeff x^0)
				};
		
		Funzione distanzaQuad = new Funzione(coeff_distanzaQuad);	// funzione d^2(x)
		
//		System.out.println("d^2(x) = " + distanzaQuad);
		
		Funzione derivata = distanzaQuad.getDerivativeFunction();
		
//		System.out.println("d^2(x)' = " + derivata);
		
//		double sol = derivata.computeThirdDegreeEquationFormaDepressa();
		
//		double distQuad = Math.abs(derivata.getValue(sol));
		
//		double dist = Math.sqrt(distQuad);
		
		String mask6d;
		DecimalFormat fmt6d;
		mask6d = "  0.000000000000";
		fmt6d = new DecimalFormat(mask6d);
		
//		System.out.println(Math.sqrt(distanzaQuad.getValue(sol)));	// Questa � la distanza minima corretta!
		
//		System.out.println("distQUad: " + fmt6d.format(distQuad));
		
//		System.out.println("dist: " + fmt6d.format(dist));
		
		
		//SBAGLIATA!! Quella corretta � quella successiva!
//		double minDist = Math.sqrt(derivata.computeEquationFormaDepressa());
		
		//il valore ottenuto da computeEquation � il valore della x della funzione derivata della distanza al quadrato
		// perci� di derivata. Per ottenere la distanza minima al quadrato devo calcolare d^2(x) con il valore della x ottenuto e poi fare
		// la radice quadrata per ottenere la distanza minima d(x)
		
//		double sol = derivata.computeThirdDegreeEquationFormaDepressa();
		
		double soluzioni_x[] = derivata.computeThirdDegreeEquationFormaDepressa();
		
		double distanzaMinima = Double.POSITIVE_INFINITY;
		
		Vector2d bestPoint = new Vector2d();
		
		for (int j = 0; j<soluzioni_x.length; j++)
		{
			Vector2d temp = new Vector2d(soluzioni_x[j], parabola.getValue(soluzioni_x[j]));
			
			double dist = temp.distance(target);
			if (dist < distanzaMinima)
			{
				distanzaMinima = dist;	
				bestPoint = temp;
			}
		}
		
//		double minDist = Math.sqrt(distanzaQuad.getValue(sol));		// d(x) minima
		
//		System.out.println("minDist = " + minDist);
		
		
//		System.out.println("a = "+ a + " v = "+ v);
//		double x = sol;
//		double y = parabola.getValue(x);
//		y = parabola.getValue(x);
		
//		System.out.println("distanza^2 = " + distanza.getValue(x));
//		System.out.println("distanza = " + Math.sqrt(distanza.getValue(x)));
//		
//		System.out.println(derivata.getValue(x));
//		System.out.println(Math.sqrt(derivata.getValue(x)));
//		System.out.println(parabola.getValue(x));
		
//		Vector2d bestPoint = new Vector2d(x, y);
		
//		System.out.println("x :" + bestPoint.x + " y: " + bestPoint.y);
		
		double bestDistance = target.distance(bestPoint);
		
//		System.out.println(bestDistance == minDist);
		
		double y_tiro = Math.tan(a)*x_tgt - ((MyConstants.GRAVITY/(2*Math.pow(v, 2)*Math.pow(Math.cos(a), 2)))*Math.pow(x_tgt, 2));
		
//		System.out.println(bestDistance <= Math.abs(y_tgt-y_tiro));
//		System.out.println("bestDistance = " + bestDistance + " minDist = " + minDist + " prevErr = " + Math.abs(y_tgt-y_tiro));
		
//		if (distanzaMinima > Math.abs(y_tgt-y_tiro))
//		{
//			System.out.println("##### INIZIO #####" + "\n" + 
//								"DELTA = " + derivata.getDelta() + "\n" + 
//								"bestPoint = (" + bestPoint.x + " ," + bestPoint.y + ")" + "\n" +
//								"bestDistance = " + bestDistance + "\n" +
//								"target = (" + x_tgt + " ," + y_tgt + ")" + "\n" +
//								"prevPoint = (" + x_tgt + " ," + y_tiro + ")" + "\n" +
//								"prevDistance = " + Math.abs(y_tgt-y_tiro) + "\n"
//								);
//		}
		
//		if (distanzaMinima != bestDistance)
//		{
//			System.out.println("##### INIZIO #####" + "\n" + 
//					"DistanzaMinima: " + distanzaMinima + "\n" +
//								"BestDistance: " + bestDistance + "\n");
//		}
		
//		tgt[i][8] = minDist;
		tgt[i][8] = distanzaMinima;
		tgt[i][MyConstants.SIM_X_MIGLIORE_INDEX] = bestPoint.x;
		tgt[i][MyConstants.SIM_Y_MIGLIORE_INDEX] = bestPoint.y;
		
		return bestPoint;
	}

}
