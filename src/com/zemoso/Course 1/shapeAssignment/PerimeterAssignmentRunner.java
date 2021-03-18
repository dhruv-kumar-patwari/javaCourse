import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        Iterable<Point> pointsInShape = s.getPoints();

        for(Point point : pointsInShape)
            count++;

        return count;
    }

    public double getAverageLength(Shape s) {
        double perimeter = getPerimeter(s);
        int numberOfPoints = getNumPoints(s);

        double averageLength = perimeter / numberOfPoints;

        return averageLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = -1;
        Iterable<Point> pointsInShape = s.getPoints();
        Point prevPt = null;
        for(Point point : pointsInShape){
            if(prevPt == null){
                prevPt = point;
                continue;
            }
            else{
                double length = prevPt.distance(point);
                if (length > largestSide)
                    largestSide = length;
                prevPt = point;
            }
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = -100000;
        Iterable<Point> pointsInShape = s.getPoints();
        Point prevPt = null;
        for(Point point : pointsInShape){
            double x = point.getX();
            if (x > largestX)
                largestX = x;
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeter = -1;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (perimeter > largestPerimeter)
                largestPerimeter = perimeter;

        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        double largestPerimeter = -1;
        File largestPerimeterFile = new File("src/com/zemoso/shapeAssignment/datatest1.txt");
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (perimeter > largestPerimeter){
                largestPerimeter = perimeter;
                largestPerimeterFile = f;
            }

        }
        return largestPerimeterFile.getName();
    }

    public void testPerimeter () {
        Shape s = getNewShape();
        printWithDescription("perimeter = ", getPerimeter(s));
        numberOfPointsPrinter(s);
        printWithDescription("Average length of the shape is: ", getAverageLength(s));
        printWithDescription("Largest side is: ", getLargestSide(s));
        printWithDescription("Largest X value is ", getLargestX(s));
    }

    private void printWithDescription(String s2, double largestX) {
        System.out.println(s2 + largestX);
    }

    private void numberOfPointsPrinter(Shape s) {
        System.out.println("The shape has " + getNumPoints(s) + " Points.");
    }
    private static Shape getNewShape() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        return s;
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        printWithDescription("The larget perimeter is: ", getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("Largest shape is in file: "+ getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
//        perimeterPrinter(triangle);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
//        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
//        pr.testFileWithLargestPerimeter();
    }
}
