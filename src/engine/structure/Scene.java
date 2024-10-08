package engine.structure;

import engine.graphics.Instance;
import engine.graphics.ParticleInstance;
import engine.graphics.ParticleRenderer;
import engine.types.Transform;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Scene {
    public Vector3f lightDirection = new Vector3f(-0.3f, -1, -0.7f);
    public Camera camera = new Camera();
    public Sun sun = new Sun();

    public HashMap<Integer, Entity> entities = new HashMap<>();
    public ArrayList<Instance> instances = new ArrayList<>();
    public HashMap<Integer, ParticleInstance> particles = new HashMap<>();

    public void addEntity(Entity entity){
        if(entity.meshInstance != null) {
            entity.meshInstance.loadMeshes();
        }
        entities.put(entity.id, entity);
    }
    public void addParticle(ParticleInstance particle){
        particles.put(particle.id, particle);
    }

    public void addInstance(Instance instance){
        instances.add(instance);
    }

    public Entity getEntity(int id){
        return entities.get(id);
    }

    public ParticleInstance getParticle(int id){
        return particles.get(id);
    }

    public Transform getInstanceTransform(int id){
        for(Instance instance : instances){
            if(instance.getTransformFromId(id) != null){
                return instance.getTransformFromId(id);
            }
        }

        return null;
    }

    public Instance getInstance(int id){
        return instances.get(id);
    }

    public abstract void load();
    public abstract void update();
    public abstract void end();
}
