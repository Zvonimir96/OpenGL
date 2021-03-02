package objects;

import graphics.Model;
import graphics.Shader;
import graphics.texture.Animation;
import graphics.texture.TextureUtils;

import java.util.HashMap;

public class BasicAnimation extends RawObject{
	private Model model;
	private HashMap<String, Animation> animation;
	private Integer animationTime;
	private Integer counter;
	private String animationName;
	
	public BasicAnimation(float[] vertices, float[] texCoords, int[] indices, String animationName) {
		super("BasicObject");
		animation = TextureUtils.getAnimation(animationName);
		animationTime = 15;
		counter = 0;
		animationName = "ideal";
		model = new Model(vertices, texCoords, indices);
	}
	
	public void setAnimation(String animationName) {
		if(this.animation.get(this.animationName)!=null)
			animation.get(this.animationName).setAnimation(0);
		this.animationName = animationName;
	}
	
	public void setAnimationTime(Integer animationTime) {
		this.animationTime = animationTime;
	}
	
	public String getAnimation() {
		return animationName;
	}
	
	public void render() {
		if(animationTime<=counter) {
			counter = 0;
			animation.get(animationName).nextAnimation();
		}
		
		counter++;

		Shader.enable(shader);
		animation.get(animationName).getTexture().bind(0);
		Shader.setUniform("projection", projection);
		model.render();
	}
}
