package maze.entities.objects;

import maze.Vector;
import java.awt.Color;

public class RPrism extends GameObject {
    public RPrism(int width, int depth, int height) {
        super(makeRPrism(width, depth, height));
    }

    private static Polygon[] makeRPrism(int width, int depth, int height) {
        Vector p1 = new Vector(-width/2, depth/2, -height/2);
        Vector p2 = new Vector(width/2, depth/2, -height/2);
        Vector p3 = new Vector(width/2, -depth/2, -height/2);
        Vector p4 = new Vector(-width/2, -depth/2, -height/2);
        Vector p5 = new Vector(-width/2, depth/2, height/2);
        Vector p6 = new Vector(width/2, depth/2, height/2);
        Vector p7 = new Vector(width/2, -depth/2, height/2);
        Vector p8 = new Vector(-width/2, -depth/2, height/2);

        Polygon top = new Polygon(Color.red, p1, p2, p3, p4);
        Polygon bottom = new Polygon(Color.blue, p5, p6, p7, p8);
        Polygon side1 = new Polygon(Color.pink, p1, p2, p6, p5);
        Polygon side2 = new Polygon(Color.cyan, p2, p3, p7, p6);
        Polygon side3 = new Polygon(Color.green, p3, p4, p8, p7);
        Polygon side4 = new Polygon(Color.white, p4, p1, p5, p8);

        return new Polygon[]{bottom, side1, side2, side3, side4, top};
    }
    
}
