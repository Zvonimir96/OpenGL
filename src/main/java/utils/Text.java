package utils;

import java.util.ArrayList;

import org.joml.Vector2f;
import org.joml.Vector3f;

import objects.LetterObject;

public class Text{
	private ArrayList <LetterObject> letterList = new ArrayList<LetterObject>();
	private String text;
	private Vector2f position;
	private Vector2f startPosition;
	private float scale;
	private Vector3f color = new Vector3f(0,0,0);
	
	private static Vector2f OFFSET = new Vector2f(0.003f,0);
	
	private LetterObject subString;
	
	public Text(String text, float scale, Vector2f pos) {
		this.text = text;
		startPosition = new Vector2f(pos);
		position = new Vector2f(pos);
		position.add(OFFSET);
		this.scale = scale;

		for(int i=0; i<text.length(); i++) {
			char letter = text.charAt(i);
			subString = Letter.getLetter(letter);
			subString.setColor(color);
			
			subString.setPosition(position);
			subString.setScale(scale);
			
			position.x += (Letter.getAdvance(letter)/ Letter.UNIT)*scale/2;
			
			letterList.add(subString);
		}	
	}
	
	public Text(float scale, Vector2f pos) {
		startPosition = new Vector2f(pos);
		position = new Vector2f(pos);
		position.add(OFFSET);
		this.scale = scale;
	}
	
	public void setColor(Vector3f color) {
		this.color = color;
		
		for(int i=0; i<letterList.size(); i++)
			letterList.get(i).setColor(color);
	}
	
	public Vector2f getPosition() {
		return new Vector2f();
	}
	
	public Vector2f getSize() {
		Vector2f size = new Vector2f();
		size.y = letterList.get(0).getScaleVector().y/2f;
		size.x = position.x - startPosition.x;
		
		return size;
	}
	
	public void remouveLetter() {
		if(letterList.size()>0) {
			int index = letterList.size()-1;
			position.x -= (Letter.getAdvance(text.charAt(index-1))/ Letter.UNIT)*scale/2;
			letterList.remove(index);
		}
	}
	
	public void addLetter(char letter) {
		subString = Letter.getLetter(letter);
		text += subString;
		
		subString.setColor(color);
		subString.setPosition(position);
		subString.setScale(scale);
		
		position.x += (Letter.getAdvance(letter)/ Letter.UNIT)*scale/2;
		
		letterList.add(subString);
	}
	
	public void render() {
		for(int i=0; i<letterList.size(); i++)
			letterList.get(i).render();
	}
	
}

