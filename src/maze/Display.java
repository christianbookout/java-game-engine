package maze;

import java.awt.Dimension;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

import maze.entities.Camera;
import maze.entities.objects.Plane;
import maze.entities.objects.Polygon;
import maze.entities.objects.RPrism;
import maze.entities.objects.RandomShape;
import maze.managers.EntityManager;
import maze.managers.MovementManager;

/**
 * 
 */
public class Display extends JFrame implements Runnable{ 

    Renderer renderer;
    boolean isRunning;
    final int FRAMES_PER_SECOND = 25;
    final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

    public static int width, height;
    private static final String TITLE = "Maze Game";
    public static Camera mainCamera;
    public static EntityManager entityManager;
    public static MovementManager movementManager;

    public Display(int w, int h) {
        entityManager = new EntityManager();
        this.renderer = new Renderer(entityManager);
        Camera c = new Camera();
        entityManager.create(c);
        mainCamera = c;
        width = w;
        height = h;

        renderer.addKeyListener(movementManager = new MovementManager());

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(TITLE);
        this.setPreferredSize(new Dimension(width, height));
        this.add(renderer);
        this.setResizable(false);
        this.pack();

        isRunning = false;
    }


    @Override
    public void run() {
        //https://dewitters.com/dewitters-gameloop/

        //TODO maybe do a different gameloop system
        double nextGameTick = System.currentTimeMillis();
        double sleepTime = 0;
        isRunning = true;
        Plane p = new Plane(1000, 1000);
        p.translate(new Vector(0, 0, 30));
        entityManager.create(p);
        entityManager.create(new RandomShape(
            new Vector(10, 10, 30),
            new Vector(10, 20, 30),
            new Vector(50, 20, 30),
            new Vector(50, 0 , 30),
            new Vector(40, 0 , 30),
            new Vector(40, 10, 30)
        ));

        while (isRunning) {

            update();

            nextGameTick += SKIP_TICKS;
            sleepTime = nextGameTick - System.currentTimeMillis();
            if (sleepTime >= 0) {
                try {
                    Thread.sleep((long) sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("FPS too fast to handle");
            }
        }
    }

    /**
     * 
     * 
     */
    public synchronized void stop(){
        isRunning = false;
    }

    /**
     * 
     */
    private void update() {
        renderer.render();

        if (movementManager.left) {
            mainCamera.translate(new Vector(0, -MovementManager.SPEED, 0));
        } else if (movementManager.right) {
            mainCamera.translate(new Vector(0, MovementManager.SPEED, 0));
        }

        if (movementManager.up) {
            mainCamera.translate(new Vector(-MovementManager.SPEED*2, 0, 0));
        } else if (movementManager.down) {
            mainCamera.translate(new Vector(MovementManager.SPEED*2, 0, 0));
        }

        if (movementManager.lookLeft) {
            mainCamera.rotate(new Vector( 0, 0, -MovementManager.LOOK_SPEED));
        } else if (movementManager.lookRight) {
            mainCamera.rotate(new Vector( 0, 0, MovementManager.LOOK_SPEED));
        }
    }


    public static void main(String[] args) {
        Display display = new Display(800, 600);
        Thread displayThread = new Thread(display, "display");
        displayThread.start();
    }
}
