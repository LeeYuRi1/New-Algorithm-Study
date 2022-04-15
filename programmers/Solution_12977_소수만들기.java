package programmers;

public class Solution_12977_소수만들기 {
    private int answer = 0;

    public int solution(int[] nums) {
        combination(0, 0, 0, nums);
        return answer;
    }

    private void combination(int cnt, int start, int num, int[] nums) {
        if (cnt == 3) {
            if (isPrime(num)) answer++;
            return;
        }
        for (int i = start; i < nums.length; i++) {
            combination(cnt + 1, i + 1, num + nums[i], nums);
        }
    }

    private boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
