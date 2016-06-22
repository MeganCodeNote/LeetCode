import java.util.Stack;


public class SimplifyPath {
    public String simplifyPath(String path) {
        // input validation
        if (path == null || path.length() == 0) {
            return "";
        }

        // assume that the path is valid
        // first simplify the path
        String[] dirs = path.split("/");
        Stack<String> stack = new Stack<String>();
        for (String dir : dirs) {
            if (dir.equals(".") || dir.equals("")) {
                continue;
            } else if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(dir);
            }
        }

        // build the path string
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, "/" + stack.pop());
        }
        /* also could use iterator
        for (String dir : stack) {
            sb.append("/" + dir);
        }
        */
        return sb.length() == 0 ? "/" : sb.toString();
    }

    ////////////////////  TEST  /////////////////////
    public static void main(String[] args) {

        System.out.println("--------- HOW string.split() works ---------");
        String[] strs1 = "///./../a///b/k///".split("/");
        String[] strs2 = "///./../a///b/k///".split("/+");
        ListPrinter.printStrArray(strs1);
        ListPrinter.printStrArray(strs2);

        System.out.println("--------- Test for this problem ---------");
        SimplifyPath sp = new SimplifyPath();
        System.out.println(sp.simplifyPath("/.."));
        System.out.println(sp.simplifyPath("/..///a/./../b/c//"));
    }
}