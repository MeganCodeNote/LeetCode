public class KMP {
    public String strStr(String h, String n) {
        // input checking
        if (h == null || n == null || n.length() == 0) {
            return h;
        }
        if (h.length() < n.length()) {
            return null;
        }

        // h and n are not null and len(h) >= len(n)
        int[] table = buildTable(n);
        int i, j = 0;
        for (i = 0; i < h.length();) {
            if (h.charAt(i) != n.charAt(j)) {
                if (j == 0) {
                    i++;
                } else {
                    j = table[j - 1];
                }
            } else {
                i++;
                j++;
                if (j == n.length()) {
                    return h.substring(i - j);
                }
            }
        }
        return null;
    }

    private int[] buildTable(String s) {
        // assume s is not null, not empty
        int n = s.length();
        int[] table = new int[n];
        int i = 0, j;
        for (j = 1; j < n;) {
            if (s.charAt(i) == s.charAt(j)) {
                table[j] = table[j - 1] + 1;
                i++;
                j++;
            } else {
                if (i == 0) {
                    j++;
                } else {
                    i = 0;
                }
            }
        }
        return table;
    }


    ///////////////////  TEST  ///////////////////
    public static void main(String[] args) {
        KMP solution = new KMP();
        int[] res = solution.buildTable("ABCDABD");
        for (int i: res) {
            System.out.println(i);
        }
        String find = solution.strStr("ABC ABCDAB ABCDABCDABDE", "ABCDABD");
        System.out.println(find);
    }
}
