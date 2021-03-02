package objects;

import org.joml.Vector3f;

public class BasicSquare extends BasicObject{
	
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
	
	static float texCoords[] = {
			1.0f,  0.0f, 
			1.0f,  1.0f, 
			0.0f,  1.0f,
			0.0f,  0.0f, 
		};
	
	public static Vector3f GREEN_COLOR = new Vector3f(0f, 0.5f, 0f);
	public static Vector3f RED_COLOR = new Vector3f(1f, 0, 0);
	public static Vector3f BLUE_COLOR = new Vector3f(0, 0, 1f);
	public static Vector3f CLEAR_COLOR = new Vector3f(0, 0, 0);
	
	public BasicSquare(String textureName) {
		super(vertices, texCoords, indices, textureName);
		setScale(0.1f);
	}
}
