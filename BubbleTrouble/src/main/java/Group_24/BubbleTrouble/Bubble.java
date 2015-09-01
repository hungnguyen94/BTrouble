package Group_24.BubbleTrouble;

public class Bubble {
	private int size;
	private double game_size;
	
	// location
	private double x;
	private double y;
	
	// speed (pixels / step)
	private double vx;
	private double vy;
	
	// acceleration (pixels / step^2)
	private double ay;
	
	// gravity
	private final double g = .01;
	// starting speed in horizontal direction
	private final double speed = .5;
	
	/**
	 * Bubble class, containing all the data about the bubble.
	 * 
	 * @param size of the bubble.
	 * @param game_size actual size of a level one bubble in the game.
	 * @param x horizontal starting position of the bubble in the room
	 * @param y vertical starting position of the bubble in the room
	 */
	public Bubble(int size, double game_size, double x, double y) {
		this.size = size;
		this.game_size = game_size;
		this.x = x;
		this.y = y;
		
		this.ay = g;
		this.vx = speed;
	}

	public int getSize() {
		return size;
	}
	
	public int getRealSize() {
		return (int) (size * game_size);
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}
	
	public void move() {
		vy += ay;
		
		x += vx;
		y += vy;
	}
	
	public static final int COLLISIONTYPE_FLOOR = 0;
	public static final int COLLISIONTYPE_WALL = 1;
	public void collide(int type){
		switch(type){
			case COLLISIONTYPE_FLOOR: vy = -vy; break;
			case COLLISIONTYPE_WALL: vx = -vx; break;
			default: return;
		}
	}
}
