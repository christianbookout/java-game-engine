package maze.entities.objects;

import java.awt.Point;

import maze.Display;
import maze.Vector;

import java.awt.Color;

import java.util.Arrays;

public class Polygon extends java.awt.Polygon implements Comparable<Polygon>{
    
    private Color color;
    public static final Color DEFAULT_COLOR = Color.red;
    public Vector[] vertices;

    public Polygon(Color color, Vector... vertices) {
        this.color = color;
        this.vertices = new Vector[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            this.vertices[i] = vertices[i];
        }
        setPoints(vertices);
    }

    public Polygon(Vector... vertices) {
        this.color = DEFAULT_COLOR;
        this.vertices = new Vector[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            this.vertices[i] = vertices[i];
        }
        setPoints(vertices);
    }

    public void rotate(Vector toRotate) {
        removePoints();
        for (Vector v: this.vertices) {
            v.rotateX(toRotate.x);
            v.rotateY(toRotate.y);
            v.rotateZ(toRotate.z);
        }
        setPoints(vertices);
    }

    public Vector[] getVertices() {
        return vertices;
    }

    public void setPoints(Vector... vertices) {
        for (Vector v: vertices) {
            Point point = v.toScreenPoint();
            //point.x is an int but point.getX() is a double?????
            this.addPoint(point.x, point.y);
        }
    }

    public void removePoints() {
        this.reset();
    }

    public Color getColor() {
        return this.color;
    }

    /*public double getAverageX() {
        double average = 0;
        for (Vector v: vertices) {
            average += v.x;
        }
        return average / vertices.length;
    }*/

    public Vector getAveragePoint() {
        Vector sum = new Vector(0, 0, 0);
        for (Vector v: vertices) {
            sum.add(v);
        }
        int l = vertices.length;
        return new Vector(sum.x/l, sum.y/l, sum.z/l);
    }

    public Vector[] getClosestVectors(Vector toPos) {
        Vector[] closestVectors = new Vector[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            closestVectors[i] = vertices[i];
        }
        Arrays.sort(closestVectors);
        return closestVectors;
    }

    @Override
    public int compareTo(Polygon p) {
        Vector[] p1 = getClosestVectors(Display.mainCamera.getPosition());
        Vector[] p2 = p.getClosestVectors(Display.mainCamera.getPosition());
        for (int i = 0; i < (p1.length > p2.length ? p2.length : p1.length); i++) {
            double diff = p2[i].distance(Display.mainCamera.getPosition()) - p1[i].distance(Display.mainCamera.getPosition());
            if (diff == 0) continue;
            return diff > 0 ? 1 : -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Polygon)) return false;
        Polygon p = (Polygon) o;
        try{
            for (int i = 0; i < vertices.length; i++) {
                if (!Arrays.asList(vertices).contains(p.getVertices()[i])) return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

}
