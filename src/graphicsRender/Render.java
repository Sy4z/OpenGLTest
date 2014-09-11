package graphicsRender;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;

public class Render {

	// Width and Height of Render Window
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	boolean running = true;


	public static void main(String[] args) throws LWJGLException {
		new Render().display();

	}

	/**
	 * Method which starts the OpenGL Window
	 */
	public void display() throws LWJGLException{
		Display.setTitle("Render Window Example");
		Display.setResizable(false);
		Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
		Display.setVSyncEnabled(true);
		Display.setFullscreen(false);

		//Show the Display
		Display.create();

		//Initialise Resources in OpenGL
		create();

		//call this to set initial size
		resize();

		//TODO Write small algorithm here to resize graphics when window is resized
		  while (running && !Display.isCloseRequested()) {
	           ;

	            // Render game
	            render();

	            // Flip the buffers and sync to 60 FPS
	            Display.update();
	            Display.sync(60);
	        }
		  
		  // Dispose any resources and destroy our window
	        dispose();
	        Display.destroy();
		
	}


	// Called to setup our game and context
	protected void create() {
		// 2D games don't need depth testing
		glDisable(GL_DEPTH_TEST);

		// Enable blending
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		// Set clear to transparent black
		glClearColor(0f, 0f, 0f, 0f);

		// ... initialize resources here ...
	}

	// Called to resize our game
	protected void resize() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		// ... update projection matrices here ...
	}

	// Called to destroy our game upon exiting
	protected void dispose() {
		
		//destroy any textures or other assets here
	}


	// Called to render the game
    protected void render() {
        // Clear the screen
        glClear(GL_COLOR_BUFFER_BIT);

        //game render logic here, can probably pass off to another class if it gets comprehensive
    }

}


