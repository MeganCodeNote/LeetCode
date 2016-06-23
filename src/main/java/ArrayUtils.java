public class ArrayUtils {
    public static void print2D(int[][] grids) {
        for (int[] row: grids){
            print1D(row);
        }
        System.out.println();
    }

    public static void print1D(int[] row) {
        for (int cell: row) {
            System.out.format("%5d", cell);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] grids1 = {{0,0,0}, {0,1,0}, {0,0,0}};
        print2D(grids1);
    }
}
