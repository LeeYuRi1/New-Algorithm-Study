package programmers;

import java.util.ArrayList;
import java.util.List;

public class Solution_42586_기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {
        int len = progresses.length;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] != 0) arr[i]++;
        }
        int day = arr[0], count = 1;
        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i < len; i++) {
            if (arr[i] > day) {
                answer.add(count);
                day = arr[i];
                count = 1;
            } else {
                count++;
            }
        }
        if (count > 0) answer.add(count);
        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}
