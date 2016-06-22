import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    //--------------- Solution 1 --------------------//
    // a very sexy sleek solution!!
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        // input checking
        if (newInterval == null) {
            return intervals;
        }

        List<Interval> results = new ArrayList<Interval>();
        for (Interval current: intervals) {
            if (current.end < newInterval.start) {
                results.add(current);
            } else if (current.start > newInterval.end) {
                results.add(newInterval);
                newInterval = current;
            } else {
                newInterval.start = Math.min(current.start, newInterval.start);
                newInterval.end = Math.max(current.end, newInterval.end);
            }
        }
        results.add(newInterval);
        return results;
    }

    //--------------- Solution 2 --------------------//
    // a solution to the normal brain
    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        // input checking
        if (newInterval == null) {
            return intervals;
        }
        List<Interval> res = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
            res.add(newInterval);  // new Interval is not null
            return res;
        }

        // first find the postion where interval might be merge into
        int i;
        for (i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).end < newInterval.start) {
                res.add(intervals.get(i));
            } else {
                break;
            }
        }

        // merget the new interval in
        Interval last = newInterval;
        for (int j = i; j < intervals.size(); j++) {
            Interval cur = intervals.get(j);
            if (cur.start > last.end) {
                res.add(last);
                last = cur;
            } else {
                last.start = Math.min(cur.start, last.start);
                last.end = Math.max(cur.end, last.end);
            }
        }
        res.add(last);
        return res;
    }

    /////////////////////// TEST ///////////////////////
    public static void main(String[] args) {
        InsertInterval solution = new InsertInterval();

        int[] a = {0, 3, 6, 10, 15, 18};
        List<Interval> list = Interval.buildIntervals(a);
        Interval newInterval = new Interval(3, 6);

        Interval.print(solution.insert(list, newInterval));
    }
}
