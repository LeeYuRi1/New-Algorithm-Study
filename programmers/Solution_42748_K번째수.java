package programmers;

import java.util.Arrays;

public class Solution_42748_K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1] - 1;
            int k = commands[i][2];

            int[] arr = new int[end - start + 1];
            for (int j = start; j <= end; j++) {
                arr[j - start] = array[j];
            }
            Arrays.sort(arr);
            answer[i] = arr[k - 1];
        }
        return answer;
    }

    // copyOfRange 사용
    public int[] solution2(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1] - 1;
            int k = commands[i][2];

            int[] arr = Arrays.copyOfRange(array, start, end + 1);
            Arrays.sort(arr);
            answer[i] = arr[k - 1];
        }
        return answer;
    }

}
