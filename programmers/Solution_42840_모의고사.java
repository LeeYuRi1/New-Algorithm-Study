package programmers;

import java.util.ArrayList;
import java.util.List;

public class Solution_42840_모의고사 {
    private int[][] num = {{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};

    public int[] solution(int[] answers) {
        int[] score = new int[3];
        int max = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < answers.length; j++) {
                if (answers[j] == num[i][j % num[i].length]) score[i]++;
            }
            max = Math.max(max, score[i]);
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (score[i] == max) answer.add(i + 1);
        }
        return answer.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}
