package gui;

public class InputStepOne 
{
	   public static int getNumSamples() { return 1; } 
	   
	   public static int getNumUnit()    { return 1; } 
	   
	   public static double getInput()
	   { 
	      double y = Math.random()*100;
	 
	      return y; 
	   } 
}
