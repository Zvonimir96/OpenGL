package objects;

import graphics.Model;
import graphics.Shader;
import graphics.texture.Texture;
import graphics.texture.TextureUtils;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class LetterObject extends RawObject{
	private Model model;
	private Texture texture;
	private Vector4f color;

	public LetterObject(float[] vertices, float[] texCoords, int[] indices, String textureName) {
		super("ColorObject");
		texture = TextureUtils.getTexture(textureName);
		model = new Model(vertices, texCoords, indices);
		color = new Vector4f(0,0,0,0);
	}
	
	public void setColor(Vector3f color) {
		this.color = new Vector4f(color,0); 
	}
	
	public void render() {
		Shader.enable(shader);
		texture.bind(0);
		Shader.setUniform("color", color);
		Shader.setUniform("projection", projection);
		model.render();
	}
}
