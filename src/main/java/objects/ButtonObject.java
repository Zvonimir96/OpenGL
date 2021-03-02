package objects;

import graphics.Model;
import graphics.Shader;
import main.Main;
import org.joml.Vector2f;

public class ButtonObject{
	private Model model;
	private String shader = "Button";
	private Vector2f position;
	private Vector2f size;
	private float highLight;
	
	static float vertices[] = {
		     1.0f,   1.0f, 0.0f,
		     1.0f,  -1.0f, 0.0f,
		    -1.0f,  -1.0f, 0.0f,
		    -1.0f,   1.0f, 0.0f,
		};
		
	static int indices[] = {
			0, 1, 2,
			0, 2, 3,
		}; 
	
	public ButtonObject(Vector2f size, Vector2f position) {
		model = new Model(vertices, indices);
		highLight = 0;
		this.position = new Vector2f (position);
		this.size = new Vector2f (size);
	}
	
	public void highLight() {
		highLight = 0.2f;
	}
	
	public void disableHighLight() {
		highLight = 0;
	}
	
	public void render() {
		Shader.enable(shader);
		Shader.setUniform("position", position);
		Shader.setUniform("size", size);
		Shader.setUniform("highLight", highLight);
		Shader.setUniform("height",(float) Main.HEIGHT);
		Shader.setUniform("width",(float)  Main.WIDTH);
		model.render();
	}
}
