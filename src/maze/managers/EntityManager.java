package maze.managers;

import java.util.ArrayList;

import maze.entities.Camera;
import maze.entities.Entity;
import maze.entities.objects.GameObject;

public class EntityManager {

    public ArrayList<GameObject> objects = new ArrayList<GameObject>();
    public ArrayList<Camera> cameras = new ArrayList<Camera>();

    public void create(Entity entity) {
        if (entity instanceof GameObject) {
            objects.add((GameObject) entity);
        } else if (entity instanceof Camera) {
            cameras.add((Camera) entity);
        }
    }
    
}
