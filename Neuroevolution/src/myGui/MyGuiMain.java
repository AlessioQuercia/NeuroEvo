package myGui;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.opengl.GL11.*;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL;

import myGui.io.Timer;
import myGui.io.Window;
import myGui.render.Camera;
import myGui.render.Model;
import myGui.render.Shader;
import myGui.render.Texture;

public class MyGuiMain 
{
	public MyGuiMain() 
	{
		if (!glfwInit())
		{
			throw new IllegalStateException("Failed to initialize GLFW!");
		}
		
		Window win = new Window();
		
		win.createWindow("New NEAT GUI");
		
		GL.createCapabilities();
		
		Camera cam = new Camera(myGuiConstants.WIDTH, myGuiConstants.HEIGHT);
		
		glEnable(GL_TEXTURE_2D);
		
		float[] vertices = new float[] {
				-0.5f, 0.5f, 0,		//TOP LEFT	0
				0.5f, 0.5f, 0,		//TOP RIGHT	1
				0.5f, -0.5f, 0,		//BOTTOM RIGHT	2
				-0.5f, -0.5f, 0,	//BOTTOM LEFT	3
		};
		
		float[] texture = new float[] {
				0,0,
				1,0,
				1,1,
				0,1,
		};
		
		int[] indices = new int[] {
				0,1,2,
				2,3,0
		};
		
		Model model = new Model(vertices, texture, indices);
		
		Shader shader = new Shader("shader");
		
		Texture tex = new Texture("G:\\Documenti\\Nuova cartella\\junkrat.png");
		
		Matrix4f scale = new Matrix4f()
				.translate(new Vector3f(100, 0, 0))
				.scale(64);
		Matrix4f target = new Matrix4f();
		
		Gui gui = new Gui(model);
		
		double frame_cap = 1.0/60.0; 	//60 frame al secondo
		
		double frame_time = 0;
		int frames = 0;
		
		double time = Timer.getTime();
		double unprocessed = 0;		//tempo in cui il gioco non è ancora stato processato
		
		
		while (!win.shouldClose())
		{	
			boolean can_render = false;
			double time_2 = Timer.getTime();
			double passed = time_2 - time;
			target = scale;
			unprocessed+=passed;
			frame_time+=passed;
			
			time = time_2;
			float x = -6f;
			float y = -4;
			while (unprocessed >= frame_cap)
			{
				if (win.hasResized())
				{
					cam.setProjection(win.getWidth(), win.getHeigth());
					glViewport(0, 0, win.getWidth(), win.getHeigth());
				}
				
				unprocessed-=frame_cap;
				can_render = true;
				
				if (win.getInput().isKeyPressed(GLFW_KEY_ESCAPE))
				{
					glfwSetWindowShouldClose(win.getWindow(), true);
				}
				
//				if (win.getInput().isMouseButtonPressed(GLFW_MOUSE_BUTTON_LEFT))
//				{
//					glfwSetWindowShouldClose(win.getWindow(), true);
//				}
				
				win.update();
				if(frame_time >= 1.0)
				{
					frame_time = 0;
					System.out.println("FPS: " + frames);
					frames = 0;
				}
			}
			
			if (can_render)
			{
				glClear(GL_COLOR_BUFFER_BIT);
				glEnable(GL_BLEND);
				glColor4f(1, 0, 0 , 0.5f);
				glBegin(GL_QUADS);
					glVertex2f(x,y);
					glVertex2f(x+4,y);
					glVertex2f(x+4,y+6);
					glVertex2f(x,y+6);
				glEnd();
				glDisable(GL_BLEND);
				
				shader.bind();
				shader.setUniform("sampler", 0);
				shader.setUniform("projection", cam.getProjection().mul(target));
				model.render();	
				tex.bind(0);
				
				gui.render(cam, win);
				
				win.swapBuffers();
				frames++;
			}
//			if (glfwGetKey(window, GLFW_KEY_A) == GL_TRUE) 
//			{
//				blue = 1;
//				red = 0;
//			}
//			else 
//			{
//				red = 1;
//				blue = 0;
//			}
//			
//			if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE) 
//			{
//				glfwSetWindowShouldClose(window, true);
//			}
//			
//			if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_LEFT)== GL_TRUE) 
//			{
//				blue = 1;
//				red = 0;
//			}
		}
		
		glfwTerminate();
	}
	public static void main(String[] args) 
	{
		new MyGuiMain();
	}
}
