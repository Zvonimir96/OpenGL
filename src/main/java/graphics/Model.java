package graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Model 
{
	private int count;
	private int vao, vbo, ibo, tbo;
	private int ID;
	
	public Model(float[] vertices, float[] texCoords, int[] indices){
		count = indices.length;
		
		vao = glGenVertexArrays();		
		glBindVertexArray(vao);
		
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, createBuffer(indices), GL_STATIC_DRAW);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
		
		tbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, tbo);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(texCoords), GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.TEXTURE_COORDINATES, 2, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
		
		ID = 0;
	}
	
	public Model(float[] vertices, int[] indices){
		count = indices.length;
		
		vao = glGenVertexArrays();		
		glBindVertexArray(vao);
		
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, createBuffer(indices), GL_STATIC_DRAW);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
		
		ID = 1;
	}
	
	public void render() {
		switch(ID) {
			case 0:
				glBindVertexArray(vao);
				glEnableVertexAttribArray(Shader.VERTEX_ATTRIB);
				glEnableVertexAttribArray(Shader.TEXTURE_COORDINATES);
				glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0);
				glDisableVertexAttribArray(Shader.VERTEX_ATTRIB);
				glDisableVertexAttribArray(Shader.TEXTURE_COORDINATES);
				glBindVertexArray(0);
				break;
			
			case 1:
				glBindVertexArray(vao);
				glEnableVertexAttribArray(Shader.VERTEX_ATTRIB);
				glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0);
				glDisableVertexAttribArray(Shader.VERTEX_ATTRIB);
				glBindVertexArray(0);
		}
	}
	
	
	private FloatBuffer createBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private IntBuffer createBuffer(int[] data){
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
}
