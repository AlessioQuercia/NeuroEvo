package myGui;

import static org.lwjgl.opengl.GL11.*;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

import myGui.io.Window;
import myGui.render.Camera;
import myGui.render.Model;
import myGui.render.Shader;

public class Gui 
{
	private Shader shader;
	private Model model;
	
	public Gui(Model model) 
	{
		shader = new Shader("gui");
		this.model = model;
	}
	
	public void render(Camera camera, Window window)
	{
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
//		glDisable(GL_DEPTH_TEST);
		
		Matrix4f mat = new Matrix4f();
		camera.getUntransformedProjection().scale(87, mat);
		mat.translate(1, 1, 0);
		shader.bind();
		
		shader.setUniform("projection", mat);
		shader.setUniform("color", new Vector4f(1,0,0,0.2f));
		
		model.render();
//		glEnable(GL_DEPTH_TEST);
		glDisable(GL_BLEND);
	}
}
