package objects;

import graphics.Model;
import graphics.Shader;
import graphics.texture.Texture;
import graphics.texture.TextureUtils;

public class BasicObject extends RawObject{	
	private Model model;
	private Texture texture;
	
	public BasicObject(float[] vertices, float[] texCoords, int[] indices, String textureName) {
		super("BasicObject");
		texture = TextureUtils.getTexture(textureName);
		model = new Model(vertices, texCoords, indices);
	}
	
	public void render() {
		Shader.enable(shader);
		texture.bind(0);
		Shader.setUniform("projection", projection);
		model.render();
	}
}
