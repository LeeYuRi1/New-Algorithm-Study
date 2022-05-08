package programmers;

public class Solution_42862_체육복 {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] arr = new int[n + 2];
        for (int i : lost) arr[i]--;
        for (int i : reserve) arr[i]++;

        int answer = n;
        for (int i = 1; i <= n; i++) {
            if (arr[i] != -1) continue;
            if (arr[i - 1] == 1) {
                arr[i - 1]--;
                arr[i]++;
            } else if (arr[i + 1] == 1) {
                arr[i + 1]--;
                arr[i]++;
            } else {
                answer--;
            }
        }
        return answer;
    }
}
