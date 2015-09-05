package Group_24.BubbleTrouble;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Sprite extends Object{
    protected boolean visible;
    protected Image image;

    public Sprite(int x, int y) {
    	super(x,y);
        this.visible = true;
    }
    
    protected abstract void init(String img);
    
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }    

    public Image getImage() {
        return image;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

	@Override
	public void drawObject(Graphics2D g, View view) {
		g.drawImage(this.getImage(), this.getX(), this.getY(), view);
	}
}
