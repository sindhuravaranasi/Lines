Analysis:

 * For both Brute.java and Fast.java we need to take the input from the command line argument.
 * The code will take the first argument(arg[0]) as the file name(input6.txt/input8.txt etc).
 * Output gets displayed on the console(The lines computed out of the given points).
Note: The input file given should be present in the src folder(same project folder) of these java files as we just give the input file name and not the actual path.

Brute.java:
 * This computes the collinear points(line) on a plane by using brute-force technique.
 * This program takes four points from all the points one after the other (takes all the combinations) and checks whether they(picked points) lie on the same line(having same slopes).
 * As this method involves in taking all the combinations(4-point set) out of all points, it require 4 for loops iterating through the points.
 * These 4 for-loops through N points makes the running-time complexity of BigO(N^4(N power 4))

Fast.java:
 * This computes the collinear points(line) on a plane by using sorting technique.
 * This program takes a running time of NlogN(for sorting) and iterating through all points(reference) needs an N.
 * For sorting: the best, worst, average time complexities for merge sort is BigO(NlogN) and the method which I used in the program to sort the points is merge sort with NlogN time complexity. 
 * Total running time to be BigO(N^2(logN)). where N is the number of points.

Compiling/Execution:
 * The project is done and executed in eclipse(an open-source and friendly platform).
 * For both Fast.java and Brute.java, right click on the program -> Run As -> Run Configurations.
 * Make sure that Name,Project,Main Class are correct. Go to arguments tab and give the input file name in the program arguments section.
 * The input file is intended to be present only in the src/java files folder as it only considers the relative path and not the absolute path
 * ex: input8.txt is the inout file. Click on Apply and Run.
 * Standard Output will be displayed on the console and Standard drawing canvas gets appeared (with points and lines plotted on it).

Time Complexity: 

 * For Brute.java: Big-oh(N (power) 4 )
 * For the best, average and worst cases, it is (N power 4).

 * For Fast.java: Big-oh( N^2 log N); NlogN for sorting and N for iterating through all the points.
 * For the best, worst, average cases fpr merge sort is NlogN and hence for best, worst, average of Fast.java is (N^2 log N)

Resources:
 * In.java and StdDraw.java are the two external java files used for the programs.
 * In.java: for reading data from the input files, given as command line arguments.
 * Imported from assignment-0 resources. 
 * StdDraw.java: for standard drawing on the canvas.
 * Imported from http://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/StdDraw.java.html.
   