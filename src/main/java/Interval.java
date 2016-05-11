import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interval {
    //------ standard fields and constructor --------//
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }

    //------ extended for testing --------//
    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }

    public static List<Interval> buildIntervals(int[] a) {
        // input checking, make odd number of ints
        List<Interval> l = new ArrayList<Interval>();
        if (a.length % 2 != 0) {
            a = Arrays.copyOf(a, a.length + 1);
            a[a.length - 1] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < a.length; i += 2) {
            Interval inter = new Interval(a[i], a[i + 1]);
            l.add(inter);
        }
        return l;
    }

    public static void print(List<Interval> l) {
        for (Interval interval : l) {
            System.out.print(interval + " ");
        }
        System.out.println();
    }
}