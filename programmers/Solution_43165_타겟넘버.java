package programmers;

public class Solution_43165_타겟넘버 {
    private int answer;

    public int solution(int[] numbers, int target) {
        answer = 0;
        dfs(numbers, target, 0, 0);
        return answer;
    }

    private void dfs(int[] numbers, int target, int cnt, int cur) {
        if (cnt == numbers.length) {
            if (cur == target) answer++;
            return;
        }
        dfs(numbers, target, cnt + 1, cur + numbers[cnt]);
        dfs(numbers, target, cnt + 1, cur - numbers[cnt]);
    }
}
