package maze.entities.objects;

import java.util.ArrayList;
import java.awt.Color;

import maze.Vector;

public class RandomShape extends GameObject{
    public RandomShape(Vector... vertices) {
        super(generateShape(vertices));
    }

    private static Polygon[] generateShape(Vector... vertices) {
        int height = 50;
        Vector[] topVertices = new Vector[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            Vector v = vertices[i];
            vertices[i] = new Vector(v.x, v.y, 0);
            topVertices[i] = new Vector(v.x, v.y, height);
        }
        Polygon bottom = new Polygon(vertices);
        Polygon top = new Polygon(Color.orange, topVertices);

        var sides = new ArrayList<Polygon>();
        sides.add(top);
        sides.add(bottom);

        for (Vector v: vertices) {
            for (Vector v2: topVertices) {
                if ((v.x == v2.x ^ v.y == v2.y)) {

                    Color color = new Color((int)(Math.random() * 0x1000000));
                    Vector p1, p2, p3, p4;
                    p1 = new Vector(v.x, v.y, 0);
                    p2 = new Vector(v2.x, v2.y, 0);
                    p3 = new Vector(v2.x, v2.y, height);
                    p4 = new Vector(v.x, v.y, height);
                    Polygon p = new Polygon(
                        color, p1, p2, p3, p4
                    );
                    if (sides.contains(p)) continue;
                    sides.add(p);
                }
            }
        }
        return sides.toArray(new Polygon[0]);
    }
}