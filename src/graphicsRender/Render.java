package graphicsRender;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL11.glClear;


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




		//Show the Display
		Display.create();

		//some GL11 Parameters
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		//Initialise Resources in OpenGL
		create();



		//call this to set initial size
		resize();

		//TODO Write small algorithm here to resize graphics when window is resized
		while (running && !Display.isCloseRequested()) {
			;

			// Render game
			draw();

			// Flip the buffers and sync to 60 FPS (Uses a double buffer system so what is stored in the other buffer will become displayed)
			Display.update();
			Display.sync(60);
		}

		// Dispose any resources and destroy the game window
		dispose();
		Display.destroy();

	}


	// Called to setup game and context
	protected void create() {


		// Enable blending - Without this, transparent sprites may not render
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


	// Game Loop
	protected void draw() {
		// Clear the screen and also the depth buffer, considering this is 3D
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);


		//game render logic here, can probably pass off to another class if it gets comprehensive
		//set quad colour
		GL11.glColor3f(0.5f,1.5f,1.0f);

		//draw quad vertices
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(200,100);
		GL11.glVertex2f(200+200,100);
		GL11.glVertex2f(200+200,100+200);
		GL11.glVertex2f(200,100+200);
		GL11.glEnd();
	}

}


