import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnLine {
    //-----------------  Solution 1 ----------------------------//
    // fix 2 points and count
    public int maxPoints(Point[] points) {
        // validate input
        if (points == null || points.length == 0) {
            return 0;
        }

        // find all pairs of DISTINCT points
        // then count how many other points are on it
        int max = 1;
        int N = points.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // only look for distinct points (i, j)
                if (samePoints(points, i, j)) {
                    continue;
                }

                int numOfPoints = 2;
                for (int k = 0; k < N; k++) {
                    // find points other than i or j
                    if (k != i && k != j && (points[k].y - points[i].y) * (points[k].x - points[j].x) ==
                                            (points[k].x - points[i].x) * (points[k].y - points[j].y)) {
                        numOfPoints++;
                    }
                }
                max = Math.max(max, numOfPoints);
            }
        }
        return allSamePoints(points, N) ? N : max;
    }

    public boolean samePoints(Point[] points, int i, int j) {
        return (points[i].x == points[j].x && points[i].y == points[j].y);
    }

    public boolean allSamePoints(Point[] points, int N) {
        for (int i = 0; i < N - 1; i++) {
            if (!samePoints(points, i, i + 1)) {
                return false;
            }
        }
        return true;
    }


    //-----------------  Solution 2 ----------------------------//
    // categorize all lines into those of:
    // category 1: lines that across point 1
    // category 2: lines that across point 2
    // ...
    // category N: lines that across point N
    //
    public int maxPoints2(Point[] points) {
        // validate inputs
        if (points == null || points.length == 0) {
            return 0;
        }

        // fix one point, then categorize all the lines across this point
        // using the slope of the line
        int max = 1;
        int N = points.length;
        for (int i = 0; i < N; i++) {
            //---- step 1: calculated number of points ------//
            // key:   slope of line over current fixed point
            // value: num of points on the slope
            Map<Double, Integer> table = new HashMap<Double, Integer>();
            int dupes = 1;
            for (int j = i + 1; j < N; j++) {
                // for points overlapping the current point
                if ((points[i].x == points[j].x) &&  (points[i].y == points[j].y)) {
                    dupes++;
                    continue;
                }
                // for points not overlapping the current point
                Double slope = (points[i].x == points[j].x) ? Double.MAX_VALUE :
                        (((double)(points[i].y - points[j].y)) / (points[i].x - points[j].x));
                if (slope == -0.0) {
                    slope = 0.0;
                }
                int numOfPoints =  table.containsKey(slope) ? (table.get(slope) + 1) : 1;
                table.put(slope, numOfPoints);
            }

            //----- step2: update the max ---------//
            max = Math.max(max, dupes); // need this here, in case the table is empty, all points are the same
            for (Double slope: table.keySet()) {
                max = Math.max(max, table.get(slope) + dupes);
            }
        }
        return max;
    }

    //////////////////////  TEST /////////////////////////
    public static void main(String[] args) {
        // test case 1
        // input: [(84,250),(0,0),(1,0),(0,-70),(0,-70),(1,-1),(21,10),(42,90),(-42,-230)]
        // ouput: 6
        int[] x1 = {84,  0, 1,   0,   0,  1, 21, 42, -42};
        int[] y1 = {250, 0, 0, -70, -70, -1, 10, 90, -230};


        // test case 2
        // input: [(0,9),(138,429),(115,359),(115,359),(-30,-102),(230,709),(-150,-686),
        //         (-135,-613),(-60,-248),(-161,-481),(207,639),(23,79),(-230,-691), (-115,-341),
        //         (92,289),(60,336),(-105,-467),(135,701),(-90,-394),(-184,-551),(150,774)]
        // ouput: 12
        int[] x2 = {0, 138, 115, 115,  -30, 230, -150, -135,  -60, -161, 207, 23, -230, -115,  92,  60, -105, 135,  -90, -184, -150};
        int[] y2 = {9, 429, 359, 359, -102, 709, -686, -613, -248, -481, 639, 79, -691, -341, 289, 336, -467, 701, -394, -551,  774};

        // test case 3: all same points
        // output: 4
        int[] x3 = {0, 0, 0, 0};
        int[] y3 = {0, 0, 0, 0};

//        Point[] points = Point.buildPoints(x1, y1);
//        Point[] points = Point.buildPoints(x2, y2);
        Point[] points = Point.buildPoints(x3, y3);
        System.out.println((new MaxPointsOnLine()).maxPoints2(points));
    }
}

// ERROR 1: slope should be double:
//            (((double)(points[i].y - points[j].y)) / (points[i].x - points[j].x));
//
// ERROR 2: related to ERROR 1  0.0 != -0.0?
//          Input:      [(2,3),(3,3),(-5,3)]
//          Expected:   3
//
// ERROR 3: used N but forgot to define it
//            int N = points.length;
