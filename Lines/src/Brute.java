/*************************************************************************
 * Name: Brute.java
 * Date: 29-May-2017
 *
 * Compilation:  javac Brute.java input6.txt
 * Execution: java Brute input6.txt
 *
 * Description: This computes the collinear points(line) on a plane by using brute-force technique.
 * This program takes four points from all the points one after the other (takes all the combinations) and checks 
 * whether they(picked points) lie on the same line(having same slopes).
 * 
 *
 *@author Vivek Roshan
 *************************************************************************/
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
(14000, 10000) -> (18000, 10000) -> (19000, 10000) -> (21000, 10000)
(14000, 10000) -> (18000, 10000) -> (19000, 10000) -> (32000, 10000)
(14000, 10000) -> (18000, 10000) -> (21000, 10000) -> (32000, 10000)
(14000, 10000) -> (19000, 10000) -> (21000, 10000) -> (32000, 10000)
(18000, 10000) -> (19000, 10000) -> (21000, 10000) -> (32000, 10000)
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Brute {
	
	//this function takes the filename argument, given from the command line
	//this function takes all the points from the input file and keeps all the points in an array of point objects(each contain x and y coordinates).
	public Point[] readingFile(String filename)
	{
	/*	BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)));
		int totalpoints = 0;
		Point[] points;
		int j=0;
		try {
			totalpoints = Integer.parseInt(br.readLine());
			//System.out.println(totalpoints);
			points = new Point[totalpoints];
			String line = "";
			while(j<points.length)
			{
				line= br.readLine();
				Point temp;
				String[] coord = line.split(" ");
				temp = new Point(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
				points[j] = temp;
				j++;
			}
			return points;
			
		} catch (Exception e) {
			return null;
		}*/
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
		
	    Brute brute = new Brute();
        //read an input file from args;
		String file = args[0]; // first argument from command line
		Point[] point = brute.readingFile(file); //send the argument read from the command line to the readingFile method
	    //the readingFile returns an array of populated points from the input file.
	    Arrays.sort(point); //sort the points according to the compareTo method in Point class.
	    List<ArrayList<Point>> arrayLists = new ArrayList<>(); //create a list of lists, which contain the list of lines. And each line contain multiple points(collinear).
	    //aim of the brute function is to pick four points from the given set of points and check whether they are collinear or not.
	    //if they are collinear, print them out to the standard output.
	    for(int i=0;i<point.length;i++)  
		{
			Point point1=point[i]; //picking up 1st point,p
			for(int j=i+1;j<point.length;j++)
			{
				Point point2=point[j]; //picking up 2nd point,q
 				for(int k=j+1;k<point.length;k++)
				{
					Point point3=point[k]; //picking up 3nd point,r
					for(int l=k+1;l<point.length;l++)
					{
						Point point4=point[l]; //picking up 4th point,s
						double s1=point1.slopeTo(point2);   //slope of p and q
						double s2=point1.slopeTo(point3);	//slope of p and r
						double s3=point1.slopeTo(point4);	//slope of p and s
						if(s1==s2 && s2==s3)                //check whether slope(p,q) = slope(p,r) and slope(p,r) = slope(p,s)
						{ //if true, their slopes are equal and they lie on same line.
							List<Point> points = new ArrayList<>();
							points.add(point1);   
							points.add(point2);
							points.add(point3);
							points.add(point4);
							//add all the points(line) to a list
							System.out.println(point1.toString() + " -> " + point2.toString() + " -> " + point3.toString() + " -> " + point4.toString() );
							//add the list containing the line to a list of lists.
							arrayLists.add((ArrayList<Point>) points);
						}
					}
				}
			}
		}
	    //sets the canvas(window) size according to the given dimensions.
	    StdDraw.setCanvasSize(1024, 512);
	    StdDraw.setPenRadius(0.01); //set the pen radius accodrding to the requirement.
	    StdDraw.setXscale(0, 32768); //x-axis
        StdDraw.setPenColor(StdDraw.BLACK); //pen color
        StdDraw.setYscale(0, 32768); //y-axis
        for (Point p : point) {
            p.draw();   //draw the point on to the canvas, with the given x and y coordinates of p.
        }
        StdDraw.show(); //show the canvas.
        
	    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE); //changing the pen color.
	    StdDraw.setPenRadius(0.002); //updating the pen color
	    int i=10;
	    for (ArrayList<Point> points : arrayLists) {
	    	// %200 is used in order not to exceed the color dimensions(ie.. 0 to 255)
	    	StdDraw.setPenColor((90+i)%200,(30+i)%200,(i)%200); //in order to get mutliple colors.
		    i=i+100; //increment i for different color combinations.
	    	points.get(0).drawTo(points.get(points.size()-1)); //draw the line from the first point to last point in a line.
		}
	    
	}

}
