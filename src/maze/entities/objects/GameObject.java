package maze.entities.objects;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Arrays;

import maze.Display;
import maze.Vector;
import maze.entities.Entity;

public abstract class GameObject implements Entity{

    private Polygon[] polygons;
    
    GameObject(Polygon... polygons) {
        this.polygons = new Polygon[polygons.length];
        for (int i = 0; i < polygons.length; i++) {
            this.polygons[i] = polygons[i];
        }
    }

    public void translate(Vector toTranslate) {
        for (Polygon p: polygons) {
            p.reset();
            for (Vector v: p.getVertices()) {
                v.translate(toTranslate);
            }
            p.setPoints(p.vertices);
            //sort();
        }
    }

    public void rotate(Vector toRotate) {
        var rotated = new LinkedList<Vector>();
        for (Polygon p: polygons) {
            p.reset();
            for (Vector v: p.getVertices()) {
                if (!rotated.contains(v)) {
                    v.rotateX(toRotate.x);
                    v.rotateY(toRotate.y);
                    v.rotateZ(toRotate.z);
                    rotated.add(v);
                }
            }
            p.setPoints(p.vertices);
            
        }
        sort();
    }

    public void render(Graphics g) {
        for (Polygon p: polygons) {
            if (!isVisible(p)) continue;

            //re-render all polygons relative to the camera position
            p.reset();
            p.setPoints(p.vertices);
            g.setColor(p.getColor());
            g.fillPolygon(p);
        }
    }

    public boolean isVisible(Polygon p) {
        Double v = Display.mainCamera.getPosition().x - p.getAveragePoint().x;
        return v > 0;
    }

    public void updateLighting() {

    }

    public void sort() {
        Arrays.sort(polygons);
    }
}
