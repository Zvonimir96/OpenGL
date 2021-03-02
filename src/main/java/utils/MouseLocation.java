package utils;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;

import main.Main;

public class MouseLocation {
	public static float X;
	public static float Y;
	
	public static void update() {
		DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(Main.WINDOW, b1, b2);
		
		X = (float) b1.get(0)/Main.WIDTH;
		Y = (float) b2.get(0)/Main.HEIGHT;
	}
}
