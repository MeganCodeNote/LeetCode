
public class Point {
	int x;
	int y;
	Point() { x = 0; y = 0; }
	Point(int a, int b) { x = a; y = b; }
	
	// Build an Array of points using this method
	public static Point[] buildPoints(int[] x, int[] y) {
		if (x == null || y == null || x.length != y.length) {
			return null;
		}
		Point[] points = new Point[x.length];
		for (int i = 0; i < x.length; i++) {
			points[i] = new Point(x[i], y[i]);
		}
		return points;
	}
}
