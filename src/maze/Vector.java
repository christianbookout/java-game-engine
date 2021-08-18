package maze;

import java.awt.Point;

public class Vector implements Comparable<Vector>{

    public static final Vector ORIGIN = new Vector(0, 0, 0);
    public double x, y, z;
    
    private static double scale = 20;
    private static final double zoomFactor = 1.1;

    public static void zoomIn() {
        scale *= zoomFactor;
    }

    public static void zoomOut() {
        scale /= zoomFactor;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void translate(Vector translation) {
        this.x += translation.x;
        this.y += translation.y;
        this.z += translation.z;
    }

    public double dot(Vector other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public Vector cross(Vector other) {
        double x1 = y * other.z - z * other.y;
        double y1 = z * other.x - x * other.z;
        double z1 = z * other.x - x * other.z;
        return new Vector(x1, y1, z1);
    }

    public Vector normalize() {
        double magnitude = magnitude(x, y, z);
        return new Vector(x/magnitude, y/magnitude, z/magnitude);
    }

    public void add(Vector other) {
        x += other.x;
        y += other.y;
        z += other.z;
    }

    public void subtract(Vector other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
    }

    public double distance(Vector other) {
        return Vector.magnitude(x - other.x, y - other.y, z - other.z);
    }

    public Vector getRelativePosition() {
        return new Vector(
            x - Display.mainCamera.getPosition().x, 
            y - Display.mainCamera.getPosition().y, 
            z - Display.mainCamera.getPosition().z
        );
    }

    public Point toScreenPoint() {
        Vector relativePos = getRelativePosition();

        double radius = Vector.magnitude(relativePos.y, relativePos.x);
		double theta = Math.atan2(relativePos.y, relativePos.x);
		theta += 2*Math.PI/360*Display.mainCamera.getRotation().z;

		relativePos.y = radius * Math.sin(theta);
		relativePos.x = radius * Math.cos(theta);


        double[] vectorScale = scale(
            relativePos.y * scale, 
            relativePos.z * scale, 
            relativePos.x * scale
        );

        int x = (int) ((Display.width/2 + vectorScale[0]));
        int y = (int) ((Display.height/2 + vectorScale[1]));

        return new Point(x, y);
    }

    private double[] scale(double x, double y, double depth) {
        double magnitude = magnitude(x, y);
        double radians = Math.atan2(y, x);
        double depth2 = 15 - depth;
        double localScale = Math.abs(1400/(depth2 + 1400));

        magnitude *= localScale;

        return new double[]{
            magnitude * Math.cos(radians),
            magnitude * Math.sin(radians)
        };
    }

    public void rotateX(double degrees) {
        double[] rotate = rotate(degrees, y, z);
        y = rotate[0];
        z = rotate[1];
    }

    public void rotateY(double degrees) {
        double[] rotate = rotate(degrees, x, z);
        x = rotate[0];
        z = rotate[1];
    }

    public void rotateZ(double degrees) {
        double[] rotate = rotate(degrees, x, y);
        x = rotate[0];
        y = rotate[1];
    }

    private double[] rotate(double degrees, double a, double b) {
        double magnitude = magnitude(a, b);
        double angle = Math.atan2(b, a);
        angle += (2*Math.PI/360)*degrees;
        return new double[] {
            magnitude * Math.cos(angle),
            magnitude * Math.sin(angle)
        };
    }

    public double magnitude() {
        return Vector.magnitude(x, y, z);
    }
    public static double magnitude(double... coords) {
        double sum = 0;
        for (double coord: coords) {
            sum += Math.pow(coord, 2);
        }
        return Math.sqrt(sum);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Vector)) return false;
        var vector = (Vector) other;
        return vector.x == x && vector.y == y && vector.z == z;
    }

    @Override
    public int compareTo(Vector o) {
        return o.distance(Display.mainCamera.getPosition()) - distance(Display.mainCamera.getPosition()) < 0 ? 1 : -1;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}