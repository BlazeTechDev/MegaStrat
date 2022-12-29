package engine.core;

import engine.events.MouseManager;
import engine.graphics.FrameBuffer;
import engine.graphics.Instance;
import engine.graphics.RenderQuad;
import engine.graphics.EntityRenderer;
import engine.platform.PlatformResources;
import engine.structure.Entity;
import engine.structure.Scene;
import game.scene.Battlefield;
import testing.MaterialTest;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.opengl.GL11.*;

public class Runtime {
    public static Display display;
    public static Scene currentScene = new Battlefield();
    public static FrameBuffer frameBuffer;
    public static void start(Display display){
        Runtime.display = display;

        PlatformResources.getSystemStats();

        currentScene.load();
        for(Entity entity : currentScene.entities.values()){
            entity.start();
            entity.meshInstance.loadMeshes();
        }
        for(Instance instance : currentScene.instances.values()){
            instance.loadMesh();
        }

        frameBuffer = new FrameBuffer(display.width / Globals.resolution, display.height / Globals.resolution);

        RenderQuad.prepare();

        Globals.load();
    }

    public static void loop(){
        currentScene.update();
        currentScene.camera.calculateMatrix();
        currentScene.sun.calculateMatrix();

        frameBuffer.bind();

        glEnable(GL_DEPTH_TEST);

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        for(Entity entity : currentScene.entities.values()){
            entity.update();
            entity.meshInstance.sendToRender();
        }
        for(Instance instance : currentScene.instances.values()){
            instance.sendToRender(currentScene.camera.view);
        }

        frameBuffer.samplePosition();

        if(MouseManager.isButtonDown(GLFW_MOUSE_BUTTON_1)) {
            Entity entity = currentScene.getEntity(frameBuffer.sampleId());
            System.out.println(entity);
        }

        frameBuffer.unbind();

        frameBuffer.blit(display.width, display.height);

        glDisable(GL_DEPTH_TEST);

        EntityRenderer.lightingPass(frameBuffer);
    }

    public static void end(){
        currentScene.end();
        for(Entity entity : currentScene.entities.values()){
            entity.end();
        }
    }
}
