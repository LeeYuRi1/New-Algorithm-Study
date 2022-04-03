package programmers;

public class Solution_86051_없는숫자더하기 {
    public int solution(int[] numbers) {
        boolean[] checkNum = new boolean[10];
        for (int i : numbers) {
            checkNum[i] = true;
        }
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            if (!checkNum[i]) answer += i;
        }
        return answer;
    }

    // 다른 풀이!
    public int solution2(int[] numbers) {
        int answer = 45;
        for (int i : numbers) {
            answer -= i;
        }
        return answer;
    }
}
