package amazoninternal.codingassessment;

import java.util.Stack;

public class RunLengthEncoder {
    public static void main(String args[]) {
        String input = "aaabbccca";
        System.out.println("Encoded String is :" + getEncodedString(input));
    }

    private static String getEncodedString(String input) {
        /*Stack<Character> stack = new Stack<>();
        for(char ch : input.toCharArray()){
            stack.push(ch);
        }
        StringBuilder str = new StringBuilder();
        while(!stack.isEmpty()){
            char currChar = stack.pop();
            int countOfChar = 1;
            while(!stack.isEmpty() && stack.peek() == currChar){
                countOfChar++;
                stack.pop();
            }
            StringBuilder countStr = new StringBuilder();
            for(char ch : String.valueOf(countOfChar).toCharArray()){
                countStr.append(ch);
            }
            str.append(countStr.reverse());
            str.append(currChar);
        }
        return str.reverse().toString();
        */
        int len = input.length();
        int idx = 0;
        StringBuilder str = new StringBuilder();
        while (idx < len) {
            char currChar = input.charAt(idx);
            int countOfChar = 1;
            int nextIdx = idx + 1;

            while (nextIdx < len && input.charAt(nextIdx) == currChar) {
                nextIdx++;
                countOfChar++;
            }
            str.append(currChar).append(countOfChar);
            idx = nextIdx;
        }
        return str.toString();
    }
}
