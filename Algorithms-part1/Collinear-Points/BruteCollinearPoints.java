import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] ls;
    private ArrayList<LineSegment> list;
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null) {
            throw new NullPointerException();
        }

        Arrays.sort(points);

        list = new ArrayList<LineSegment>();
        int l = points.length;
        for ( int i = 0; i < l - 3; i++ ) {
            for ( int j = i + 1; j < l - 2; j++) {
                for ( int k = j + 1; k < l - 1; k++) {
                    for ( int m = k + 1; m < l; m++) {
                        if ( ( points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) ) &&
                               ( points[i].slopeTo(points[j]) == points[i].slopeTo(points[m]) ) )  {
                            list.add(new LineSegment(points[i], points[m]) );
                        }
                    }
                }
            }
        }
    }
    public int numberOfSegments() {
        // the number of line segments
        return list.size();
    }
    public LineSegment[] segments() {
        // the line segments
        ls = new LineSegment[numberOfSegments()];
        list.toArray(ls);
        return ls;
    }

    private void checkForDuplicates(Point[] points) {
        int l = points.length;
        for (int i = 0; i < l - 1 ; i++) {
            for (int j = i + 1; j < l; j++) {
                if (points[i] == null || points[j] == null) {
                    throw new NullPointerException();
                }
                if (points[i].compareTo(points[j]) == 0 ) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }


}
