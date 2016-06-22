import java.util.Arrays;
import java.util.List;

public class ListPrinter {
    public static void printList(List<Integer> l ) {
        if (l == null) {
            System.out.println("null");
        } else {
            System.out.print("[");
            for (int i = 0; i < l.size(); i++) {
                System.out.print(l.get(i));
                if (i < l.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    public static void printArray(int[] a) {
        if (a == null) {
            System.out.println("null");
        } else {
            System.out.println(Arrays.toString(a));
        }
    }

    public static void printStrArray(String[] strs) {
        System.out.print("[");
        for (int i = 0; i < strs.length; i++) {
            System.out.print("\"" + strs[i] +"\"");
            if (i < strs.length - 1) System.out.print(", ");
        }
        System.out.println("]\n");
    }

    public static void print2DList(List<List<Integer>> list ) {
        if (list == null) {
            System.out.println("null");
        } else {
            for (List<Integer> l : list) {
                printList(l);
            }
        }
    }

    public static void print2DArray(int[][] matrix) {
        if (matrix == null) {
            System.out.println("null");
        } else {
            for (int[] arr : matrix) {
                printArray(arr);
            }
        }
    }
}
