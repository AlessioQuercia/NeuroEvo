package newGui;

import org.joml.Vector2d;

public class Funzione 
{
	private int grado;
	private double[] coeff;
	
	public Funzione(double [] coeff) 
	{
		this.grado = coeff.length-1;
		this.coeff = coeff;
	}
	
	public int getGrado() 
	{
		return grado;
	}

	public double[] getCoeff()
	{
		return coeff;
	}

	public double getValue(double x)
	{
		double value = 0;
		double grado = getGrado();
		
		for (int i=0; i<coeff.length; i++)
		{
			value += coeff[i]*Math.pow(x, grado--);
		}
		
		return value;
	}
	
	public double[] getDerivativeValue()
	{
		double[] coeff_der = new double[coeff.length-1];
		double grado = getGrado();
		
		for (int i=0; i<coeff.length-1; i++)
		{
			coeff_der[i] = coeff[i]*grado--;
		}
		
		return coeff_der;
	}
	
	public Funzione getDerivativeFunction()
	{
		return new Funzione(getDerivativeValue());
	}
	
	public Vector2d computeEquation()
	{
		Vector2d point = new Vector2d(0,0);
		
		double x = 0;
		double y = 0;
		
		//risoluzione funzione di terzo grado
		if (getGrado() == 3)
		{
			double a = coeff[0];
			double b = coeff[1];
			double c = coeff[2];
			double d = coeff[3];
			
			double p = (c/a) - (Math.pow(b, 2))/(3*Math.pow(a, 2));
			
			double q = (d/a) - (b*c)/(3*Math.pow(a, 2)) + (2*Math.pow(b, 3)/(27*Math.pow(a, 3)));
			
			double delta = (Math.pow(q, 2)/4) + (Math.pow(p, 3)/27);
			
			System.out.println((-q/2) + Math.sqrt( delta ));
			System.out.println((-q/2) - Math.sqrt( delta ));
			
			double u = Math.pow(( (-q/2) + Math.sqrt( delta )), 1.0/3.0);
			
			double v = Math.pow(( (-q/2) - Math.sqrt( delta )), 1.0/3.0);
			
			System.out.println("u: " + u + " v: " + v);
			
			double[] coeff_temp = {1, 0, p, q};
			
			Funzione temp = new Funzione(coeff_temp);
			
			double y1 = 0;
			double y2 = 0;
			double y3 = 0;
			
			if (delta > 0)
			{
				System.out.println("DELTA > 0");
				
				y1 = u + v;
			}
			else if (delta == 0)
			{
				System.out.println("DELTA = 0");
				
				y1 = -2*Math.pow((q/2), 1/3);
				y2 = y3 = Math.pow((q/2), 1/3);
			}
			else
			{
				System.out.println("DELTA < 0");
			}
			
			y = Math.max(y1, y2);
			
			x = y - b/(3*a);
			
		}
		else
		{
			System.out.println("L'equazione non è di terzo grado!");
		}
		
		point.set(x, y);
		
		System.out.println("x: " + x +" y: "+y);
		
		return point;
	}
}
