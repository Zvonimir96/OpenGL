package utils;

import static org.lwjgl.glfw.GLFW.*;

import main.Main;

public class Input{
	private long window;
	private int keys[] = {GLFW_KEY_A, GLFW_KEY_D, GLFW_KEY_H, GLFW_KEY_S};
	private int button[] = {GLFW_MOUSE_BUTTON_LEFT};
	private boolean keyValue[] = new boolean[keys.length];
	private boolean buttonValue[] = new boolean[button.length];
	
	public Input() {
		window = Main.WINDOW;
		for(int i=0; i<keys.length; i++)
			keyValue[i] = false;
		for(int i=0; i<button.length; i++)
			buttonValue[i] = false;
	}
	
	public Integer witchKeyIsPressed() {
		for(int i=0; i<keys.length; i++) {
			if(glfwGetKey(window, keys[i]) == 1 && keyValue[searchKey(keys[i])]==false) 
				return keys[i];
		}
		
		return -1;
	}
	
	public boolean isKeyDown(int key) {
		return(glfwGetKey(window, key) == 1);
	}
	
	public boolean isKeyPressed(int key) {
		return(glfwGetKey(window, key) == 1 && keyValue[searchKey(key)]==false);
	}
	
	public boolean isMouseButtonPressed(int button) {
		return(glfwGetMouseButton(window, button) == 1 && buttonValue[searchButton(button)]==false);
	}
	
	public boolean isMouseButtonDown(int button) {
		return(glfwGetMouseButton(window, button) == 1);
	}
	
	public boolean isKeyRealeased(int key) {
		return(glfwGetKey(window, key) == 0 && keyValue[searchKey(key)]==true);
	}
	
	public void update() {
		for(int i=0; i<keys.length; i++) 
			keyValue[i] = (glfwGetKey(window, keys[i])==1);
		
		for(int i=0; i<button.length; i++) 
			buttonValue[i] = (glfwGetMouseButton(window, button[i])==1);
		
		MouseLocation.update();
	}
	
	public int searchKey(int key){
		int i=0;
		for(i=0; i<keys.length; i++)
			if(keys[i] == key) break;
		return i;
	}
	
	public int searchButton(int key){
		int i=0;
		for(i=0; i<button.length; i++)
			if(button[i] == key) break;
		return i;
	}
}