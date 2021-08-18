package maze.entities;

import maze.Vector;

public class Camera implements Entity{
    private Vector position;
    private Vector rotation;

    public Camera(Vector position, Vector rotation) {
        this.position = position;
        this.rotation = rotation;
    }
    public Camera() {
        position = new Vector(0, 0, 0);
        this.rotation = new Vector(0, 0, 0);
    }
    public Vector getPosition() {
        return position;
    }
    public Vector getRotation() {
        return rotation;
    }
    @Override
    public void translate(Vector toTranslate) {
        double radius = Vector.magnitude(toTranslate.y, toTranslate.x);
		double theta = Math.atan2(toTranslate.y, toTranslate.x) - Math.toRadians(rotation.z);
        position.add(new Vector(radius*Math.cos(theta), radius*Math.sin(theta), toTranslate.z));
    }
    @Override
    public void rotate(Vector toRotate) {
        rotation.add(toRotate);
    }
}
