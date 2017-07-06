
/*************************************************************************
 * Name: Point.java
 * Date: 29-May-2016
 *
 * Compilation:  javac Point.java
 *
 * Description: An immutable data type for points in the plane.
 *
*@author Vivek Roshan
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
		
		@Override
		//this method returns 1,0,-1 accordingly.
		public int compare(Point arg0, Point arg1) {
			if(slopeTo(arg0)-slopeTo(arg1) == 0 ) //if slope(invoking,arg0) = slope (invoking,arg1) return 0
				return 0;
			else if(slopeTo(arg0)-slopeTo(arg1) > 0 ) //return 1
 				return 1;
			else                   //return -1
				return -1;
			
		}
	}; //This Comparator SLOPE_ORDER is useful when we try to sort the points according to the slopes they make with an invoking(this) point.

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    // this method is not required, but for receiving bonus points 
    public void draw() {
    	StdDraw.point(x, y); //this method draws the invoking point onto the canvas.
    }

    // draw line between this point and that point to standard drawing
    // this method is not required, but for receiving bonus points 
    public void drawTo(Point that) {
         /* YOUR CODE HERE */
    	StdDraw.line(x, y, that.x, that.y); //this method StdDraw.line draws a line from the invoking point to the end point
    	//this draws a line, given a start point and end point
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
    	if (this.y - that.y == 0) {
            if (this.x - that.x == 0) 
                return Double.NEGATIVE_INFINITY;   // if invoking and that point is same.
            return 0;                          //points are on a horizontal axis.
        } else if (this.x - that.x == 0) {
            return Double.POSITIVE_INFINITY;  // points are on a vertical axis manner.
        }
        return (double) (that.y - this.y) / (double) (that.x - this.x); //return dy/dx.
    	
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
    	if(this.y > that.y)   //compare y coordinates first and return 1,0,-1 accordingly.
    		return 1;
    	else if(this.y < that.y)
    		return -1;
    	else                  //for a tie with y coordinates, compare x coordinates.
    		if(this.x > that.x)
    			return 1;
    		else if(this.x < that.x)
    			return -1;
    		else
    			return 0;
    	
    }

    // return string representation of this point (x, y)
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    //this method is used whenever we need to compare two points.
    //this returns true or false. If the points under comparison are equal, it returns true, else false.
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Point)
    		return (this.x == ((Point)obj).x  && this.y == ((Point)obj).y); //return true if both x's and y's are equal for both the points
    	return false; //else return false
    }
}