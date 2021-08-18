package maze;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;

import maze.entities.objects.GameObject;
import maze.entities.objects.RPrism;
import maze.entities.objects.RandomShape;
import maze.managers.EntityManager;


//https://gamedev.stackexchange.com/questions/10030/how-do-3d-game-engines-render-3d-environments-to-a-2d-screen

//TODO
//https://stackoverflow.com/questions/22431620/java-canvas-draw-image-from-bufferedimage
//https://stackoverflow.com/questions/7047749/painting-pixels-images-in-java
//http://alienryderflex.com/polygon_fill/
public class Renderer extends Canvas {

    private static final Color BACKGROUND_COLOR = Color.black;

    EntityManager manager;
    public Renderer(EntityManager manager) {
        this.manager = manager;
    }
    
    BufferStrategy buffer;
    int toRotate = 1;
    //RPrism cube = new RPrism(100, 50, 30);
    public void render() {
        if ((buffer = this.getBufferStrategy()) == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = buffer.getDrawGraphics();

        //Draw background
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, 0, Display.width * 2, Display.height * 2);

        manager.objects.forEach(s -> {
            //s.translate(new Vector(0, 0.0001, 0));
            //if (s instanceof RandomShape)
            //s.rotate(new Vector(1, 0, 0));
            s.sort();
            s.render(graphics);
        });

        graphics.dispose();
        buffer.show();
    }
    


}
