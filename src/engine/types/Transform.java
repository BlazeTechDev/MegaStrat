package engine.types;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transform {
    public Vector3f position = new Vector3f();
    public Vector3f rotation = new Vector3f();
    public Vector3f scale = new Vector3f(1,1,1);
    public Matrix4f matrix = new Matrix4f();

    public void calculateMatrix(Vector3f rotation){
        matrix.identity();
        matrix.translate(position);
        matrix.rotateAffineXYZ(this.rotation.x + rotation.x, this.rotation.y + rotation.y, this.rotation.z + rotation.z);
        matrix.scale(scale);
    }

    public void calculateMatrixPosition(Vector3f position){
        matrix.identity();
        matrix.translate(new Vector3f(this.position.x + position.x, this.position.y + position.y, this.position.z + position.z));
        matrix.rotateAffineXYZ(this.rotation.x, this.rotation.y, this.rotation.z);
        matrix.scale(scale);
    }

    public void calculateMatrix(){
        matrix.identity();
        matrix.translate(position);
        matrix.rotateAffineXYZ(this.rotation.x, this.rotation.y, this.rotation.z);
        matrix.scale(scale);
    }
}
