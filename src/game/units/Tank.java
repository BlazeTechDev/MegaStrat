package game.units;

import engine.core.Globals;
import engine.core.Runtime;
import engine.events.KeyboardManager;
import engine.importer.EntityImporter;
import engine.structure.Entity;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class Tank extends Entity {
    public Tank(){
        if(Runtime.isRunning){
            meshInstance = EntityImporter.loadMeshFromFile("src/resources/meshes/tank.fbx", Globals.entityShader, this);

            meshInstance.hasPhysics = true;
            meshInstance.collider.radius = 0;
            meshInstance.transform.position.y = 15;

            meshInstance.transform.rotation.x = (float)Math.toRadians(-90);
            meshInstance.transform.scale = new Vector3f(0.1f, 0.1f, 0.1f);
            meshInstance.bounceCoefficient = 0.2f;
        }
    }

    @Override
    public void start() {
        meshInstance = EntityImporter.loadMeshFromFile("src/resources/meshes/tank.fbx", Globals.entityShader, this);

        meshInstance.hasPhysics = true;
        meshInstance.collider.radius = 0;
        meshInstance.transform.position.y = 15;
        meshInstance.transform.rotation.x = (float)Math.toRadians(-90);
        meshInstance.transform.scale = new Vector3f(0.1f, 0.1f, 0.1f);
        meshInstance.bounceCoefficient = 0.2f;
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {

    }
}
