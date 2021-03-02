package main;

import graphics.Shader;
import graphics.texture.TextureUtils;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import utils.Input;
import utils.Timer;
import utils.Letter;

public class Main implements Runnable{
	
	public static long WINDOW;
	public static int WIDTH = 1000;
	public static int HEIGHT = 700;
	private Thread thread;
	public static Input INPUT;
	
	private static boolean RUNNING = false;
	public static float FPS = 60f;
	
	public static Vector3f FRAME_POSITION = new Vector3f(0.8f, 0.9f, 0);
	
	public static void main(String[] args) {
		new Main().start();
	}
	
	public void start() {
		RUNNING = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	public void run() {
		init();
		
		double frame_cap = 1.0/FPS;
		double time = Timer.getTime();
		double unprocessed = 0;
		double frame_time = 0;
		
		while(RUNNING) {
			boolean can_render = false;
			double time_2 = Timer.getTime();
			double passed = time_2 - time;
			unprocessed+=passed;
			frame_time+=passed;
			time = time_2;
			
			if(unprocessed >=frame_cap) {
				unprocessed-=frame_cap;
				can_render = true;
				
				update();
				
				if(frame_time >= 1.0) {
					frame_time = 0; 
				}
			}
			
			if(can_render) {				
				render();
			}
			
			if(glfwWindowShouldClose(WINDOW))
				RUNNING = false;
		}
		glfwTerminate();
	}
	
	public static void stop() {
		RUNNING = false;
	}
	
	private void init() {
		if(!glfwInit()) return;
		
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
	    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		
		WINDOW = glfwCreateWindow(WIDTH, HEIGHT, "Test", NULL, NULL);
		if(WINDOW == NULL) return;
		
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(WINDOW, (vidmode.width()-WIDTH)/2, (vidmode.height()-HEIGHT)/2);
		glfwMakeContextCurrent(WINDOW);
		glfwShowWindow(WINDOW);
		GL.createCapabilities();
		//glViewport(0, 0, width, height);
		
		glfwSetKeyCallback(WINDOW, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true);
		});
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		System.out.println("OpengGL " + glGetString(GL_VERSION));
		glClearColor(1f, 1f, 1f, 0f);
		
		TextureUtils.load();
		Shader.load();
		Letter.load();

		INPUT = new Input();
		}
	
	
	private void update() {
		glfwPollEvents();
		//INPUT.update();
	}
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		//glfwSwapBuffers(WINDOW);
	}

}