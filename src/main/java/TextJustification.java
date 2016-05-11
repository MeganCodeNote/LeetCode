import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int L) {
        // input checking
        List<String> lines = new ArrayList<String>();
        if (L == 0 || words == null || words.length == 0) {
            lines.add("");
            return lines;
        }

        int curLen = 0; // len of all words determined to be in the same line
        int lo = 0, hi = 0;
        while (hi < words.length) {
            int nextLen = curLen + words[hi].length() + hi - lo;
            if (nextLen > L) {  // hi - lo is the least number of spaces needed
                addLine(lines, words, lo, hi - 1, curLen, L);
                lo = hi;
                curLen = 0;
            } else {
                curLen += words[hi].length();
                hi++;
            }
        }
        addLine(lines, words, lo, hi - 1, curLen, L);
        return lines;
    }

    private void addLine(List<String> lines, String[] words, int lo, int hi, int curlen, int L) {
        // only one word (regular line || last line)
        if (lo == hi) {
            lines.add(String.format("%-" + L + "s", words[lo]));
            return;
        }

        // >= 2 words
        int num = (L - curlen) / (hi - lo);
        int extra = (L - curlen) % (hi - lo);
        StringBuilder sb = new StringBuilder();
        for (int i = lo; i < hi; i++) {
            sb.append(words[i] + String.format("%-" + num + "s", ""));
            if (i - lo < extra) {
                sb.append(' ');
            }
        }
        sb.append(words[hi]);   // append last word

        // regular line or last line
        String line = sb.toString();   // case 1: regular line
        if (hi == words.length - 1) {  // case 2: last line
            line = String.format("%-" + L + "s", line.replaceAll("[ ]+", " "));
        }
        lines.add(line);
    }

    ////////////////// TEST ///////////////////
    private static void test(TextJustification solution, String[] words, int L) {
        List<String> lines = solution.fullJustify(words, L);
        System.out.println("12345678901234567890");  // ruler
        for (String line: lines) {
            System.out.println(line + "#");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TextJustification solution = new TextJustification();
        String[] words1 = {"What","must","be","shall","be."};
        String[] words2 = {"This", "is", "an", "example", "of", "text", "justification."};
        String[] words3 = {""};

        test(solution, words1, 6);
        test(solution, words1, 10);
        test(solution, words1, 12);
        test(solution, words1, 18);

        test(solution, words2, 15);
        test(solution, words3, 0);
    }
}

//-------- WRONG ANSWERS --------------//
// Input:       [""], 0
// Output:      []
// Expected:    [""]

// Input:       ["What","must","be","shall","be."], 12
// Output:      ["What must be","shall    be."]
// Expected:    ["What must be","shall be.   "]
