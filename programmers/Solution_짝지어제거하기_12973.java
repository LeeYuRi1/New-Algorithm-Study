package programmers;

import java.util.Stack;

public class Solution_짝지어제거하기_12973 {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        return stack.isEmpty() ? 1 : 0;
    }
}
