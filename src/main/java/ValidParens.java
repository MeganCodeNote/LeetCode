import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParens {

    //---------------- Solution1: the most basic idea ----------------/
    // pushing right parens

    public boolean isValid1(String s) {
        // input checking
        if (s == null) return false;
        
        // use a stack to do valid checking
        Stack<Character> st = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {  // left parens
                st.push(ch);
            } else {
                if (st.empty()) return false;
                char top = st.pop();
                if (top == '(' && ch != ')' || 
                    top == '{' && ch != '}' || 
                    top == '[' && ch != ']')
                    return false;
            }
        }
        
        return st.empty();
    }
    
    
	//---------------- Solution2: A nicer format ----------------/
    // instead of pushing left parens, we push right parens
    // to make the code look a little bit more nicer
	
    public boolean isValid2(String s) {
        // input checking
        if (s == null) {
            return false;
        }
        
        // use a stack to do valid checking
        Stack<Character> st = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if      (ch == '(') st.push(')');
            else if (ch == '[') st.push(']');
            else if (ch == '{') st.push('}');
            else if (st.empty() || st.pop() != ch) return false;
        }
        return st.empty();       
    }


    //--------------- Solution 3 ---------------------//
    // use a map
    public boolean isValid3(String s) {
        if (s == null) {
            return false;
        }

        Map<Character, Character> table = new HashMap<Character, Character>();
        table.put('}', '{');
        table.put(')', '(');
        table.put(']', '[');
        Stack<Character> st = new Stack<Character>();
        for (char ch : s.toCharArray()) {
            if (table.containsKey(ch)) {
                if (st.empty() || st.pop() != table.get(ch)) {
                    return false;
                }
            } else {
                st.push(ch);
            }
        }
        return st.empty();
    }
}


/*
 * NOTE: Great test cases
 * 1) len == 0
 * 2) len % 2 = 1
 * 3) all right parens
 * 4) all left parens 
 * 5) a null string
 * 6) a valid embedded string
 * 7) a valid concatenated string
 * 8) a valid complicated case
 */