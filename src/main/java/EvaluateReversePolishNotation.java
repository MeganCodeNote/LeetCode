import java.util.Stack;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        // validate the arguments
        if (tokens == null) {
            return 0;
        }
        
        // use a stack to facilitate calculation
        String operators = "+-*/";
        Stack<Integer> stack = new Stack<Integer>();
        for (String token: tokens) {
            // numbers: convert string into integer and push to stack
            if (!operators.contains(token)) {
                stack.push(Integer.valueOf(token));
             
            // operators: pop operands, calculate and store result back to stack
            } else {
                int b = stack.pop();
                int a = stack.pop();
                if (token.equals("+")) {
                    stack.push(a + b);
                } else if (token.equals("-")) {
                    stack.push(a - b);
                } else if (token.equals("*")) {
                    stack.push(a * b);
                } else if (token.equals("/")) {
                    stack.push(a / b);
                }
            }
        }
        return stack.pop();
    }
}

// NOTE 1: str.contains(antherStr);
