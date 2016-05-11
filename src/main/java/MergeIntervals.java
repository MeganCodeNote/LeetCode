import java.util.*;

public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        // input checking
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        // don't forget to sort!!
        List<Interval> res = new ArrayList<Interval>();
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        // merge one by one
        Interval cur = intervals.get(0);
        for (Interval i : intervals) {
            if (i.start > cur.end) {
                res.add(cur);
                cur = i;
            } else {
                cur.end = Math.max(cur.end, i.end);
            }
        }
        res.add(cur); // don't forget to add the last one in!!
        return res;
    }
    
    
    /////////////////////// TEST ///////////////////////
    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();
        int[] a = {0, 3, 8, 10, 15, 20, 3, 6, 8, 12, 19, 20};
        List<Interval> list = Interval.buildIntervals(a);
        Interval.print(list);
        Interval.print(solution.merge(list));
    }
}


