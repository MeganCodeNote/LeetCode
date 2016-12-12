import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {
    //---------------- Solution 1 -------------------//
    // use int[] as solution builder
    // this solution uses swap()
    // Attention: the permutations are not naturally ordered!!
    public List<List<Integer>> permute(int[] num) {
        // input checking
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }

        // use a helper to populate the res
        // assume there are no duplicates in num
        helper(res, num, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, int[] num, int start) {
        // base case
        if (start == num.length) {
            List<Integer> perm = new ArrayList<Integer>();
            for (int i : num) {
                perm.add(i);
            }
            res.add(perm);
            return;
        }
        // general case
        for (int i = start; i < num.length; i++) {
            swap(num, start, i);
            helper(res, num, start + 1);
            swap(num, start, i);
        }
    }

    private void swap(int[] num, int a, int b) {
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }

    //--------------------- Solution 2 --------------------//
    // a very classic permutation solution
    public List<List<Integer>> permute2(int[] num) {
        // input checking
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }

        // use a helper to populate the res
        helper(res, new ArrayList<Integer>(), num);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> perm, int[] num) {
        // base case
        if (perm.size() == num.length) {
            res.add(new ArrayList<Integer>(perm));  // remember to new a List
            return;                                 // remember to return
        }
        // general case
        for (int i : num) {
            if (!perm.contains(i)) {
                perm.add(i);
                helper(res, perm, num);
                perm.remove(perm.size() - 1);
            }
        }
    }

    //----------------- Solution 3 -------------------//
    // iterative
    public List<List<Integer>> permute3(int[] num) {
        // input checking
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }

        // use a incremental way of constructing the solution
        Arrays.sort(num);
        res.add(new ArrayList<Integer>());
        for (int i: num) {
            List<List<Integer>> resTemp = new ArrayList<List<Integer>>();
            for (List<Integer> perm: res) {
                for (int j = 0; j <= perm.size(); j++) {
                    perm.add(j, i);
                    resTemp.add(new ArrayList<Integer>(perm));
                    perm.remove(j);
                }
            }
            res = resTemp;
        }
        return res;
    }
    
    //------------------   TEST  --------------------//
    private static void test(Permutation solution, int[] a) {
        List<List<Integer>> permutations = solution.permute3(a);
        ListPrinter.print2DList(permutations);
        System.out.println();
    }
    public static void main(String[] args) {
        Permutation solution = new Permutation();
        int[] a1 = {1, 2, 3};
        int[] a2 = {4, 3, 2, 1};
        int[] a3 = {5, 6, 7, 8};
        test(solution, a1);
        test(solution, a2);
        test(solution, a3);
    }
}