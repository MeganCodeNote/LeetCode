import java.util.ArrayList;
import java.util.List;

public class GenerateParens {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        if (n <= 0) {
            return res;
        }

        helper(n, n, 0, new char[n * 2], res);
        return res;
    }

    private void helper(int numL, int numR, int i, char[] str, List<String> res) {
        if (numL + numR == 0) {
            res.add(String.valueOf(str));
            return;
        }

        if (numL > 0) {
            str[i] = '(';
            helper(numL - 1, numR, i + 1, str, res);
        }

        if (numL < numR) {
            str[i] = ')';
            helper(numL, numR - 1, i + 1, str, res);
        }
    }

    public static void main(String[] args) {
        GenerateParens pg = new GenerateParens();
        System.out.println(pg.generateParenthesis(4));
        System.out.println(pg.generateParenthesis(3));
        System.out.println(pg.generateParenthesis(0));
        System.out.println(pg.generateParenthesis(-1));
    }
}
     
