package com.aod6061.lwjgl;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

/**
 * This is just some runner class to run the application. 
 * @author Fred
 *
 */
public class Runner {
	
	// This is the GLFWErrorCallback for error messages from GLFW
	private GLFWErrorCallback errorCallback;
	
	
	// This is the window ID
	private long windowID;
	
	
	// Size of window
	// Width and Height of window
	private int width = 800; // Def width of 800px
	private int height = 600; // Def height of 600px
	/**
	 * I'm being lazy, I'm just throwing this stuff into
	 * the constructor lol.
	 * @throws Exception 
	 */
	public Runner() throws Exception {
		// Inits stuff
		init();
		// Main loop or heartbeat of the appilcation
		loop();
		// This will release stuffs
		release();
	}
	
	/**
	 * This inits stuff
	 * @throws Exception 
	 */
	private void init() throws Exception {
		// Set the errorCallback to the System.err PrintStream
		this.errorCallback = Callbacks.errorCallbackPrint(System.err);
		// Set the errorCallback to the glfwCallback method 
		GLFW.glfwSetErrorCallback(errorCallback);
		
		// Init glfwInit()
		if(GLFW.glfwInit() != GL11.GL_TRUE) {
			throw new Exception("GLFW didn't create for somereason.");
		}
		
		// This load default window hints.
		GLFW.glfwDefaultWindowHints();
		// I don't want a visible window
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
		// I don't want the window to resize, because I'm being 
		// lazy...
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_FALSE);
		
		// Create the window and grab the window ID
		windowID = GLFW.glfwCreateWindow(this.width, this.height, "Hell, World :D", 0, 0);
		
		// Setting the window Position to [32, 32] sounds safe to me
		GLFW.glfwSetWindowPos(windowID, 32, 32);
		
		// This makes the OpenGL context current.
		GLFW.glfwMakeContextCurrent(windowID);
		
		GLFW.glfwShowWindow(windowID);
		
		// this binds the opengl context
		GLContext.createFromCurrent();
	}
	
	/**
	 * This is in a forever loop until it 
	 * exits
	 */
	private void loop() {
		
		while(GLFW.glfwWindowShouldClose(windowID) == GL11.GL_FALSE) {
			
			// This clears the screen to magenta :3
			GL11.glClearColor(1.0f, 0.0f, 1.0f, 1.0f);
			// This clears both the depth buffer and color buffer
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			// Add more stuff here 
			GLFW.glfwSwapBuffers(this.windowID);
			// Poll for events ;3
			GLFW.glfwPollEvents();
		}
	}
	
	/**
	 * This releases stuff
	 */
	
	private void release() {
		// Time to destroy the window
		GLFW.glfwDestroyWindow(this.windowID);
		GLFW.glfwTerminate();
		errorCallback.release();
	}
	
	/**
	 * lol throws exception. I'm being a terrible java programmer lol.
	 * don't do this in practice kids our your boss will have your head 
	 * on a spear at the end of the day. ;D
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new Runner();
	}
}
