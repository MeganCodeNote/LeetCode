import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {
    public List<String> anagrams(String[] strs) {
        // input validation
        List<String> res = new ArrayList<String>();
        if (strs == null || strs.length == 0) {
            return res;
        }

        // put anagrams into the same bin (key is sorted str)
        Map<String, List<String>> grouper = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String sorted = new String(chs);
            if (grouper.containsKey(sorted)) {
                grouper.get(sorted).add(str);
            } else {
                List<String> list = new ArrayList<String>();
                list.add(str);
                grouper.put(sorted, list);
            }
        }

        for (List<String> list : grouper.values()) {
            if (list.size() > 1) {
                res.addAll(list);
            }
        }
        return res;
    }
}


