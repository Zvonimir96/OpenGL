package graphics.texture;

import java.util.ArrayList;

public class Animation {
	
	private ArrayList<Texture> animation;
	private Integer animationIndex;
	
	public Animation() {
		animation = new ArrayList<Texture>();
		animationIndex = 0;
	}
	
	public void addTexture(Texture newTexture) {
		animation.add(newTexture);
	}
	
	public Texture getTexture() {
		return animation.get(animationIndex);
	}
	
	public void nextAnimation() {
		animationIndex++;
		if(animationIndex>(animation.size()-1)) animationIndex = 0;
	}
	
	public void setAnimation(Integer index) {
		if(index>(animation.size()-1)) animationIndex = animation.size()-1;
		else if(index<0) animationIndex = 0;
		else animationIndex = index;
	}

}
