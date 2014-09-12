package graphicsRender;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
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

/**
 * Class which Renders an Animated Square in OpenGL
 */
public class Render {

	// Width and Height of Render Window
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	/** angle of rotation for square*/
	float rotation = 0;
	/** position of Current square vertice */
	float x = 300, y = 200;



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
		while (!Display.isCloseRequested()) {
			;

			// Render game
			moveObject();
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


		// Enable blending - Without this, transparent sprites may not render correctly
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		// Set clear to transparent black
		glClearColor(0f, 0f, 0f, 0f);

		// ... initialize resources here ...
	}

	// Called to resize our game
	protected void resize() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		//update projection matrices here
	}

	// Called to destroy game upon exiting
	protected void dispose() {

		//destroy any textures or other assets here
	}


	// Game Loop
	protected void draw() {
		// Clear the screen and also the depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);


		//game render logic here, can probably pass off to another class if it gets comprehensive
		//set quad colour

		GL11.glColor3f(0.5f,1.5f,0.5f);



		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(rotation, 0f, 0f, 1f);
		GL11.glTranslatef(-x, -y, 0);


		//draw quad vertices
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x,y);
		GL11.glVertex2f(x+80,y);
		GL11.glVertex2f(x+80,y+80);
		GL11.glVertex2f(x,y+80);
		GL11.glEnd();

		GL11.glPopMatrix();



	}

	/**
	 * Method which makes calculations for movement based on keypresses
	 */
	public void moveObject(){
		//rotate by x degrees while it is moving;
		
		
		//Left
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			x-=0.5f;
			rotation -= 0.15f;
		}
		//Right
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			x+=0.5f;
			rotation += 0.15f;
		}
		//Up
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			y+=0.5f;
			rotation += 0.15f;
		}
		//Down
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			y-=0.5f;
			rotation -= 0.15f;
		}
		
		




	}

}


