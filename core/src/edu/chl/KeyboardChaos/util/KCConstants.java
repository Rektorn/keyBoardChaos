package edu.chl.KeyboardChaos.util;

import java.util.concurrent.ConcurrentHashMap;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
/*
 * This class holds all the used constants of the game KeyboardChaos
 */
public class KCConstants {
	
	// Enum describing a direction
	public static enum Direction {
	    UP, DOWN, RIGHT, LEFT, UPPERRIGHT, UPPERLEFT, LOWERRIGHT, LOWERLEFT,  NAN
	}
	
	// Main window width and height
	public static final int GAME_WIDTH = 1440, GAME_HEIGHT = 960;
	
	//Pixels per meter ratio
	public static final int PPM = 100;
	
	//Bits used to decide which bodies that will collide with what
	
	public static final short BIT_PLAYER = 0x0001;
	public static final short BIT_SPELL = 0x0002;
	public static final short BIT_OBSTACLE = 0x0004;
	public static final short BIT_LAVA = 0x0008;
	public static final short BIT_INVISIBLE_WALL = 0x00016;
	
	public static final short MASK_PLAYER = BIT_SPELL | BIT_OBSTACLE | BIT_LAVA | BIT_PLAYER;
	public static final short MASK_SPELL = BIT_PLAYER | BIT_OBSTACLE | BIT_INVISIBLE_WALL;
	public static final short MASK_OBSTACLE = BIT_PLAYER | BIT_SPELL;
	public static final short MASK_LAVA = BIT_PLAYER;
	public static final short MASK_INVISIBLE_WALL = BIT_SPELL;
	
	//Updates per second
	public static final float TIME_STEP = 1 / 60f;
	
	//Gravity of world - probably not gonna be used
	
	//public static final Vector2 GRAVITY = new Vector2(0f, -9.82f);
	public static final Vector2 GRAVITY = new Vector2(0f, 0);
	
	
	//Game state ID numbers
	
	public static final int PLAY_STATE = 17*5*37*41;
	public static final int MENU_STATE = 19*13*51*43;
	public static final int UNKNOWN_STATE = 11*31*39*67;
}
