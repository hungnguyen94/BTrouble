package Group_24.BubbleTrouble;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Sprite extends Object{
    protected boolean visible;
    protected Image image;
    
    /**
     * Constructs a new Sprite at a position, and sets it to visible.
     * @param x should be an integer representing the horizontal position of the Sprite.
     * @param y should be an integer representing the vertical position of the Sprite.
     */
    public Sprite(int x, int y) {
    	super(x,y);
        this.visible = true;
    }
    
    /**
     * Forces its children to provide an image which is used to draw the sprite.
     * @param img should be a String representing the path to an image file.
     */
    protected abstract void init(String img);
    
    /**
     * Loads the image into the Sprite.
     * @param imageName should be the name of the image which is loaded into the Sprite.
     */
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    
    /**
     * Sets the dimensions of the object to the dimensions of the image loaded into the Sprite. 
     */
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }    
    
    /**
     * Returns the image loaded into the Sprite. 
     * @return returns the image loaded into the Sprite.
     */
    public Image getImage() {
        return image;
    }
    
    /**
     * Returns whether the Sprite is visible
     * @return returns a boolean representing whether the Sprite is visible
     */
    public boolean isVisible() {
        return visible;
    }
    
    /**
     * Sets the visibility of the Sprite to true or false. 
     * @param visible should be a boolean representing whether the Sprite is visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    /**
	 * Draws the object using the image loaded into the Sprite.
	 * @param g should be the graphics object in which the Object is drawn.
	 * @param v should be the view in which the Object is drawn.
	 */
	@Override
	public void drawObject(Graphics2D g, View view) {
		g.drawImage(this.getImage(), this.getX(), this.getY(), view);
	}
}
