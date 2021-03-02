package objects;

public class Background extends BasicObject{

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
		
	private static String textureName = "backGround";
	
	public Background() {
		super(vertices, texCoords, indices, textureName);
	}	
}
