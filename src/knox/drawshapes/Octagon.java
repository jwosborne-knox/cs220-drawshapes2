package knox.drawshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Octagon extends AbstractShape implements IShape {

    protected int r;
    protected Point center;
    
    public Octagon(Point center, int r, Color color){
    	super(new Point(center.x, center.y), color);
        boundingBox = new BoundingBox(center.x - r, center.x + r, center.y - r, center.y + r);
        this.r = r;
        this.center = center;
    }

    /* (non-Javadoc)
     * @see drawshapes.sol.Shape#draw(java.awt.Graphics)
     */
    @Override
    public void draw(Graphics g) {
        if (isSelected()){
            g.setColor(color.darker());
        } else {
            g.setColor(getColor());
        }
        //it wouldn't let me put in the raw arrays in the fill or the polygon so here I am
        int sideLengthOverTwo = (int) ((double) ((double)Math.tan(Math.PI/8)) * r); //this is to simplify the arrays by doing the trig part seperatly, alst the casting to double is just me being safe that teh math is done right before it becomes an int
        int[] xInts = {
	        		center.x+sideLengthOverTwo, 
	        		center.x-sideLengthOverTwo, 
	        		center.x-r, 
	        		center.x-r, 
	        		center.x-sideLengthOverTwo,
	        		center.x+sideLengthOverTwo, 
	        		center.x+r, 
	        		center.x+r
        		};
        int[] yInts = {
	        		center.y+r, 
	        		center.y+r, 
	        		center.y+sideLengthOverTwo, 
	        		center.y-sideLengthOverTwo, 
	        		center.y-r, 
	        		center.y-r, 
	        		center.y-sideLengthOverTwo, 
	        		center.y+sideLengthOverTwo
        		};
        Polygon p = new Polygon(xInts, yInts, 8);
        g.fillPolygon(p);
    }

    public String toString() {
        return String.format("Octagon (%d) r=%d color=%s selected? %s", 
                getAnchorPoint().x,
                getAnchorPoint().y,
                r,
                Util.colorToString(getColor()),
                selected);
    }
    
    public String encode() {
    	return String.format("Octagon %d %d %s %s", 
                getAnchorPoint().x,
                getAnchorPoint().y,
                r,
                Util.colorToString(getColor()),
                selected);
    }
    
	@Override
	public void scale(double factor) {
		this.r = (int)(this.r * factor);
	}

}
