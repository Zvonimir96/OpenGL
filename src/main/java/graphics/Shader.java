package graphics;

import static org.lwjgl.opengl.GL20.*;

import main.Main;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import utils.FileUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class Shader {
	
	public static final int VERTEX_ATTRIB = 0;
	public static final int TEXTURE_COORDINATES = 1;
	
	private static HashMap<String, Integer> PROGRAM_LIST = new HashMap<String, Integer>();
	private static HashMap<String, Integer> UNIFORM_LIST = new HashMap<String, Integer>();
	private static String SHADER_LOCATION = "./Shaders";
	
	private int vs;
	private int fs;
	
	public static void load() {		
		ArrayList<String> shaderNames = utils.FileUtils.review(SHADER_LOCATION);
		
		for(int i=0; i<shaderNames.size(); i++)
			new Shader(shaderNames.get(i));

	}
	
	public Shader(String fileName) {		
		vs = glCreateShader(GL_VERTEX_SHADER);
		String vertex = FileUtils.read(SHADER_LOCATION + "/" + fileName + "/vertex");
		glShaderSource(vs, vertex);
		glCompileShader(vs);
		if(glGetShaderi(vs, GL_COMPILE_STATUS) != 1) {
			System.err.println(glGetShaderInfoLog(vs));
			System.exit(1);
		}
		
		fs = glCreateShader(GL_FRAGMENT_SHADER);
		String fragment = FileUtils.read(SHADER_LOCATION + "/" + fileName + "/fragment");
		glShaderSource(fs, fragment);
		glCompileShader(fs);
		if(glGetShaderi(fs, GL_COMPILE_STATUS) != 1) {
			System.err.println(glGetShaderInfoLog(fs));
			System.exit(1);
		}
		
		int program = glCreateProgram();
		
		glAttachShader(program, vs);
		glAttachShader(program, fs);
		
		glLinkProgram(program);
		if(glGetProgrami(program, GL_LINK_STATUS) != 1) {
			System.err.println(glGetProgramInfoLog(program));
			System.exit(1);
		}
		
		glValidateProgram(program);
		if(glGetProgrami(program, GL_VALIDATE_STATUS) != 1) {
			System.err.println(glGetProgramInfoLog(program));
			System.exit(1);
		}
		
		PROGRAM_LIST.put(fileName,program);
		
		setUniforms(vertex, fileName);
		setUniforms(fragment, fileName);
		
		glDeleteShader(vs);
		glDeleteShader(fs);
	}
	
	public void setUniforms(String text, String fileName) {
		int counter=0;
		int startIndex = 0;
		
		while(text.indexOf("uniform ", startIndex)!=-1) {
			String variableName = "";
			startIndex = text.indexOf(" ", text.indexOf("uniform ", startIndex)+8)+1;
			
			for(int i=startIndex; text.charAt(i) != ';' ;i++) 
				variableName += text.charAt(i);
			
			if(!variableName.equals("Vertices") && !variableName.equals("TexturePosition")) 
				UNIFORM_LIST.put(variableName, glGetUniformLocation(PROGRAM_LIST.get(fileName), variableName));
			
			if(counter == 20) {
				System.out.println("Error: Shader loading");
				Main.stop();
				break;
			} else counter++;
		}
	}
	
	public static void setUniform(String name, Vector3f value){
		int location = UNIFORM_LIST.get(name);
		
		glUniform3f(location, value.x, value.y, value.z);
	}
	
	public static void setUniform(String name, Vector4f value){
		int location = UNIFORM_LIST.get(name);
		
		glUniform4f(location, value.x, value.y, value.z, value.w);
	}
	
	public static void setUniform(String name, Vector2f value){
		int location = UNIFORM_LIST.get(name);
		
		glUniform2f(location, value.x, value.y);
	}
	
	public static void setUniform(String name, Matrix4f value){
		int location = UNIFORM_LIST.get(name);
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		value.get(buffer);
		
		glUniformMatrix4fv(location, false, buffer);
	}
	
	public static void setUniform(String name, float value){
		int location = UNIFORM_LIST.get(name);
		
		glUniform1f(location, value);
	}
	
	public static void enable(String fileName) {
		glUseProgram(PROGRAM_LIST.get(fileName));
	}
}