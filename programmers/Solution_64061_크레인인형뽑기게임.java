package programmers;

import java.util.Stack;

public class Solution_64061_크레인인형뽑기게임 {
    public int solution(int[][] board, int[] moves) {
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        for (int move : moves) {
            int select = getItem(board, move - 1);
            if (select == 0) continue;
            if (!stack.isEmpty() && stack.peek() == select) {
                stack.pop();
                answer += 2;
            } else {
                stack.push(select);
            }
        }
        return answer;
    }

    private static int getItem(int[][] board, int j) {
        int remove = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][j] != 0) {
                remove = board[i][j];
                board[i][j] = 0;
                break;
            }
        }
        return remove;
    }
}
