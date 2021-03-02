package graphics.texture;

import main.Main;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class TextureUtils {
	
	private static HashMap<String, Texture> PICTURES = new HashMap<String, Texture>();
	private static HashMap<String, HashMap<String, Animation>> ANIMATIONS = new HashMap<String, HashMap<String, Animation>>();
	
	private static String PICTURE_LOCATION = "./Textures/Pictures";
	private static String ANIMATION_LOCATION = "./Textures/Animations";

	public static void load() {	
		ArrayList<String> texturesReview = FileUtils.review(PICTURE_LOCATION);
		
		for(int i=0; i<texturesReview.size(); i++) {
			String textureName = texturesReview.get(i).replace(".png","");
			PICTURES.put(textureName, new Texture(PICTURE_LOCATION + "/" + textureName));
		}
		
		ArrayList<String> animationsReview = FileUtils.review(ANIMATION_LOCATION);
		
		for(int i=0; i<animationsReview.size(); i++) {	
			String animations = ANIMATION_LOCATION + "/" + animationsReview.get(i);
			ArrayList<String> animationNames = FileUtils.review(animations);
			HashMap<String, Animation> animationColection = new HashMap<String, Animation>();
			
			for(int j=0; j<animationNames.size(); j++) {
				ArrayList<String> animationTextures = FileUtils.review(animations + "/" + animationNames.get(j));
				Animation animation = new Animation();
				
				for(int z=0; z<animationTextures.size(); z++) {
					String textureName = animationTextures.get(z).replace(".png","");
					animation.addTexture(new Texture(animations + "/" + animationNames.get(j) + "/" + textureName));
				}
				animationColection.put(animationNames.get(j), animation);
			}
			
			ANIMATIONS.put(animationsReview.get(i),animationColection);
		}
	}
	
	public static Texture getTexture(String name) {
		if(PICTURES.containsKey(name)) return PICTURES.get(name);
		else {
			System.out.println("Tekstura ne postoji: " + name);
			Main.stop();
			return null;
		}
	}
	
	public static HashMap<String, Animation> getAnimation(String characterName) {
		if(ANIMATIONS.containsKey(characterName)) return ANIMATIONS.get(characterName);
		else {
			System.out.println("Animacija za charaktera ne postoji " + characterName);
			Main.stop();
			return null;
		}
	}
	
	public static Animation getAnimation(String characterName, String animationName) {
		if(ANIMATIONS.containsKey(characterName)) {
			if(ANIMATIONS.get(characterName).containsKey(animationName))
				return ANIMATIONS.get(characterName).get(animationName);
			
			else {
				System.out.println("Animacija ne postoji: " + animationName);
				Main.stop();
				return null;
			}
		}
		else {
			System.out.println("Animacija za charactera ne postoji: " + characterName);
			return null;
		}
	}
}

