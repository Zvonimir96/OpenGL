package objects;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class RawObject {
	
	protected Matrix4f projection;
	protected String shader;
	protected Vector3f rotation;
	
	public RawObject(String shader) {
		projection = new Matrix4f();
		rotation = new Vector3f();
		this.shader = shader;
	}
	
	public void setPosition(Vector2f position) {
		projection._m30(position.x*2-1);
		projection._m31(-position.y*2+1);
	}
	
	public void addPosition(Vector2f position) {
		setPosition(getPosition().add(position, new Vector2f()));
	}
	
	public Vector2f getPosition() {
		Vector2f vector = new Vector2f();
		vector.x = (projection.m30()+1)/2;
		vector.y = Math.abs((projection.m31()-1)/2);
		return vector;
	}
	
	public void setScale(float scale) {
		projection._m00(1);
		projection._m11(1);
		projection.scale(scale);
	}
	
	public void setScale(float x, float y) {
		projection._m00(1);
		projection._m11(1);
		projection.scale(x,y,1);
	}
	
	public void setScale(Vector2f scale) {
		projection._m00(1);
		projection._m11(1);
		projection.scale(scale.x,scale.y,1);
	}
	
	public float getScale() {
		return projection.m00();
	}
	
	public Vector2f getScaleVector() {
		Vector2f scale = new Vector2f();
		scale.x = projection.m00();
		scale.y = projection.m11();
		return scale;
	}
	
	public void setAngleX(float angle) {
		projection.rotate(-rotation.x, 1, 0, 0);
		rotation.x = angle;
		projection.rotate(angle, 1, 0, 0);
	}
	
	public void setAngleY(float angle) {
		projection.rotate(-rotation.y, 0, 1, 0);
		rotation.y = angle;
		projection.rotate(angle, 0, 1, 0);
	}
	
	public void setAngleZ(float angle) {
		projection.rotate(-rotation.z, 0, 0, 1);
		rotation.z = angle;
		projection.rotate(angle, 0, 0, 1);
	}
	
	public Vector3f getRotation() {
		return rotation;
	}
	
	public void rotate(float angle, Vector3f rotation) {
		this.rotation = rotation;
		projection.rotate(angle, rotation);
	}
}
