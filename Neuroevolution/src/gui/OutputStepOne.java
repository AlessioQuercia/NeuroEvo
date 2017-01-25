package gui;

public class OutputStepOne
{
	   public static int getNumUnit() { return 2; } 
	   
	   public static double[] getTarget( int _plist[]) 
	   { 
		   double x_target=0;
		   
		   double gravity = 0;
		   
		   double speed = 0;
		   
		   double angle = 0;
		   
		   double[] target = {speed, angle};
		   
		   double y_output = Math.tan(angle)*x_target-(gravity/(2*Math.pow(speed, 2)*Math.pow(Math.cos(angle), 2)))*Math.pow(x_target, 2);
		   return target;
	   } 
}
