package maze;

import java.util.Arrays;
import java.util.Random;
/*
Begin by making a random starting cell part of the Maze. Proceed by picking a random cell not already part of the  Maze, 
and doing a random walk until a cell is found which is already part of the Maze.  Once the already created part of  the 
Maze is hit, go back to the random cell that was picked, and carve along the path that was taken, adding those cells to 
the Maze. More specifically, when retracing the path, at each cell carve along  the direction that the random walk most 
recently  took when it left that cell.  That avoids  adding loops  along the retraced path,  resulting in a single long 
passage  being  appended  to   the  Maze.   The  Maze  is  done  when  all  cells   have  been  appended  to  the  Maze.
*/
public class MazeGenerator {
    static class Position {
        public final int x, y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        static Position getRandom(int width, int height) {
            Random random = new Random();
            return new Position(random.nextInt(width), random.nextInt(height));
        }
    }
    public boolean[][] createMaze(int width, int height) {
        boolean[][] maze = new boolean[width][height];
        boolean[][] joined = new boolean[width][height];

        //setTrue()

        
        for(int remainingCells = width*height-1; remainingCells >= 0;) {
            Position startPos = Position.getRandom(width, height);
            Position currPos = startPos;

            
        }


        return maze;
    }

    public static void setTrue(Position pos, boolean[][]... mazes) {
        //maze[pos.x][pos.y] = true;
    }
}
