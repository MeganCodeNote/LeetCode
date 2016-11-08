import java.util.Arrays;

public class ArrayUtils {
    public static void print2D(int[][] grids) {
        for (int[] row: grids){
            print1DFormat(row);
        }
        System.out.println();
    }

    private static void print1DFormat(int[] row) {
        for (int cell: row) {
            System.out.format("%7d", cell);
        }
        System.out.println();
    }

    public static void print1D(int[] row) {
        System.out.println(Arrays.toString(row));
    }

    public static void main(String[] args) {
        int[][] grids1 = {{0,0,0}, {0,10,0}, {0,0,0}};
        print2D(grids1);
    }
}
