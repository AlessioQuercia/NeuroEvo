package myGui.io;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import static org.lwjgl.glfw.Callbacks.*;
import myGui.myGuiConstants;

public class Window 
{
	private long window;

	private int width, heigth;
	
	private boolean hasResized;
	private GLFWWindowSizeCallback windowSizeCallback;
	
	private Input input;
	
	private void setLocalCallbacks() {
		windowSizeCallback = new GLFWWindowSizeCallback() {
			
			@Override
			public void invoke(long argWindow, int argWidth, int argHeight) 
			{
				width = argWidth;
				heigth = argHeight;
				hasResized = true;
			}
		};
		
		glfwSetWindowSizeCallback(window, windowSizeCallback);
	}
	
	public Window() 
	{
		setSize(myGuiConstants.WIDTH, myGuiConstants.HEIGHT);
		hasResized = false;
	}
	
	public void createWindow(String title)
	{
		window = glfwCreateWindow(width, heigth, title, 0, 0);
		
		if (window == 0)
		{
			throw new IllegalStateException("Failed to create window!");
		}
		
		GLFWVidMode vid = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vid.width()-width)/2, (vid.height()-heigth)/2);
		
		glfwShowWindow(window);
		
		glfwMakeContextCurrent(window);
		
		input = new Input(window);
		setLocalCallbacks();
	}
	
	public void cleanUp()
	{
		glfwFreeCallbacks(window);
	}
	
	public boolean shouldClose()
	{
		return glfwWindowShouldClose(window);
	}
	
	public void swapBuffers()
	{
		glfwSwapBuffers(window);
	}
	
	public void setSize(int width, int height)
	{
		this.width = width;
		this.heigth = height;
	}
	
	public void update()
	{
		hasResized = false;
		input.update();
		glfwPollEvents();
	}
	
	public int getWidth() 
	{
		return width;
	}

	public int getHeigth() 
	{
		return heigth;
	}
	
	public boolean hasResized()
	{
		return hasResized;
	}
	
	public long getWindow()
	{
		return window;
	}
	
	public Input getInput()
	{
		return input;
	}
}
