package maze.entities;

import maze.Vector;

public interface Entity {
    public void translate(Vector toTranslate);
    public void rotate(Vector toRotate);
}
