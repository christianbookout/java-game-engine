package maze.entities.objects;

import maze.Vector;

public class Plane extends GameObject{
     
    public Plane(int width, int depth) {
        super(planeGenerator(width, depth));
    }

    private static Polygon planeGenerator(int width, int depth) {
        return new Polygon(
            new Vector(width/2, depth/2, 0),
            new Vector(-width/2, depth/2, 0),
            new Vector(-width/2, -depth/2, 0),
            new Vector(width/2, -depth/2, 0)
            );
    }
}
