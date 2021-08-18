package maze.entities.objects;

import java.util.ArrayList;
import java.util.Stack;
import maze.Vector;


public class MazeShape extends GameObject{
    public MazeShape(boolean[][] maze) {
        //Vector[maze[0].length][maze.length];
        var polygons = new ArrayList<Polygon>();
        Stack<Integer[]> unviewedIndex = new Stack<>();
        for (int i = 0; i < maze[0].length; i++) {
            for (int j = 0; j < maze.length; j++) {
                unviewedIndex.add(new Integer[] {i, j});
            }
        }
    }

    
}
