/*************************************************************************
 * Name: Fast.java
 * Date: 29-May-2017
 *
 * Compilation:  javac Fast.java
 * Execution: java Fast input6.txt
 *
 * Description: This computes the collinear points(line) on a plane by using sorting technique.
 * This program takes a running time of NlogN(for sorting) and iterating through all points(reference) needs an N.
 * Total running time to be N^2(logN). where N is the number of points.
 * 
*@author Vivek Roshan
**************************************************************************/

/*
 * Inputs:
 * input8.txt:
8
10000 0
0 10000
3000 7000
7000 3000
20000 21000
3000 4000
14000 15000
6000 7000
Output:
(10000, 0) -> (7000, 3000) -> (3000, 7000) -> (0, 10000)
(3000, 4000) -> (6000, 7000) -> (14000, 15000) -> (20000, 21000)

 * input6.txt:
6
19000 10000
18000 10000
32000 10000
21000 10000
1234 5678
14000 10000
Output:
(14000, 10000) -> (18000, 10000) -> (19000, 10000) -> (21000, 10000) -> (32000, 10000)
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Fast {

	//this function takes the filename argument, given from the command line
	//this function takes all the points from the input file and keeps all the points in an array of point objects(each contain x and y coordinates).
	public Point[] readingFile(String filename)
	{
		In in = new In(filename); //send the file name to the In class constructor to read all the points from the input file.
		int N = in.readInt(); //read the first line, which contains the number of points in the input file.
		Point[] point = new Point[N]; //create an array of Point objects.
		for (int i = 0; i < N; i++) {
			int x= in.readInt();  //read x coordinate and put in x.
			int y = in.readInt(); //read y coordinate and put in y.
			point[i] = new Point(x, y);  //with the obtained x and y, create a Point instance and add the object to the array.
			}
		return point; //return the point array.
	}

	public static void main(String[] args) {
		try{
		Fast fast = new Fast();
		String file = args[0];    //read an input file from args;
		// first argument from command line
	    Point[] point = fast.readingFile(file);  //send the argument read from the command line to the readingFile method
	    //the readingFile returns an array of populated points from the input file.	    
	    Arrays.sort(point); //sort the points according to the compareTo method in Point class.
	    //StdDraw.show(0);  
	    
	  //sets the canvas(window) size according to the given dimensions.
	    StdDraw.setCanvasSize(1024, 512); 
	    StdDraw.setPenRadius(0.01);   //set the pen radius accodrding to the requirement.
	    StdDraw.setXscale(0, 32768);  //x-axis 
        StdDraw.setPenColor(StdDraw.BLACK);  //pen color
        StdDraw.setYscale(0, 32768);  //y-axis
        for (Point p : point) { 
            p.draw();  //draw the point on to the canvas, with the given x and y coordinates of p.
        }
        StdDraw.show();  //show the canvas.
	    
	    
	    List<Point> pointsList = new ArrayList<Point>();   //create a ArrayList<Point> to copy all the points from array to list.
	    for (int i = 0; i < point.length; i++) {
	    	pointsList.add(point[i]);  //add all the points from array to list.
	    }
	    int j=0;   //initialize j=0 for iterating through the list one after the other(making each and every point as the reference point) 
	    List<ArrayList<Point>> arrayLists = new ArrayList<>();
	    while(j<pointsList.size())  //check whether j is less than the list size or not.
	    {
	    	List<Point> remaining = new ArrayList<Point>(); //creating a list to store the points other than the reference point
	    	remaining.clear();  //clearing it from garbage values
	    	remaining = fast.ArrayCopy(pointsList); //copy all the points from pointsList to remaining list
	    	Point p0 = remaining.get(j); //getting the reference point(as the index of j) one after the other.
	    	remaining.remove(j);   /*remove the point from the remaining list. The reference point must not be in the list 
	    	and all the points other than reference point must be compared and sorted to find the collinear points */
	    	Collections.sort(remaining,p0.SLOPE_ORDER); //sort the remaining list with respect to the slopes it makes with reference point
	    	List<ArrayList<Point>> equalSlopes = fast.collinear(p0, remaining);  //send reference point and remaining list to collinear method.
	    	//collinear method returns all the lines(collinear points) that pass through the reference point and returns a list of lists which are collected in equalSlopes. 

	    	for (List<Point> equalSlopesLine : equalSlopes) {  //loop through all the lines one-by-one obtained from the reference point.
	    		Boolean flag = fast.comparison(equalSlopesLine, arrayLists); //compare the obtained lines,whether they are just permutations or subsegments.
	    		if(flag) //if flag returned is true, thats a new line and should be treated as a legiteble line.
		    	{
		    		arrayLists.add((ArrayList<Point>)equalSlopesLine); //add the line in list of lists for output.
		    	}
			}
	    	j++;   //increment j so as to take the next reference point.
	    }
	    int i=0;            
	    //loop to print on the stabdard output page.
	    for (ArrayList<Point> arrayList : arrayLists) {
			for (Point point2 : arrayList) {
				if(i != 0)
				{
					System.out.print(" -> ");
				}
				System.out.print(point2.toString());  //print the point
				i++;
			}
			System.out.println();
			i=0; 
		}
	    
	    
	    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE); //changing the pen color.
	    StdDraw.setPenRadius(0.002); //updating the pen color
	    for (ArrayList<Point> points : arrayLists) { 
	    	points.get(0).drawTo(points.get(points.size()-1));  //draw the line from the first point to last point in a line.
		}
	    
		}
		catch(Exception e)
		{
			System.err.println("There is some error in the input");
		}
	}
	//this method is useful for copying the elements from one list to other list
	public List<Point> ArrayCopy(List<Point> point)
	{
		List<Point> list = new ArrayList<Point>();
		for (Point point2 : point) {
			list.add(point2); //add all the points one after the other.
		}
		return list; //returned the copied list
	}
	//this method returns a list of lists
	//this method is useful to find all the lines(4 or more collinear points) that pass through the reference point.
	public List<ArrayList<Point>> collinear(Point p0,List<Point> points)
	{
		int j=0;
		List<ArrayList<Point>> arrayLists = new ArrayList<>(); //create a list of lists
		Double initialSlope = p0.slopeTo(points.get(0)); //get the initial slope(slopeTo(referncePoint,first element in list)
		Double slope = Double.NEGATIVE_INFINITY;  //initialize a variable slope to be negative infinity.
		List<Double> doubles = new ArrayList<Double>();
		for (int i = 0; i < points.size(); i++) {
			if(p0.slopeTo(points.get(i)) == initialSlope) //if the slopes of the next elements are equal to initial slopes
			{
				j++; //increment j
				slope = p0.slopeTo(points.get(i)); //update the slope value.  
			}
			else{
				initialSlope = p0.slopeTo(points.get(i));  //update initial slope
				if(j > 2 && slope != Double.NEGATIVE_INFINITY)  //check the conditions
				{	
					//we are finding out all the lines that pass through the reference point and adding the slopes of lines to a list.
					doubles.add(slope);  //add all the slopes where j value is atleast 3(collinear ->line) to a list
					j=1; //reinitialize j to 1 as the cycle for the new slopes has started again
				}
				else{
					j=1;  //reinitialize j to 1 as the cycle for the new slopes has started again
				}
			}
		}
		if(j > 2 && slope != Double.NEGATIVE_INFINITY)
		{	
			doubles.add(slope);  //checking the last set
		}

		for (Double double1 : doubles) {
			List<Point> points2 = new ArrayList<Point>();
			for (Point point : points) {
				if(p0.slopeTo(point) == double1)
				{
					points2.add(point); //adding the points with the selected slope
				}
			}
			points2.add(p0); //adding the reference point itself to the list
			Collections.sort(points2); //sort them according to the compareTo method of point.
			arrayLists.add((ArrayList<Point>)points2);  //add the list(collinear points/line to the list)
		
		}
		return arrayLists;	 //return the list of lists.
	}
	//this method return true or false; true if the line is legitimate(not a permutation or subsegment of already existing line) 
	public Boolean comparison(List<Point> equalSlopes, List<ArrayList<Point>> arrayLists)
	{
		int j=0;
			for (List<Point> point2 : arrayLists) { //compare the already existing lines to the obtained lines
				for (Point point : equalSlopes) { //take the lines in obtained lines one after other
					for (Point point3 : point2) { //take the points from the line
						if(point.equals(point3)){ //compare the points
							j++;   //increment j if they are equal
							break;
							}
				}
			}
			if(j != equalSlopes.size() )
				j=0;
			else
				break;
		}
		if(j == equalSlopes.size()) //if this is true, line already exists i arrayLists. Hence return fase.
			return false;
		return true; //else return true; it means that the line doesn't previously existed.
	}

}
